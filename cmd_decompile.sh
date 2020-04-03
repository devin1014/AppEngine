#!/usr/bin/env bash
#version: 20200324

function logVer() {
  echo -e "\033[30m$1\033[0m"
}

function logDebug() {
  echo -e "\033[34m$1\033[0m"
}

function logWarn() {
  echo -e "\033[33m$1\033[0m"
}

function logError() {
  echo -e "\033[31m$1\033[0m"
}

# build apk task
function buildApk() {
  local flavor=$1
  local type="$2"
  logVer "clean and build flavor: [${flavor}]......"
  ./gradlew :app:clean
  sleep 0.5s #wait 1s
  ./gradlew ":app:assemble${flavor}${type}"
  logVer "build success"
}

function decompileApk() {
  local flavor=$1
  local type=$2
  echo
  curPath=$(pwd)
  outputFile="${curPath}/app/build/outputs/apk/${flavor}/${type}"
  targetFile="${curPath}/app/outputs/${type}/${flavor}"
  rm -rf "$targetFile"
  mkdir -p "$targetFile"
  cp -r "$outputFile" "$targetFile"
  if [ $? -ne 0 ]; then
    echo -e "\033[31m unable to copy file: ${targetFile} \033[0m"
    exit
  else
    echo "copy files success to '${targetFile}'"
  fi
  cp -r "${curPath}/app/build/outputs/mapping/${flavor}${type}" "${curPath}/app/${type}/${flavor}/info/"
  #find apk file
  apks=$(find "${targetFile}" -name "*.apk")
  for file in $apks; do
    name=$(basename "$file" .apk)
    fileDir=${file%%.*}
    #apktool d apkFile -o test
    apktool -f -s d "$file" -o "$fileDir"
    if [ $? -ne 0 ]; then
      logError "********* apktool commond failed **********"
      unzip "$file" -d "$fileDir"
    fi
    #dex2jar
    sh "${DEX2JAR_HOME}/d2j-dex2jar.sh" "$file"
    mv -f "${curPath}/${name}-dex2jar.jar" "$fileDir/source.jar"
  done
}

function matrixApk() {
  local flavor=$1
  local type=$2
  echo
  curPath=$(pwd)
  outputFile="${curPath}/app/build/outputs/apk/${flavor}/${type}"
  targetFile="${curPath}/app/outputs/${type}/${flavor}"
  rm -rf "$targetFile"
  mkdir -p "$targetFile"
  #find apk file
  apks=$(find "${outputFile}" -name "*.apk")
  for file in $apks; do
    name=$(basename "$file" .apk)
    fileDir=${file%%.*}
    java "-jar" "${APKCHECKER_HOME}/apkchecker.jar" "--config" "${curPath}/matrix_config.json"
  done
}

#clear screen first
clear
echo "##########################################################"
echo "[1] buildDebugApk"
echo "[2] buildReleaseApk"
echo "[3] decompileDubgApk"
echo "[4] decompileReleaseApk"
echo "[5] checkDubgApk"
echo "[6] checkReleaseApk"
echo "[#] exit"
echo "##########################################################"
read -n1 -rp "Enter your command: " command
echo
if [ "$command" == "e" ] | [ "$command" == "#" ]; then
  echo "exit..."
  exit
fi
read -rp "Enter the flavor name: " flavor
if [ "$commond" == "1" ]; then
  buildApk "$flavor" "debug"
elif [ "$command" == "2" ]; then
  buildApk "$flavor" "release"
elif [ "$command" == "3" ]; then
  decompileApk "$flavor" "debug"
elif [ "$command" == "4" ]; then
  decompileApk "$flavor" "release"
elif [ "$command" == "5" ]; then
  matrixApk "$flavor" "debug"
elif [ "$command" == "6" ]; then
  matrixApk "$flavor" "release"
else
  logError "invalid command!"
fi
