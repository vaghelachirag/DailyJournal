package com.app.dailyjounral.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.dailyjounral.R
import com.app.dailyjounral.adapter.MoodSelectorAdapter
import com.app.dailyjounral.adapter.WeekAdapter
import com.app.dailyjounral.databinding.DetailActivityBinding
import com.app.dailyjounral.interfaces.OnItemSelected
import com.app.dailyjounral.model.ModelDataWeek
import com.app.dailyjounral.model.MoodDataModel
import com.app.dailyjounral.model.getDailyQuoteResponse.GetDailyQuoteResponse
import com.app.dailyjounral.model.getSelfCareTipResponse.GetSelfCareTipResponse
import com.app.dailyjounral.model.getTipOfTheDayResponse.GetTipOfTheDayResponse
import com.app.dailyjounral.network.CallbackObserver
import com.app.dailyjounral.network.Networking
import com.app.dailyjounral.uttils.AppConstants
import com.app.dailyjounral.uttils.Utility
import com.app.dailyjounral.uttils.Utils
import com.app.dailyjounral.model.base.BaseViewModel
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailViewModel(val context: Context, val binding: DetailActivityBinding) : BaseViewModel()  {

    private var weekDayList = mutableListOf<ModelDataWeek>()
    private var moodDataList = mutableListOf<MoodDataModel>()

    fun init() {

        Log.e("WeekDay",Utils().getCurrentWeekDay().toString())
        Log.e("DetailType",AppConstants.detailType.toString())

        if (AppConstants.detailType == 1){
            getTipOfDay()
        }
        if (AppConstants.detailType == 2){
            getDailyQuote()
        }

        if (AppConstants.detailType == 4){
            addSleepData()
        }
        if (AppConstants.detailType == 5){
          //  addWorkoutData()
        }

        if (AppConstants.detailType == 6){
          addMoodData()
        }

        if (AppConstants.detailType == 8){
            addWorkoutData()
        }
        if (AppConstants.detailType == 9){
            getSelfCareTip()
        }
       addWeekData()

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

    private fun setTipOfTheDayData(getTipOfTheDayResponse: GetTipOfTheDayResponse) {
        binding.txtTips.text =  context.resources.getString(R.string.double_quote)+ Utility.getNullToBlankString(getTipOfTheDayResponse.getData()?.getTipMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getTipOfTheDayResponse.getData()?.getTipImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }

    @SuppressLint("SetTextI18n")
    private fun setDailyQuoteData(getDailyQuoteResponse: GetDailyQuoteResponse) {
        binding.txtTips.text =  context.resources.getString(R.string.double_quote)+Utility.getNullToBlankString(getDailyQuoteResponse.getData()?.getQuoteMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getDailyQuoteResponse.getData()?.getQuoteImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }

    private fun setSelfCareTipData(getSelfCareTipData: GetSelfCareTipResponse) {
        binding.txtTips.text = context.resources.getString(R.string.double_quote)+ Utility.getNullToBlankString(getSelfCareTipData.getData()?.getTipMessage().toString())+ context.resources.getString(R.string.double_quote)
        Glide.with(context).load(getSelfCareTipData.getData()?.getTipImage()).apply(Utility.getGlideRequestOption()).into(binding.ivImage)
    }

    private fun addSleepData() {
        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_number_one))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_number_two))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_number_three))


        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_number_four))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_number_five))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_number_six))
    }

    private fun addWorkoutData() {
        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_workout_one))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_workout_two))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_workout_three))


        moodDataList.add(MoodDataModel("Sleep","", R.drawable.icon_workout_four))
        moodDataList.add(MoodDataModel("Gratitude","", R.drawable.icon_workout_five))
        moodDataList.add(MoodDataModel("Mood","", R.drawable.icon_workout_six))
    }

    private fun addMoodData() {
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_mood_angel))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_mood_angry))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_mood_sad))


        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_mood_smart))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_mood_sleep))
        moodDataList.add(MoodDataModel("MoodSelector","", R.drawable.icon_mood_smart))

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



        binding.rvMoodDetector.setLayoutManager(GridLayoutManager(context, 3))

        binding.rvMoodDetector.adapter = MoodSelectorAdapter(context, moodDataList,object :
            OnItemSelected<MoodDataModel> {

            override fun onItemSelected(t: MoodDataModel?, position: Int) {
                //  clickMenuEvent(t)
            }

        })

    }

}