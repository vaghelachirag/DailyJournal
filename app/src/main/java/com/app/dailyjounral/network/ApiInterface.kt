package com.app.dailyjounral.network
import com.app.dailyjounral.model.getDailyGoalAnswerReponse.GetDailyGoalAnswerResponse
import com.app.dailyjounral.model.getDailyGoalResponse.GetDailyGoalResponse
import com.app.dailyjounral.model.getDailyQuoteResponse.GetDailyQuoteResponse
import com.app.dailyjounral.model.getDailyReflectionResponse.GetDailyReflectionResponse
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getGratitudeResponse.GetGratitudeListResponse
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getLogoutResponse.GetLogoutResponse
import com.app.dailyjounral.model.getMoodResponse.GetMoodDataResponse
import com.app.dailyjounral.model.getRegisterResponse.GetRegisterUserResponse
import com.app.dailyjounral.model.getSaveMoodDataResponse.GetSaveMoodDataResponse
import com.app.dailyjounral.model.getSelfCareTipResponse.GetSelfCareTipResponse
import com.app.dailyjounral.model.getSendOTPResponse.GetSendOTPResponse
import com.app.dailyjounral.model.getSleepDataResponse.GetSleepDataResponse
import com.app.dailyjounral.model.getSocialLoginResponse.GetSocialLoginResponse
import com.app.dailyjounral.model.getTipOfTheDayResponse.GetTipOfTheDayResponse
import com.app.dailyjounral.model.getUserProfileResponse.GetUserProfileResponse
import com.app.dailyjounral.model.getWorkoutDataResponse.GetWorkoutDataResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("app/account/logout")
    fun getLogoutResponse(@Header("Authorization") header: String?): Observable<GetLogoutResponse>

    @POST("app/mooduser/save")
    fun getSaveMoodDataResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetSaveMoodDataResponse>

    @POST("app/workout-user/save")
    fun getSaveWorkoutDataResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetSaveMoodDataResponse>

    @POST("app/sleep-user/save")
    fun getSaveSleepDataResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetSaveMoodDataResponse>


    @GET("app/daily-goal/today")
    fun getDailyGoalResponse(): Observable<GetDailyGoalResponse>


    @GET("app/daily-reflection/{date}")
    fun getDailyReflectionResponse(@Header("Authorization") header: String?,@Path("date") date : String): Observable<GetDailyReflectionResponse>


    @POST("app/workout-user/save")
    fun getSaveGoalAnswerResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetSaveMoodDataResponse>


    @PUT("app/user/update-profile")
    fun saveUpdateUserProfile(@Header("Authorization") header: String?,@Body body: RequestBody): Observable<GetUserProfileResponse>


    @POST("app/daily-reflection/save")
    fun getSaveDailyReflectionAnswerResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>



    @GET("app/daily-goal/{date}")
    fun getDailyGoalResponseData(@Header("Authorization") header: String?,@Path("date") date : String): Observable<GetDailyGoalAnswerResponse>


    @POST("app/daily-goal/save")
    fun getSaveDailyGoalAnswerResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>


    @POST("app/daily-goal/status-update")
    fun getSaveDailyPastGoalStatusResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>


    @GET("app/gratitude/list")
    fun getGratitudeListResponse(@Header("Authorization") header: String?): Observable<GetGratitudeListResponse>

    @GET("app/gratitude/list/{date}")
    fun getGratitudeListByDateResponse(@Header("Authorization") header: String?,@Path("date") date : String): Observable<GetGratitudeListResponse>

    @POST("app/gratitude/save")
    fun getSaveGratitudeResponse(@Header("Authorization") header: String?,@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>

    @DELETE("app/gratitude/{gratitudeUserRecordId}")
    fun getDeleteGratitudeResponse(@Header("Authorization") header: String?,@Path("gratitudeUserRecordId") gratitudeUserRecordId : Int): Observable<GetForgotPasswordResponse>

    @POST("app/account/social-login")
    fun getSocialLoginResponse(@Header("Authorization") header: String?,@Body reGetLoginResponsequestBody: RequestBody): Observable<GetLoginResponse>

}