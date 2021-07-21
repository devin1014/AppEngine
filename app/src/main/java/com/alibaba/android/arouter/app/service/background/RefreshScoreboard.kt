package com.alibaba.android.arouter.app.service.background

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.alibaba.android.arouter.app.util.AppLog
import java.util.concurrent.TimeUnit

class RefreshScoreboard(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        // refresh schedule data.
        AppLog.info("${this.javaClass.simpleName} doWork...")
        AppLog.info("${this.javaClass.simpleName} doWork...")
        AppLog.info("${this.javaClass.simpleName} doWork...")

        return Result.success()
    }
}

//val refreshScoreboard: WorkRequest = OneTimeWorkRequestBuilder<RefreshScoreboard>().build()
val onceScoreboardRequest: OneTimeWorkRequest = OneTimeWorkRequest.from(RefreshScoreboard::class.java)

val refreshScoreboardRequest = PeriodicWorkRequestBuilder<RefreshScoreboard>(
    30, TimeUnit.MINUTES,
    15, TimeUnit.MINUTES
).build()