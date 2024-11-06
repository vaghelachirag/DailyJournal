package com.app.dailyjounral.network
import com.app.dailyjounral.model.getDailyQuoteResponse.GetDailyQuoteResponse
import com.app.dailyjounral.model.getForgotPasswordResponse.GetForgotPasswordResponse
import com.app.dailyjounral.model.getLoginResponse.GetLoginResponse
import com.app.dailyjounral.model.getRegisterResponse.GetRegisterUserResponse
import com.app.dailyjounral.model.getSelfCareTipResponse.GetSelfCareTipResponse
import com.app.dailyjounral.model.getTipOfTheDayResponse.GetTipOfTheDayResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


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
    fun getChangePasswordResponse(@Body requestBody: RequestBody): Observable<GetForgotPasswordResponse>

    @POST("app/account/register")
    fun getRegisterUserResponse(@Body requestBody: RequestBody): Observable<GetRegisterUserResponse>

}