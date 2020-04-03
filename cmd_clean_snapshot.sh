#!/usr/bin/env bash

function deleteSnapshots() {
    #$USER_HOME$/.gradle/caches/modules-2/files-2.1/
    desDir="${HOME}/desktop/cache"
    mkdir -p "$desDir"
    gradleCacheDir="${HOME}/.gradle/caches/modules-2/files-2.1"
    curPath=$(pwd)
    cacheDir="${curPath}/.idea/libraries"
    #echo "cacheDir: ${cacheDir}"
    cache=$1
    echo "try to delete: ${cache}"
    cacheFileName=${cache//"."/"_"}
    cacheFileName=${cacheFileName//"-"/"_"}
    cacheFileName=${cacheFileName//":"/"_"}
    #echo "cache: ${cacheFileName}"
    files=$(find "${cacheDir}" -type f -name "*${cacheFileName}*")
    if [ ${#files} -eq 0 ]; then
      echo "not find android studio cache file"
    fi
    for file in $files; do
      #delete android studio cache .idea/libraries
      rm -rf "${file}"
      echo "delete: ${file}"
      array=(${cache//:/ })
      group=${array[0]}
      module=${array[1]}
      version=${array[2]}
      gradleCacle="${gradleCacheDir}/${group}/${module}/${version}"
      #rm -rf "${gradleCacle}"
      mv -f "${gradleCacle}" "$desDir" #if remove file, can not revert ever.
      echo "delete: ${gradleCacle}"
    done
}

function findSnapshots() {
  echo "************************************************"
  echo "parse 'build.gradle'"
  echo "************************************************"
  echo "you can force delete gradle cache [number]"
  echo "################################################"
  num=0
  filterKeys=("implementation" "api")
  gradleFile="app/build.gradle"
  echo "[0] All"
  while read -r line; do
    for key in $filterKeys; do
      if [ $(expr "$line" : "$key") -eq ${#key} ]; then
        imp=${line:${#key}}
        str="${imp:0:2}"
        if [ "$str" == " '" ] || [ "$str" == " \"" ] || [ "$str" == "('" ] || [ "$str" == "(\"" ]; then
          imp=${imp//' '/}
          imp=${imp//\'/}
          imp=${imp//\"/}
          imp=${imp//\(/}
          imp=${imp//\)/}
          imp=${imp//\{/}
          if [[ "$imp" == *SNAPSHOT* ]]; then
            snapshots[$num]="$imp"
            num=$(($num + 1))
            echo "[$num] $imp"
          fi
        fi
      fi
    done
  done<"$gradleFile"
  echo "[#] refresh dependencies"
  echo "[e] exit"
  echo "################################################"

  while read -rp "select the number: " commond; do
    if [ "$commond" == "0" ]; then
      read -n1 -rp "confirm clean all SNAPSHOTS? [yes|no]: " confirm
        echo
        case "$confirm" in
          "Y" | "y")
          for(( i=0;i<${#snapshots[@]};i++)) do
            deleteSnapshots "${snapshots[$i]}"
          done;
          ;;
          "N" | "n")
          ;;
        esac
    elif [ "$commond" == "e" ] || [ "$commond" == "exit" ]; then
        break
    elif [ "$commond" == "#" ]; then
        ./gradlew --refresh-dependencies
        break
    else
      num=$(($commond-1))
      deleteSnapshots "${snapshots[$num]}"
    fi
  done
}

#clear screen first
clear
findSnapshots
echo
echo "complete"