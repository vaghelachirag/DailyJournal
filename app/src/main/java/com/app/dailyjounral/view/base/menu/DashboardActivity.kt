package com.app.dailyjounral.view.base.menu
import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.databinding.ActivityDashboardBinding
import com.app.dailyjounral.model.MenuDataModel
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.base.BaseActivity
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.adapter.MenuItemAdapter
import com.app.dailyjounral.uttils.AppConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@Suppress("DEPRECATION")
class DashboardActivity : BaseActivity(){


    private lateinit var binding: ActivityDashboardBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var activityDashboard : DashboardActivity? = null

    // Session
    private var session: Session? = null

    private var menuList = mutableListOf<MenuDataModel>()
    private var options: RequestOptions? = null;
    @SuppressLint("DiscouragedPrivateApi", "SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboard = this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        session = Session(this)

       options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        // Set up ActionBar
      //  setSupportActionBar(binding.toolbarDashboard)
        val actionBar = supportActionBar
        if (actionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.title ="Test"
        }

        setVersionName()

       /* supportActionBar!!.title = "Test"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_nav_menup)*/

        navController = findNavController(R.id.navHostFragmentPickford)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.dashboardMenuFragment,
            R.id.dashboardMenuFragment,
        ).setDrawerLayout(binding.drawer).build()

        Glide.with(this)
            .load(R.drawable.applogo)
            .circleCrop()
            .into(binding.ivLogo)


/*
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
*/

        addMenuData()
        setAction()
    }

    private fun addMenuData() {
        menuList.add(MenuDataModel("Home","",R.drawable.icon_home,true))
        menuList.add(MenuDataModel("Sleep","",R.drawable.icon_sleep,false))
        menuList.add(MenuDataModel("Gratitude","",R.drawable.icon_gradituty,false))


        menuList.add(MenuDataModel("Mood","",R.drawable.icon_mood,false))
        menuList.add(MenuDataModel("Profile","",R.drawable.icon_profile,false))
        menuList.add(MenuDataModel("Logout","",R.drawable.icon_login,false))

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvMenu.layoutManager = layoutManager

        binding.rvMenu.adapter = MenuItemAdapter(this, menuList,object :
            OnItemSelected<MenuDataModel> {

            override fun onItemSelected(t: MenuDataModel?, position: Int) {
               // clickMenuEvent(t)
                Log.e("MenuPosition",position.toString())
            }

        })

        val menuHeader = findViewById<View>(R.id.layoutMenu)
        val userImage = menuHeader.findViewById<View>(R.id.navHeaderLogo) as ImageView

        Glide.with(this)
            .load(R.drawable.user_image)
            .circleCrop()
            .into(userImage)
    }


    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return false
    }


    private fun setVersionName() {
     //   binding.txtVersion.text = "Version : " +BuildConfig.VERSION_NAME
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("NotifyUser")

        navController = findNavController(R.id.navHostFragmentPickford)

        setBottomTab()

      //  if (title != null) binding.tvTitle.text = "Dashboard"
        Log.e("OnResume","OnResume")
    }

    @SuppressLint("SetTextI18n")
    fun  setTitle(){
        //if (title != null) binding.tvTitle.text = "Dashboard"
    }

    private fun setAction() {
                binding.ivMenu.setOnClickListener {
                    if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
                        binding.drawer.closeDrawer(GravityCompat.START)
                    }else{
                        binding.drawer.openDrawer(GravityCompat.START)
                    }
                }
                binding.llHome.setOnClickListener {
                    binding.llTab1.visibility = View.VISIBLE
                    binding.llTab2.visibility = View.GONE


                    binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
                    binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

                    Glide.with(this).load(R.drawable.icon_home_active).apply(options!!).into(binding.ivHome)
                    Glide.with(this).load(R.drawable.icon_analystic_unselected).apply(options!!).into(binding.ivAnalytics)

                }

                binding.llAnalystic.setOnClickListener {
                    binding.llTab1.visibility = View.GONE
                    binding.llTab2.visibility = View.VISIBLE

                    binding.txtHome.setTextColor(resources.getColor(R.color.tab_un_selected_bg))
                    binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_selected_bg))

                   // binding.ivHome.setColorFilter(R.color.tab_un_selected_bg,android.graphics.PorterDuff.Mode.MULTIPLY);
                    Glide.with(this).load(R.drawable.icon_home_unselected).apply(options!!).into(binding.ivHome)
                    Glide.with(this).load(R.drawable.icon_anaylist_active).apply(options!!).into(binding.ivAnalytics)
                }
    }
    private fun setBottomTab() {
         binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
         binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

         binding.llTab1.visibility  = View.VISIBLE
         binding.llTab2.visibility  = View.GONE
    }
}