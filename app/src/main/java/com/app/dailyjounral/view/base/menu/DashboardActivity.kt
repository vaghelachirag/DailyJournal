package com.app.dailyjounral.view.base.menu

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MenuItemAdapter
import com.app.dailyjounral.databinding.ActivityDashboardBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.MenuDataModel
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getLogoutResponse.GetLogoutResponse
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.view.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@Suppress("DEPRECATION")
class DashboardActivity : BaseActivity(){


    private lateinit var binding: ActivityDashboardBinding
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var activityDashboard : DashboardActivity? = null

    // Session
    private var session: Session? = null

    private var menuList = mutableListOf<MenuDataModel>()
    private var options: RequestOptions? = null
    private var menuAdapter: MenuItemAdapter? = null

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

        val menuHeader = findViewById<View>(R.id.layoutMenu)
        val llHeader = menuHeader.findViewById<View>(R.id.ll_Header) as ConstraintLayout

        llHeader.setOnClickListener {
            navController.navigate(R.id.MyProfileFragment)
            binding.drawer.closeDrawer(GravityCompat.START)
        }

        addMenuData()
        setAction()
    }

    private fun addMenuData() {
         menuList = mutableListOf<MenuDataModel>()
        menuList.add(MenuDataModel("Home","",R.drawable.icon_menu_home_unselected,R.drawable.icon_home,true))
        menuList.add(MenuDataModel("Tip of the day","",R.drawable.icon_menu_tip_of_day_unselected,R.drawable.icon_menu_tip_of_day_selected,false))
        menuList.add(MenuDataModel("Daily Quote","",R.drawable.icon_menu_quote_unselected,R.drawable.icon_menu_quote_selected,false))
        menuList.add(MenuDataModel("Daily Journal","",R.drawable.icon_menu_gradituty_unselected,R.drawable.icon_menu_gradituty_selected,false))
        menuList.add(MenuDataModel("Sleep","",R.drawable.icon_menu_sleep_unselected,R.drawable.icon_menu_sleep_selected,false))
        menuList.add(MenuDataModel("Gratitude","",R.drawable.icon_menu_gradituty_unselected,R.drawable.icon_menu_gradituty_selected,false))
        menuList.add(MenuDataModel("Mood","",R.drawable.icon_menu_mood_unselected,R.drawable.icon_menu_mood_selected,false))
        menuList.add(MenuDataModel("Goal Setting","",R.drawable.icon_menu_goal_unselected,R.drawable.icon_menu_goal_selected,false))
        menuList.add(MenuDataModel("Workout","",R.drawable.icon_menu_workout_unselected,R.drawable.icon_menu_workout_selected,false))
        menuList.add(MenuDataModel("Self-Care Tip","",R.drawable.icon_menu_selfcare_unselected,R.drawable.icon_menu_selfcare_selected,false))
        menuList.add(MenuDataModel("Profile","",R.drawable.icon_profile,R.drawable.icon_profile,false))
        menuList.add(MenuDataModel("Change Password","",R.drawable.icon_menu_change_password_unselected,R.drawable.icon_menu_change_password_selected,false))

        session= Session(this)
        if (!session!!.isLoggedIn){
            menuList.add(MenuDataModel("Login","",R.drawable.icon_menu_login_unseleted,R.drawable.icon_login_menu_selected,false))
        }else{
            if (session!!.user != null){
                val menuHeader = findViewById<View>(R.id.layoutMenu)
                val txtUserName = menuHeader.findViewById<View>(R.id.txt_UserName) as TextView
                txtUserName.text = session!!.user!!.fullName.toString()
                Log.e("UserName",session!!.user!!.fullName.toString())
            }
            menuList.add(MenuDataModel(AppConstants.menuLogout,"",R.drawable.icon_menu_login_unseleted,R.drawable.icon_login_menu_selected,false))
        }

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvMenu.layoutManager = layoutManager
        menuAdapter = MenuItemAdapter(this, menuList,object :
            OnItemSelected<MenuDataModel> {

            override fun onItemSelected(t: MenuDataModel?, position: Int) {
                // clickMenuEvent(t)
                Log.e("MenuPosition",position.toString())
                binding.drawer.closeDrawer(GravityCompat.START)
                if (position == 0){
                    navController.navigate(R.id.dashboardMenuFragment)
                }
                if (position == 1){
                    AppConstants.detailType == 1
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 2){
                    AppConstants.detailType = 2
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 3){
                    AppConstants.detailType = 3
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 4){
                    AppConstants.detailType = 4
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 5){
                    AppConstants.detailType = 5
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 6){
                    AppConstants.detailType = 6
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 7){
                    AppConstants.detailType = 7
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 8){
                    AppConstants.detailType = 8
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 9){
                    AppConstants.detailType = 9
                    navController.navigate(R.id.detailViewFragment)
                }
                if (position == 10){
                    AppConstants.detailType = 3
                    navController.navigate(R.id.MyProfileFragment)
                }
                if (position == 11){
                    if (session!!.isLoggedIn){
                        AppConstants.detailType = 6
                        navController.navigate(R.id.ChangePasswordFragment)
                    }else{
                        navController.navigate(R.id.LoginFragment)
                    }
                }
                if (position == 12){
                    /* AppConstants.detailType = 6
                     navController.navigate(R.id.LoginFragment)*/
                    Log.e("MenuName", t!!.title)
                    if (t.title == AppConstants.menuLogout){
                        Utils().showAlertDialog(this@DashboardActivity,resources.getString(R.string.logoutAlert))
                    }else{
                        AppConstants.detailType = 6
                        navController.navigate(R.id.LoginFragment)
                    }
                }
            }
        })

        binding.rvMenu.adapter =  menuAdapter

        if (session!!.isLoggedIn){
            setUserLogoAndName()
        }else{
            setAppLogo()
        }

    }

    @SuppressLint("CutPasteId")
    private fun setAppLogo() {

        val menuHeader = findViewById<View>(R.id.layoutMenu)
        val userImage = menuHeader.findViewById<View>(R.id.navHeaderAppLogo) as ImageView
        val llProfile = menuHeader.findViewById<View>(R.id.llProfile) as LinearLayout
        val appImage = menuHeader.findViewById<View>(R.id.navHeaderAppLogo) as ImageView

        llProfile.visibility = View.GONE
        appImage.visibility = View.VISIBLE


        Glide.with(this)
            .load(R.drawable.applogo)
            .circleCrop()
            .into(userImage)

    }

    public fun setUserLogoAndName() {

        val menuHeader = findViewById<View>(R.id.layoutMenu)
        val userImage = menuHeader.findViewById<View>(R.id.navHeaderLogo) as ImageView
        val llProfile = menuHeader.findViewById<View>(R.id.llProfile) as LinearLayout
        val appImage = menuHeader.findViewById<View>(R.id.navHeaderAppLogo) as ImageView

        llProfile.visibility = View.VISIBLE
        appImage.visibility = View.GONE

        var profileImage = ""
        var userName = ""
        if (session != null){
          if (!session!!.getUserProfileImageKey().isNullOrEmpty()){
            profileImage = session!!.getUserProfileImageKey()!!
          }
            if (!session!!.getUserNameKey().isNullOrEmpty()){
                userName = session!!.getUserNameKey()!!
                val txtUserName = menuHeader.findViewById<View>(R.id.txt_UserName) as TextView
                txtUserName.text = userName
            }
        }

        val options: RequestOptions = RequestOptions().placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_placeholder)
        Glide.with(this).load(profileImage)
            .circleCrop()
            .apply(options).into(userImage)

    }

    fun  callLogoutApi(){

      if (Utility.isNetworkConnected(this)){
          showProgressbar()
          Networking.with(this)
              .getServices()
              .getLogoutResponse(Utils().getUserToken(this))
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(object : CallbackObserver<GetLogoutResponse>() {
                  override fun onSuccess(response: GetLogoutResponse) {
                      hideProgressbar()
                      //redirectToHome()
                  }

                  override fun onFailed(code: Int, message: String) {
                      hideProgressbar()
                      session!!.clearSession()
                      Utils().showSnackBar(this@DashboardActivity,message,binding.constraintLayout)
                      Utils().reloadActivity(this@DashboardActivity)
                  }

                  override fun onNext(t: GetLogoutResponse) {
                      hideProgressbar()
                      session!!.clearSession()

                      if(t.getSuccess() == true){
                          Utils().showSnackBar(this@DashboardActivity,t.getMessage()!!,binding.constraintLayout)
                      }else{
                          Utils().showSnackBar(this@DashboardActivity,t.getMessage()!!,binding.constraintLayout)
                      }
                      Utils().reloadActivity(this@DashboardActivity)
                      Log.e("StatusCode",t.getSuccess().toString())
                  }

              })
      }else{
          Utils().showToast(this,this.getString(R.string.nointernetconnection).toString())
      }

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

                    binding.ivHome.setBackgroundResource(R.drawable.icon_home_active);
                    binding.ivAnalytics.setBackgroundResource(R.drawable.icon_analystic_unselected);

                    navController.navigate(R.id.dashboardMenuFragment)
                }

                binding.llAnalystic.setOnClickListener {
                    binding.llTab1.visibility = View.GONE
                    binding.llTab2.visibility = View.VISIBLE

                    binding.txtHome.setTextColor(resources.getColor(R.color.tab_un_selected_bg))
                    binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_selected_bg))


                    binding.ivHome.setBackgroundResource(R.drawable.icon_home_unselected)
                    binding.ivAnalytics.setBackgroundResource(R.drawable.icon_anaylist_active)

                    navController.navigate(R.id.AnalyticsFragment)
                }
    }
    private fun setBottomTab() {
         binding.txtHome.setTextColor(resources.getColor(R.color.tab_selected_bg))
         binding.txtAnalytics.setTextColor(resources.getColor(R.color.tab_un_selected_bg))

        binding.ivHome.setBackgroundResource(R.drawable.icon_home_active);
        binding.ivAnalytics.setBackgroundResource(R.drawable.icon_analystic_unselected);

         binding.llTab1.visibility  = View.VISIBLE
         binding.llTab2.visibility  = View.GONE
    }

   fun setSelectedMenuPosition(position: Int){
       if (menuAdapter !=null){
           menuAdapter!!.mSelectedItem = position
           menuAdapter!!.notifyDataSetChanged()
       }
    }
}