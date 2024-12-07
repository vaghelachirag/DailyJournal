package com.app.dailyjounral

import android.app.Application
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter


class App : Application() {

    override fun onCreate() {
        super.onCreate()



       Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("GlobalExceptionHandler", "Uncaught exception in thread: ${thread.name}", throwable)
            handleUncaughtException(thread, throwable)
        }
    }

    private fun handleUncaughtException(thread: Thread, throwable: Throwable) {
        // Example: Restart the app after a crash
        try {
            // Define the file path (External storage or app directory)
            val logFile = File(getExternalFilesDir(null), "app_crash_logs.txt")

            // Append the exception details to the file
            FileWriter(logFile, true).use { writer ->
                PrintWriter(writer).use { printWriter ->
                    printWriter.println("Thread: ${thread.name}")
                    printWriter.println("Exception: ${throwable.localizedMessage}")
                    printWriter.println("Stack Trace:")
                    throwable.printStackTrace(printWriter)
                    printWriter.println("--------------------------------------------------")
                }
            }

            Log.d("GlobalExceptionHandler", "Logged exception to: ${logFile.absolutePath}")
        } catch (e: Exception) {
            Log.e("GlobalExceptionHandler", "Failed to log exception to file", e)
        }
    }
}