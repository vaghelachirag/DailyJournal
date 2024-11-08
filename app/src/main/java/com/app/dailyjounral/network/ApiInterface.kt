package com.app.dailyjounral.network
import com.app.dailyjounral.model.getDailyQuoteResponse.GetDailyQuoteResponse
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getMoodResponse.GetMoodDataResponse
import com.app.dailyjounral.model.getRegisterResponse.GetRegisterUserResponse
import com.app.dailyjounral.model.getSelfCareTipResponse.GetSelfCareTipResponse
import com.app.dailyjounral.model.getSendOTPResponse.GetSendOTPResponse
import com.app.dailyjounral.model.getSleepDataResponse.GetSleepDataResponse
import com.app.dailyjounral.model.getTipOfTheDayResponse.GetTipOfTheDayResponse
import com.app.dailyjounral.model.getUserProfileResponse.GetUserProfileResponse
import com.app.dailyjounral.model.getWorkoutDataResponse.GetWorkoutDataResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("app/daily-tip/today")
    fun getTipOfTheDay(): Observable<GetTipOfTheDayResponse>

    @GET("app/daily-quote/today")
    fun getDailyQuote(): Observable<GetDailyQuoteResponse>

    @GET("app/daily-selfcaretip/today")
    fun getSelfCareTip(): Observable<GetSelfCareTipResponse>


    @POST("app/account/login")
    fun getLoginResponse(@Body requestBody: RequestBody): Observable<GetLoginResponse>

    @POST("app/account/forget-password")
    fun getForgotPasswordResponse(@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>


    @POST("app/account/verification-code")
    fun getVerificationCodeResponse(@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>


    @POST("app/account/reset-password")
    fun getResetPasswordResponse(@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>

    @POST("app/account/change-password")
    fun getChangePasswordResponse( @Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>

    @POST("app/account/register")
    fun getRegisterUserResponse(@Body requestBody: RequestBody): Observable<GetRegisterUserResponse>


    @GET("app/user/{Id}")
    fun getUserProfileResponse(@Header("Authorization") header: String?,@Path("Id") emailId: String): Observable<GetUserProfileResponse>


    @POST("app/account/register-send-verification-code/{emailId}")
    fun getSendOTPToEmail(@Path("emailId") emailId: String): Observable<GetSendOTPResponse>


    @GET("app/mooduser/{date}")
    fun getUserMoodResponse(@Header("Authorization") header: String?,@Path("date") date : String): Observable<GetMoodDataResponse>

    @GET("app/sleep-user/{date}")
    fun getUserSleepResponse(@Header("Authorization") header: String?,@Path("date") date : String): Observable<GetSleepDataResponse>

    @GET("app/workout-user/{date}")
    fun getUserWorkoutResponse(@Header("Authorization") header: String?,@Path("date") date : String): Observable<GetWorkoutDataResponse>

}