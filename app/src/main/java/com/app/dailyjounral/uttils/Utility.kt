package com.app.dailyjounral.uttils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.app.dailyjounral.R
import com.app.dailyjounral.uttils.AppConstants.baseURL
import com.app.dailyjounral.view.DetailFragment
import com.app.dailyjounral.view.base.menu.DashboardActivity
import com.app.dailyjounral.view.dialougs.MessageDialog
import com.bumptech.glide.request.RequestOptions
import java.io.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class Utility {

    companion object {
        private const val requestCode: Int = 1111

        private val VALID_EMAIL_ADDRESS_REGEX: Pattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        private val VALID_PASSWORD_REGEX: Pattern = Pattern.compile(
            "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$",
            Pattern.CASE_INSENSITIVE
        )

        fun View.setAllEnabled(enabled: Boolean) {
            isEnabled = enabled

        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentDate() : String{
            var currentDate : String = "";
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            currentDate = LocalDateTime.now().format(formatter)
           return currentDate;
        }

        fun getUserToken(context: Context): String {
           val session = Session(context)
            var token = ""
            if (session.isLoggedIn){
                token =  "Bearer " + session.user!!.token
            }
            return token
        }

        fun getDeviceHeight(context: Activity) : Int{
            val displayMetrics = DisplayMetrics()
            context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val height = displayMetrics.heightPixels
            return height
        }

        fun getParseInteger(str: String?): Int {
            var int = 0

            if (str.isNullOrBlank() || str.isEmpty() || str.isEmpty() || str.toIntOrNull() == null) {
                int = 0
            } else {
                int = Integer.parseInt(str)
            }
            return  int;
        }

        fun changeBaseURL(intURL: Int) {
            // Staging URL
            baseURL = when (intURL) {
                0 -> {
                    // Staging URL
                    AppConstants.StagingURL
                }
                // Live URL
                1 -> {
                    AppConstants.LiveURL
                }
                2 -> {
                    AppConstants.TestLiveURL
                }
                else -> {
                    // Local URL
                    "http://192.168.1.103:10111/api/"
                }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun getDrawable(context: Context?, id: Int): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ContextCompat.getDrawable(context!!, id)!!
            } else {
                ResourcesCompat.getDrawable(context!!.resources, id, null)!!
            }
        }
        private fun isExternalStorageDocument(uri: Uri): Boolean {
            return "com.android.externalstorage.documents" == uri.authority
        }

        private fun isDownloadsDocument(uri: Uri): Boolean {
            return "com.android.providers.downloads.documents" == uri.authority
        }

         fun showLocationAlert(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Alert")
            builder.setMessage("Please start GPS first")

            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                context.startActivity( Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                (context as Activity).finish()
            }

            builder.show()
        }

        // Session Expired
        fun sessionExpired(context: Context){
            var session  = Session(context)
            session!!.clearSession()
            MessageDialog(context, context.getString(R.string.session_expired)).show()
            (context as DashboardActivity).navController.navigate(R.id.LoginFragment)
          //  Utils().reloadActivity(context)
        }

        private fun turnGPSOn(context: Context) {
            val provider = Settings.Secure.getString(
                (context as Activity).contentResolver,
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED
            )

            if (!provider.contains("gps")) { //if gps is disabled
                val poke = Intent()
                poke.setClassName(
                    "com.android.settings",
                    "com.android.settings.widget.SettingsAppWidgetProvider"
                )
                poke.addCategory(Intent.CATEGORY_ALTERNATIVE)
                poke.setData(Uri.parse("3"))
                context.sendBroadcast(poke)
            }
        }

        // Redirect To Login
        fun redirectToLogin(detailFragment : DetailFragment){
            detailFragment.findNavController().popBackStack(R.id.LoginFragment, false)
        }

        fun getNullToBlankString(mainString: String) : String{
            var currentDate : String = "";
            if (mainString == "NULL" || mainString == "null"){
                currentDate = ""
            }
            else{
                currentDate = mainString
            }
            return currentDate;
        }



        @SuppressLint("SimpleDateFormat")
        fun convertDateTime(value: String?) : String {
            var outputString = ""
            if (value != null) {
                val cal = Calendar.getInstance()
                try {
                    val input = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                    cal.time = input.parse(value)!!
                    val output = SimpleDateFormat("dd-M-yyyy hh:mm:ss")
                    outputString =  output.format(cal.timeInMillis).toString()
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
            return outputString
        }

        @SuppressLint("SimpleDateFormat")
        fun convertDate(value: String?) : String {
            var outputString = ""
            if (value != null) {
                val cal = Calendar.getInstance()
                try {
                    val input = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                    cal.time = input.parse(value)!!
                    val output = SimpleDateFormat("dd-M-yyyy")
                    outputString =  output.format(cal.timeInMillis).toString()
                } catch (e: ParseException) {
                    e.printStackTrace()
                }
            }
            return outputString
        }

        private fun getDataColumn(
            context: Context, uri: Uri?, selection: String?,
            selectionArgs: Array<String>?
        ): String? {
            var cursor: Cursor? = null
            val column = "_data"
            val projection = arrayOf(
                column
            )
            try {
                cursor = context.contentResolver.query(
                    uri!!, projection, selection, selectionArgs,
                    null
                )
                if (cursor != null && cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(column)
                    return cursor.getString(columnIndex)
                }
            } finally {
                cursor?.close()
            }
            return null
        }

        fun getPath(context: Context, uri: Uri): String? {
            val isKitKat = true

            // DocumentProvider
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":").toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }

                    // TODO handle non-primary volumes
                } else if (isDownloadsDocument(uri)) {
                    val id = DocumentsContract.getDocumentId(uri)
                    if (id != null && id.startsWith(
                            "raw:"
                        )
                    ) {
                        return id.substring(4)
                    }
                    val contentUriPrefixesToTry = arrayOf(
                        "content://downloads/public_downloads",
                        "content://downloads/my_downloads"
                    )
                    for (contentUriPrefix in contentUriPrefixesToTry) {
                        try {
                            val contentUri = ContentUris.withAppendedId(
                                Uri.parse(contentUriPrefix),
                                java.lang.Long.valueOf(id)
                            )
                            val path: String? =
                                getDataColumn(context, contentUri, null, null)
                            if (path != null) {
                                return path
                            }
                        } catch (ignored: Exception) {
                        }
                    }

                    // path could not be retrieved using ContentResolver, therefore copy file to accessible cache using streams
                    val fileName = getFileName(context, uri)
                    val cacheDir = getDocumentCacheDir(context)
                    val file = generateFileName(fileName, cacheDir)
                    var destinationPath: String? = null
                    if (file != null) {
                        destinationPath = file.absolutePath
                        saveFileFromUri(context, uri, destinationPath)
                    }
                    return destinationPath
                } else if (isMediaDocument(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":").toTypedArray()
                    val type = split[0]
                    var contentUri: Uri? = null
                    if ("image" == type) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if ("video" == type) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if ("audio" == type) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(
                        split[1]
                    )
                    return getDataColumn(
                        context,
                        contentUri,
                        selection,
                        selectionArgs
                    )
                }
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                return getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                return uri.path
            }
            return null
        }

        private fun isMediaDocument(uri: Uri): Boolean {
            return "com.android.providers.media.documents" == uri.authority
        }

        private fun saveFileFromUri(context: Context, uri: Uri, destinationPath: String?) {
            var `is`: InputStream? = null
            var bos: BufferedOutputStream? = null
            try {
                `is` = context.contentResolver.openInputStream(uri)
                bos = BufferedOutputStream(FileOutputStream(destinationPath, false))
                val buf = ByteArray(1024)
                `is`!!.read(buf)
                do {
                    bos.write(buf)
                } while (`is`.read(buf) != -1)
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    `is`?.close()
                    bos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


        public  fun getBackupFolderData(exportDir: File) : ArrayList<String>? {
            val result = ArrayList<String>()
            try {
                val folder = File(exportDir.path)
                val filesInFolder = folder.listFiles()
                for (file in filesInFolder!!) {
                    if (!file.isDirectory) {
                        result.add(java.lang.String(file.name) as String)
                    }
                }
            }catch (e: Exception){
            }
            return  result
        }

        private fun generateFileName(nameS: String?, directory: File?): File? {
            var name = nameS ?: return null
            var file = File(directory, name)
            if (file.exists()) {
                var fileName = name
                var extension = ""
                val dotIndex = name.lastIndexOf('.')
                if (dotIndex > 0) {
                    fileName = name.substring(0, dotIndex)
                    extension = name.substring(dotIndex)
                }
                var index = 0
                while (file.exists()) {
                    index++
                    name = "$fileName($index)$extension"
                    file = File(directory, name)
                }
            }
            try {
                if (!file.createNewFile()) {
                    return null
                }
            } catch (e: IOException) {
                return null
            }
            return file
        }

        private fun getDocumentCacheDir(context: Context): File {
            val dir = File(context.cacheDir, "documents")
            if (!dir.exists()) {
                dir.mkdirs()
            }
            return dir
        }

        fun getName(filename: String?): String? {
            if (filename == null) {
                return null
            }
            val index = filename.lastIndexOf('/')
            return filename.substring(index + 1)
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        fun getFileName(context: Context, uri: Uri): String? {
            val mimeType = context.contentResolver.getType(uri)
            var filename: String? = null
            if (mimeType == null && context != null) {
                val path = getPath(context, uri)
                filename = if (path == null) {
                    getName(uri.toString())
                } else {
                    val file = File(path)
                    file.name
                }
            } else {
                val returnCursor = context.contentResolver.query(uri, null, null, null, null)
                if (returnCursor != null) {
                    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    filename = returnCursor.getString(nameIndex)
                    returnCursor.close()
                }
            }
            return filename
        }

        fun  getGlideRequestOption(): RequestOptions{
            val options: RequestOptions = RequestOptions().placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder)
            return  options
        }

       /**
         * Check Internet is connected or not
         */
        fun isNetworkConnected(context: Context?): Boolean {
            val connectivityManager: ConnectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT < 23) {
                val ni = connectivityManager.activeNetworkInfo
                if (ni != null) {
                    return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI
                            || ni.type == ConnectivityManager.TYPE_MOBILE)
                }
            } else {
                val network: Network? = connectivityManager.activeNetwork
                if (network != null) {
                    val networkCapabilities: NetworkCapabilities? =
                        connectivityManager.getNetworkCapabilities(network)

                    return networkCapabilities!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }
            }
            return false
        }

        /**
         * Share string Message To Other Apps
         */
        fun shareMessageToOtherApps(mContext: Context, message: String, extraMessage: String) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, extraMessage)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, message)
            mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }

        fun isEmailValid(string: String): Boolean {
            val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(string)
            return matcher.find()
        }

       fun formatDecimal(doubleValue: Double?): String? {
            val numberFormat: NumberFormat = DecimalFormat("##.##")
            return "$" + numberFormat.format(doubleValue)
        }

        fun copyUriToFile(context: Context, uri: Uri): File? {
            var `in`: InputStream? = null
            var out: OutputStream? = null
            var outFile: File? = null
            try {
                if (context.contentResolver != null) {
                    `in` = context.contentResolver.openInputStream(uri)
                    val path: String = "temp_image_2_" + System.currentTimeMillis() + ".jpg"
                    outFile = createImageFile(context, path)
                    if (outFile != null && `in` != null) {
                        out = FileOutputStream(outFile)
                        val buf = ByteArray(1024)
                        var len: Int
                        while (`in`.read(buf).also { len = it } > 0) {
                            out.write(buf, 0, len)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally { // Ensure that the InputStreams are closed even if there's an exception.
                try {
                    out?.close()
                    // If you want to close the "in" InputStream yourself then remove this
                    // from here but ensure that you close it yourself eventually.
                    `in`?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return outFile
        }

        @Throws(IOException::class)
        fun createImageFile(context: Context, imageFileName: String): File? {
            var storageDir = context.filesDir
            val dirCreated: Boolean
            if (storageDir == null) {
                val externalStorage =
                    context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                if (externalStorage == null) {
                    storageDir =
                        File(context.cacheDir, Environment.DIRECTORY_PICTURES)
                    dirCreated = storageDir.exists() || storageDir.mkdirs()
                } else {
                    dirCreated = true
                }
            } else {
                storageDir =
                    File(context.filesDir, Environment.DIRECTORY_PICTURES)
                dirCreated = storageDir.exists() || storageDir.mkdirs()
            }
            return if (dirCreated) {
                val imageFile = File(storageDir, imageFileName)
                var isDeleted = true
                if (imageFile.exists()) {
                    isDeleted = imageFile.delete()
                }
                if (isDeleted) {
                    val fileCreated = imageFile.createNewFile()
                    if (fileCreated) imageFile else null
                } else {
                    null
                }
            } else {
                null
            }
        }
    }

}