package com.app.dailyjounral.uttils


import android.content.Context
import android.text.Html
import android.view.Gravity
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class Utils {

    var session: Session? = null;

    fun showToast(context: Context,message: String){
        val toast = Toast.makeText(
            context,
            Html.fromHtml("<font color='#e3f2fd' ><b>$message</b></font>"),
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }


    fun getCurrentDate(): String {
        val today = Date()
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(today)
    }

    // Get WeekDay
    fun getCurrentWeekDay(): Int {
        val c: Calendar = Calendar.getInstance()
        val dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek
    }
}