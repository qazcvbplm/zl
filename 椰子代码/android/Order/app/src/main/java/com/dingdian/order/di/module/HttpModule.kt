package com.dingdian.order.di.module

import com.dingdian.order.api.ApiService
import com.dingdian.order.api.CommonQueryInterceptor
import com.dingdian.order.api.LogInterceptor
import com.dingdian.order.comm.HttpUrl.baseUrl
import com.dingdian.order.utils.HttpsUtils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class HttpModule @Inject constructor() {

    companion object {
        private const val DEFAULT_TIME_OUT = 15
    }

//    @Singleton
//    @Provides
//    fun provideCookieJar(context: Context): PersistentCookieJar {
//        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
//    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(LogInterceptor())
                .addInterceptor(CommonQueryInterceptor())
        //                .cookieJar(cookieJar)
        //信任所有服务器地址
        builder.hostnameVerifier { _, _ ->
            //设置为true
            true
        }

        if (HttpsUtils.SSLParams.sSLSocketFactory != null && HttpsUtils.SSLParams.trustManager != null) {
            builder.sslSocketFactory(HttpsUtils.SSLParams.sSLSocketFactory, HttpsUtils.SSLParams.trustManager)
        }

        return builder.build()

    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java!!)
    }


}