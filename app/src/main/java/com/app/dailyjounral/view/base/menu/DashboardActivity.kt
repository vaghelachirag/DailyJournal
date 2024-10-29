package com.app.dailyjounral.view.base.menu
import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ActivityDashboardBinding
import com.app.dailyjounral.model.MenuDataModel
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.base.BaseActivity
import com.app.secureglobal.interfaces.OnItemSelected
import com.app.secureglobal.view.adapter.MenuItemAdapter


@Suppress("DEPRECATION")
class DashboardActivity : BaseActivity(){


    private lateinit var binding: ActivityDashboardBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var activityDashboard : DashboardActivity? = null

    // Session
    var session: Session? = null;

    private var menuList = mutableListOf<MenuDataModel>()

    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboard = this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        session = Session(this);


        // Set up ActionBar
        setSupportActionBar(binding.toolbarDashboard)
        val actionBar = supportActionBar
        if (actionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.title ="Test"
        }

        setVersionName()

        supportActionBar!!.title = "Test"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_menup)

        navController = findNavController(R.id.navHostFragmentPickford)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.dashboardMenuFragment,
            R.id.dashboardMenuFragment,
        ).setDrawerLayout(binding.drawer).build()


        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )

        addMenuData()
    }

    private fun addMenuData() {
        menuList.add(MenuDataModel("Home","",R.drawable.icon_home))
        menuList.add(MenuDataModel("Sleep","",R.drawable.icon_sleep))
        menuList.add(MenuDataModel("Gratitude","",R.drawable.icon_gradituty))


        menuList.add(MenuDataModel("Mood","",R.drawable.icon_mood))
        menuList.add(MenuDataModel("Profile","",R.drawable.icon_profile))
        menuList.add(MenuDataModel("Logout","",R.drawable.icon_login))

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvMenu.layoutManager = layoutManager

        binding.rvMenu.adapter = MenuItemAdapter(this, menuList,object :
            OnItemSelected<MenuDataModel> {

            override fun onItemSelected(t: MenuDataModel?, position: Int) {
               // clickMenuEvent(t)
            }

        })
    }


    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
    }


    private fun setVersionName() {
     //   binding.txtVersion.text = "Version : " +BuildConfig.VERSION_NAME
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("NotifyUser")

        navController = findNavController(R.id.navHostFragmentPickford)

        if (title != null) binding.tvTitle.text = "Dashboard"
        Log.e("OnResume","OnResume")
    }

    fun  setTitle(){
        if (title != null) binding.tvTitle.text = "Dashboard"
    }

    override fun onPause() {
        super.onPause()
    }


    private fun getToken(context: Context): String? {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty")
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {

            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

}