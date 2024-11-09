package com.app.dailyjounral.viewmodel

import SleepSelectorAdapter
import WorkoutSelectorAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MoodSelectorAdapter
import com.app.dailyjounral.adapter.WeekAdapter
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.ModelDataWeek
import com.app.dailyjounral.model.getDailyQuoteResponse.GetDailyQuoteResponse
import com.app.dailyjounral.model.getSelfCareTipResponse.GetSelfCareTipResponse
import com.app.dailyjounral.model.getTipOfTheDayResponse.GetTipOfTheDayResponse
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.model.base.BaseViewModel
import com.app.dailyjounral.model.getDailyGoalResponse.GetDailyGoalResponse
import com.app.dailyjounral.model.getDailyReflectionResponse.GetDailyReflectionResponse
import com.app.dailyjounral.model.getMoodResponse.GetMoodDataResponse
import com.app.dailyjounral.model.getMoodResponse.GetMoodDataResponseData
import com.app.dailyjounral.model.getMoodResponse.SetSelectedMoodData
import com.app.dailyjounral.model.getSaveMoodDataResponse.GetSaveMoodDataResponse
import com.app.dailyjounral.model.getSleepDataResponse.GetSleepDataResponse
import com.app.dailyjounral.model.getSleepDataResponse.GetSleepDataResponseData
import com.app.dailyjounral.model.getSleepDataResponse.SetSelectedSleepData
import com.app.dailyjounral.model.getWorkoutDataResponse.GetWorkoutDataResponse
import com.app.dailyjounral.model.getWorkoutDataResponse.GetWorkoutResponseData
import com.app.dailyjounral.model.getWorkoutDataResponse.SetSelectedWorkoutData
import com.app.dailyjounral.uttils.Session
import com.app.dailyjounral.view.DetailFragment
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailViewModel(val context: Context, val binding: DetailActivityBinding, private val detailFragment: DetailFragment) : BaseViewModel()  {

    private var weekDayList = mutableListOf<ModelDataWeek>()
    private var moodDataList = mutableListOf<SetSelectedMoodData>()
    private var workoutDataList = mutableListOf<SetSelectedWorkoutData>()
    private var sleepDataList = mutableListOf<SetSelectedSleepData>()

    val session  = Session(context)

    // All Selected ItemType Id
    var selectedMoodTypeId : MutableLiveData<Int> = MutableLiveData<Int>()
    private var selectedWorkoutTypeId : MutableLiveData<Int> = MutableLiveData<Int>()
    private var selectedSleepTypeId : MutableLiveData<Int> = MutableLiveData<Int>()

    fun init() {
         selectedMoodTypeId.value = 0
        selectedWorkoutTypeId.value = 0
        selectedSleepTypeId.value = 0

        if (AppConstants.detailType == 1){
            getTipOfDay()
        }
        if (AppConstants.detailType == 2){
            getDailyQuote()
        }

        if (AppConstants.detailType == 3){
            getDailyGeneralApiResponse()
        }

        if (AppConstants.detailType == 4){
            getSleepApiResponse()
            addSleepData()
        }
        if (AppConstants.detailType == 5){
          //  addWorkoutData()
        }

        if (AppConstants.detailType == 6){
          getMoodDetectorApiResponse()
          addMoodData()
        }
        if (AppConstants.detailType == 7){
           getDailyGoalApiResponse()
        }
        if (AppConstants.detailType == 8){
            getWorkoutApiResponse()
            addWorkoutData()
        }
        if (AppConstants.detailType == 9){
            getSelfCareTip()
        }
       addWeekData()

    }

    private fun getDailyGeneralApiResponse() {
        if (!session.isLoggedIn){
            detailFragment.findNavController().navigate(R.id.LoginFragment)
            return
        }

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getDailyReflectionResponse(Utils().getUserToken(context),Utils().getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDailyReflectionResponse>() {
                    override fun onSuccess(response: GetDailyReflectionResponse) {
                        isLoading.postValue(false)
                        if (response.getSuccess() == true){
                            if (response.getData() != null){
                                setDailyReflectionQuestionData(response)
                            }
                        }
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(getDailyReflectionResponse: GetDailyReflectionResponse) {
                        isLoading.postValue(false)
                        if (getDailyReflectionResponse.getSuccess() == true){
                            if (getDailyReflectionResponse.getData() != null){
                                setDailyReflectionQuestionData(getDailyReflectionResponse)
                            }
                        }
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Get Daily Goal Setting Response
    private fun getDailyGoalApiResponse() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getDailyGoalResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDailyGoalResponse>() {
                    override fun onSuccess(response: GetDailyGoalResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())

                    }

                    override fun onNext(getDailyGoalResponse: GetDailyGoalResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",getDailyGoalResponse.getSuccess().toString())
                        if(getDailyGoalResponse.getSuccess() == true){
                            if(getDailyGoalResponse.getData() != null){
                                setDailyGoalData(getDailyGoalResponse)
                            }
                        }else{
                            Utils().showToast(context,getDailyGoalResponse.getMessage().toString())
                        }
                        Log.e("StatusCode",getDailyGoalResponse.getSuccess().toString())
                    }



                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Save Mood Type Response
    fun saveMoodApiResponse(typeId: Int) {

        val params = HashMap<String,Any>()
        params["moodDate"] =  Utils().getDate()
        params["moodTypeId"] = typeId

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSaveMoodDataResponse(Utility.getUserToken(context),Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSaveMoodDataResponse>() {
                    override fun onSuccess(response: GetSaveMoodDataResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()

                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(t: GetSaveMoodDataResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                         //   detailFragment.findNavController().navigate(R.id.dashboardMenuFragment)
                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Save Sleep Type Response
    fun saveSleepApiResponse(typeId: Int) {

        val params = HashMap<String,Any>()
        params["sleepDate"] =  Utils().getDate()
        params["sleepHour"] = typeId

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSaveSleepDataResponse(Utility.getUserToken(context),Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSaveMoodDataResponse>() {
                    override fun onSuccess(response: GetSaveMoodDataResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()

                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(t: GetSaveMoodDataResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){

                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Save Sleep Type Response
    fun saveWorkoutApiResponse(typeId: Int) {

        val params = HashMap<String,Any>()
        params["workoutDate"] =  Utils().getDate()
        params["workoutTypeId"] = typeId

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSaveWorkoutDataResponse(Utility.getUserToken(context),Networking.wrapParams(params))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSaveMoodDataResponse>() {
                    override fun onSuccess(response: GetSaveMoodDataResponse) {
                        isLoading.postValue(false)
                        //redirectToHome()

                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(t: GetSaveMoodDataResponse) {
                        Log.e("Status",t.getSuccess().toString())
                        isLoading.postValue(false)
                        if(t.getSuccess() == true){

                        }else{
                            //  Utils().showToast(context,t.getMessage().toString())
                            Utils().showSnackBar(context,t.getMessage().toString(),binding.constraintLayout)
                        }
                        Log.e("StatusCode",t.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Get Sleep data from API
    private fun getSleepApiResponse() {
        if (!session.isLoggedIn){
            detailFragment.findNavController().navigate(R.id.LoginFragment)
            return
        }

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getUserSleepResponse(Utils().getUserToken(context),Utils().getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSleepDataResponse>() {
                    override fun onSuccess(response: GetSleepDataResponse) {
                        isLoading.postValue(false)
                        if (response.getSuccess() == true){
                            if (response.getData() != null){
                                setSelectedSleepData(response?.getData()!!)
                            }
                        }
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(getSleepDataResponse: GetSleepDataResponse) {
                        isLoading.postValue(false)
                        if (getSleepDataResponse.getSuccess() == true){
                            if (getSleepDataResponse.getData() != null){
                                setSelectedSleepData(getSleepDataResponse?.getData()!!)
                            }
                        }
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Get Workout data from API
    private fun getWorkoutApiResponse() {
        if (!session.isLoggedIn){
            detailFragment.findNavController().navigate(R.id.LoginFragment)
            return
        }

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getUserWorkoutResponse(Utils().getUserToken(context),Utils().getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetWorkoutDataResponse>() {
                    override fun onSuccess(response: GetWorkoutDataResponse) {
                        isLoading.postValue(false)
                        if (response.getSuccess() == true){
                            if (response.getData() != null){
                                setSelectedWorkoutData(response?.getData()!!)
                            }
                        }
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(getWorkoutResponseData: GetWorkoutDataResponse) {
                        isLoading.postValue(false)
                        if (getWorkoutResponseData.getSuccess() == true){
                            if (getWorkoutResponseData.getData() != null){
                                setSelectedWorkoutData(getWorkoutResponseData?.getData()!!)
                            }
                        }
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // Get Mood data from API
    private fun getMoodDetectorApiResponse() {

        if (!session.isLoggedIn){
            detailFragment.findNavController().navigate(R.id.LoginFragment)
            return
        }

        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getUserMoodResponse(Utils().getUserToken(context),Utils().getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetMoodDataResponse>() {
                    override fun onSuccess(response: GetMoodDataResponse) {
                        isLoading.postValue(false)
                        if (response.getSuccess() == true){
                         if (response.getData() != null){
                             setSelectedMoodData(response?.getData()!!)
                         }
                        }
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())
                        Utils().showSnackBar(context,message,binding.constraintLayout)
                    }

                    override fun onNext(getMoodDataResponse: GetMoodDataResponse) {
                        isLoading.postValue(false)
                        if (getMoodDataResponse.getSuccess() == true){
                            if (getMoodDataResponse.getData() != null){
                                setSelectedMoodData(getMoodDataResponse?.getData()!!)
                            }
                        }
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    // set Selected Mood Data
    private fun setSelectedMoodData(getMoodDataResponseData: GetMoodDataResponseData) {
         selectedMoodTypeId.postValue(getMoodDataResponseData.getMoodTypeId())
         Log.e("SelectedData",selectedMoodTypeId.value.toString())
    }

    // set Selected Workout Dat
    private fun setSelectedWorkoutData(getWorkoutResponseData: GetWorkoutResponseData) {
        selectedWorkoutTypeId.postValue(getWorkoutResponseData.getWorkoutTypeId())
        Log.e("SelectedData",selectedWorkoutTypeId.value.toString())
    }

    // set Selected Workout Dat
    private fun setSelectedSleepData(getSleepDataResponseData: GetSleepDataResponseData) {
        selectedSleepTypeId.postValue(getSleepDataResponseData.getSleepHour())
        Log.e("SelectedData",selectedSleepTypeId.value.toString())
    }


    // Call API For Self Care Tip
    private fun getSelfCareTip() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getSelfCareTip()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetSelfCareTipResponse>() {
                    override fun onSuccess(response: GetSelfCareTipResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())

                    }

                    override fun onNext(getSelfCareTipData: GetSelfCareTipResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",getSelfCareTipData.getSuccess().toString())
                        if(getSelfCareTipData.getSuccess() == true){
                            if(getSelfCareTipData.getData() != null){
                                setSelfCareTipData(getSelfCareTipData)
                            }
                        }else{
                            Utils().showToast(context,getSelfCareTipData.getMessage().toString())
                        }
                        Log.e("StatusCode",getSelfCareTipData.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    private fun getDailyQuote() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getDailyQuote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetDailyQuoteResponse>() {
                    override fun onSuccess(response: GetDailyQuoteResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())

                    }

                    override fun onNext(getDailyQuoteResponse: GetDailyQuoteResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",getDailyQuoteResponse.getSuccess().toString())
                        if(getDailyQuoteResponse.getSuccess() == true){
                            if(getDailyQuoteResponse.getData() != null){
                                setDailyQuoteData(getDailyQuoteResponse)
                            }
                        }else{
                            Utils().showToast(context,getDailyQuoteResponse.getMessage().toString())
                        }
                        Log.e("StatusCode",getDailyQuoteResponse.getSuccess().toString())
                    }

                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    private fun getTipOfDay() {
        if (Utility.isNetworkConnected(context)){
            isLoading.postValue(true)
            Networking.with(context)
                .getServices()
                .getTipOfTheDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CallbackObserver<GetTipOfTheDayResponse>() {
                    override fun onSuccess(response: GetTipOfTheDayResponse) {
                        isLoading.postValue(false)
                    }

                    override fun onFailed(code: Int, message: String) {
                        isLoading.postValue(false)
                        Log.e("Status",code.toString())

                    }

                    override fun onNext(getTipOfTheDayResponse: GetTipOfTheDayResponse) {
                        isLoading.postValue(false)
                        Log.e("Status",getTipOfTheDayResponse.getSuccess().toString())
                        if(getTipOfTheDayResponse.getSuccess() == true){
                            if(getTipOfTheDayResponse.getData() != null){
                                setTipOfTheDayData(getTipOfTheDayResponse)
                            }
                        }else{
                            Utils().showToast(context,getTipOfTheDayResponse.getMessage().toString())
                        }
                        Log.e("StatusCode",getTipOfTheDayResponse.getSuccess().toString())
                    }



                })
        }else{
            Utils().showToast(context, context.getString(R.string.nointernetconnection))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTipOfTheDayData(getTipOfTheDayResponse: GetTipOfTheDayResponse) {
        binding.txtTips.text =  context.resources.getString(R.string.double_quote)+ Utility.getNullToBlankString(getTipOfTheDayResponse.getData()?.getTipMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getTipOfTheDayResponse.getData()?.getTipImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }

    @SuppressLint("SetTextI18n")
    private fun setDailyGoalData(getDailyGoalResponse: GetDailyGoalResponse) {
        binding.txtTips.text =  context.resources.getString(R.string.double_quote)+ Utility.getNullToBlankString(getDailyGoalResponse.getData()?.getGoalMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getDailyGoalResponse.getData()?.getGoalImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }

    @SuppressLint("SetTextI18n")
    private fun setDailyQuoteData(getDailyQuoteResponse: GetDailyQuoteResponse) {
        binding.txtTips.text =  context.resources.getString(R.string.double_quote)+Utility.getNullToBlankString(getDailyQuoteResponse.getData()?.getQuoteMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getDailyQuoteResponse.getData()?.getQuoteImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }
    @SuppressLint("SetTextI18n")
    private fun setDailyReflectionQuestionData(getDailyReflectionResponse: GetDailyReflectionResponse) {
        binding.txtTips.text = context.resources.getString(R.string.double_quote)+ Utility.getNullToBlankString(getDailyReflectionResponse.getData()?.getQuestion().toString())+ context.resources.getString(R.string.double_quote)
     //   Glide.with(context).load(getDailyReflectionResponse.getData()?.get()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }
    private fun setSelfCareTipData(getSelfCareTipData: GetSelfCareTipResponse) {
        binding.txtTips.text = context.resources.getString(R.string.double_quote)+ Utility.getNullToBlankString(getSelfCareTipData.getData()?.getTipMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getSelfCareTipData.getData()?.getTipImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }

    private fun addSleepData() {
        sleepDataList.add(SetSelectedSleepData(1,"Sleep","", R.drawable.icon_number_one))
        sleepDataList.add(SetSelectedSleepData(2,"Gratitude","", R.drawable.icon_number_two))
        sleepDataList.add(SetSelectedSleepData(3,"Mood","", R.drawable.icon_number_three))


        sleepDataList.add(SetSelectedSleepData(4,"Sleep","", R.drawable.icon_number_four))
        sleepDataList.add(SetSelectedSleepData(5,"Gratitude","", R.drawable.icon_number_five))
        sleepDataList.add(SetSelectedSleepData(6,"Mood","", R.drawable.icon_number_six))

        binding.rvMoodDetector.setLayoutManager(GridLayoutManager(context, 3))
        var selectedSleepData = SleepSelectorAdapter(context, sleepDataList,selectedSleepTypeId,object :
            OnItemSelected<SetSelectedSleepData> {

            override fun onItemSelected(t: SetSelectedSleepData?, position: Int) {
                //  clickMenuEvent(t)
            }
        })

        binding.rvMoodDetector.adapter = selectedSleepData

        selectedSleepTypeId.observeForever {
            selectedSleepData.notifyDataSetChanged()
        }
    }

    private fun addWorkoutData() {
        workoutDataList.add(SetSelectedWorkoutData(1,"Sleep","", R.drawable.icon_workout_one))
        workoutDataList.add(SetSelectedWorkoutData(2,"Gratitude","", R.drawable.icon_workout_two))
        workoutDataList.add(SetSelectedWorkoutData(3,"Mood","", R.drawable.icon_workout_three))


        workoutDataList.add(SetSelectedWorkoutData(4,"Sleep","", R.drawable.icon_workout_four))
        workoutDataList.add(SetSelectedWorkoutData(5,"Gratitude","", R.drawable.icon_workout_five))
        workoutDataList.add(SetSelectedWorkoutData(6,"Mood","", R.drawable.icon_workout_six))

        binding.rvMoodDetector.setLayoutManager(GridLayoutManager(context, 3))
        var workoutSelectorAdapter = WorkoutSelectorAdapter(context, workoutDataList,selectedWorkoutTypeId,this,object :
            OnItemSelected<SetSelectedWorkoutData> {

            override fun onItemSelected(t: SetSelectedWorkoutData?, position: Int) {
                //  clickMenuEvent(t)
            }
        })

        binding.rvMoodDetector.adapter = workoutSelectorAdapter

        selectedWorkoutTypeId.observeForever {
            workoutSelectorAdapter.notifyDataSetChanged()
        }
    }

    private fun addMoodData() {
        moodDataList.add(SetSelectedMoodData(1,"MoodSelector","", R.drawable.icon_mood_angel,false))
        moodDataList.add(SetSelectedMoodData(2,"MoodSelector","", R.drawable.icon_mood_angry,false))
        moodDataList.add(SetSelectedMoodData(3,"MoodSelector","", R.drawable.icon_mood_sad,false))


        moodDataList.add(SetSelectedMoodData(4,"MoodSelector","", R.drawable.icon_mood_smart,false))
        moodDataList.add(SetSelectedMoodData(5,"MoodSelector","", R.drawable.icon_mood_sleep,false))
        moodDataList.add(SetSelectedMoodData(6,"MoodSelector","", R.drawable.icon_mood_smart,false))

        binding.rvMoodDetector.setLayoutManager(GridLayoutManager(context, 3))
        var moodSelectorAdapter = MoodSelectorAdapter(context, moodDataList,selectedMoodTypeId,this,true,object :
            OnItemSelected<SetSelectedMoodData> {

            override fun onItemSelected(t: SetSelectedMoodData?, position: Int) {
                //  clickMenuEvent(t)
            }
        })

        binding.rvMoodDetector.adapter = moodSelectorAdapter

        selectedMoodTypeId.observeForever {
            moodSelectorAdapter.notifyDataSetChanged()
        }
    }

    private fun addWeekData() {
        weekDayList.add(ModelDataWeek("S",false,1))
        weekDayList.add(ModelDataWeek("M",true,2))
        weekDayList.add(ModelDataWeek("T",false,3))
        weekDayList.add(ModelDataWeek("W",false,4))
        weekDayList.add(ModelDataWeek("T",false,5))
        weekDayList.add(ModelDataWeek("F",false,6))
        weekDayList.add(ModelDataWeek("S",false,7))

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvWeekDays.layoutManager = layoutManager

        binding.rvWeekDays.adapter = WeekAdapter(context, weekDayList,object :
            OnItemSelected<ModelDataWeek> {

            override fun onItemSelected(t: ModelDataWeek?, position: Int) {
                //  clickMenuEvent(t)
            }

        })
    }

}