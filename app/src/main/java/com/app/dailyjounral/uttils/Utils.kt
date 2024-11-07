package com.app.dailyjounral.uttils


import android.R.attr.textColor
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.app.dailyjounral.MainActivity
import com.app.dailyjounral.R
import com.app.dailyjounral.view.SplashScreen
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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


    fun  showSnackBar(context: Context, message: String, constraintLayout: ConstraintLayout){
        val snackBar = Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG)
        snackBar.setBackgroundTint(ContextCompat.getColor(context, R.color.tip_of_day_gradient_top))
        val view: View = snackBar.view
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.tip_of_day_gradient_top))
        snackBar.setActionTextColor(textColor)
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
        params.setMargins(0, 0, 0, 0)
        view.layoutParams = params
        snackBar.show()
    }

    // Show Logout Alert Dialoug
    public fun showAlertDialog(context: Context,strTitle: String) {

        session = Session(context);

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.attributes.windowAnimations = R.style.DialogTheme;
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_alert_dialoug)

        dialog.window!!.setBackgroundDrawableResource(R.color.tip_of_day_gradient_top);

        val txtHeader  : TextView = dialog.findViewById(R.id.tvMessage)
        txtHeader.text = strTitle

        // Button
        val buttonOk : MaterialButton = dialog.findViewById(R.id.btnOk)
        val buttonCancel : MaterialButton = dialog.findViewById(R.id.btnCancel)

        buttonOk.setOnClickListener {
            dialog.dismiss()
            session!!.clearSession()

            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val today = Date()
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(today)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {
        val today = Date()
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(today)
    }


    // Get WeekDay
    fun getCurrentWeekDay(): Int {
        val c: Calendar = Calendar.getInstance()
        val dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek
    }
}