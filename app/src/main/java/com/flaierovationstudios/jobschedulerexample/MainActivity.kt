package com.flaierovationstudios.jobschedulerexample

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    // var btnScheduleJob: Button? =null; var btnCancelJob: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnScheduleJob: Button = findViewById(R.id.btnScheduleJob)
        val btnCancelJob: Button = findViewById(R.id.btnCancelJob)


        btnScheduleJob.setOnClickListener(
            View.OnClickListener {
                Log.d(TAG, "Schedule clicked ")
                scheduleJob()
            }
        )

        btnCancelJob.setOnClickListener(View.OnClickListener {
            Log.d(TAG, " Cancel Clicked")
            cancelJob()
        })



    }


    fun scheduleJob() {
        val componentName = ComponentName(this, ExampleJobService::class.java)
        val jobInfo = JobInfo.Builder(123, componentName)
            .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic(15 * 60 * 1000)
            .build()

        val jobScheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = jobScheduler.schedule(jobInfo)

        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job Scheduled")
        } else {
            Log.d(TAG, "Job Scheduling Failed")
        }
    }


    fun cancelJob() {
        val jobScheduler: JobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(123)
        Log.d(TAG, "Job Cancelled")

    }
}
