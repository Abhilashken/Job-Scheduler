package com.flaierovationstudios.jobschedulerexample

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log


class ExampleJobService : JobService() {

    var TAG: String = "ExampleJobService"
    var jobCancelled: Boolean = false


    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        doInBackgroundWork(params)
        return true

    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job Cancelled Before Completion")
        jobCancelled = true
        return true
    }




    fun doInBackgroundWork(params: JobParameters?) {
        Log.d(TAG, "doInBackgroundWork block")
         val thread=Thread{
             Log.d(TAG, "Thread block")

             for (i in 1..10) {
                 Log.d(TAG, "run: $i");
                 if (jobCancelled) {
                     return@Thread
                 }
                 try {
                     Thread.sleep(1000);
                 } catch (e: InterruptedException) {
                     e.printStackTrace();
                 }
             }

             Log.d(TAG, "Job finished");
             jobFinished(params, false);
         }
         thread.start();
    }








/*    class SimpleThread(var TAG: String,  var jobCancelled: Boolean, var params: JobParameters?) : Thread() {


        override fun run() {
            println("${Thread.currentThread()} has run.")


            Log.d(TAG, "Thread block")

            for (i in 1..10) {
                Log.d(TAG, "run: $i");
                if (jobCancelled) {
                    return
                }
                try {
                    Thread.sleep(1000);
                } catch (e: InterruptedException) {
                    e.printStackTrace();
                }
            }

            Log.d(TAG, "Job finished");
            ExampleJobService.jobFinished(params, false);
        }
    }*/


    /*   Thread(Runnable {
         for (i in 1..10) {
             Log.d(TAG, "run: $i");
             if (jobCancelled) {
                 return@Runnable
             }
             try {
                 Thread.sleep(1000);
             } catch (e: InterruptedException) {
                 e.printStackTrace();
             }
         }

         Log.d(TAG, "Job finished");
         jobFinished(params, false);
     }).start()*/
}



