package com.dingdian.order.api

import com.dingdian.order.api.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("shop/android/login")
    fun login(@Field("loginName") name: String, @Field("loginPassWord") password: String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("orders/cancel")
    @Headers("Generic-Params-enabled:enable")
    fun cancel(@Field("id") id: String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("txlog/txapply")
    @Headers("Generic-Params-enabled:enable")
    fun tx(@Field("id") id: String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("shop/find")
    @Headers("Generic-Params-enabled:enable")
    fun shopInfo(@Field("id") shopId: String): Call<ShopInfoResponse>

    @FormUrlEncoded
    @POST("shop/nocheck/shopstatistics")
    @Headers("Generic-Params-enabled:enable")
    fun shopStatistics(@Field("beginTime") beginTime: String, @Field("endTime") endTime: String, @Field("shopId") shopId: String): Call<ShopStatisticsResponse>

    @FormUrlEncoded
    @POST("evaluate/findbyshopid")
    @Headers("Generic-Params-enabled:enable")
    fun commentList(@Field("shopId") shopId: String, @Field("page") page: Int, @Field("size") size: Int): Call<CommentResponse>

    @FormUrlEncoded
    @POST("shop/update")
    @Headers("Generic-Params-enabled:enable")
    fun shopUpdate(@FieldMap map: HashMap<String, Any>): Call<StringResponse>

    @FormUrlEncoded
    @POST("productcategory/find")
    @Headers("Generic-Params-enabled:enable")
    fun goodTypeList(@Field("shopId") shopId: String): Call<GoodTypeResponse>

    @FormUrlEncoded
    @POST("product/find")
    @Headers("Generic-Params-enabled:enable")
    fun goodsList(@Field("productCategoryId") productCategoryId: Int): Call<GoodsListResponse>

    @POST("product/removea")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun removeAttr(@Field("id") id: Int): Call<StringResponse>

    @POST("product/adda")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun addAttr(@Field("attributeName") attributeName: String, @Field("attributePrice") attributePrice: String, @Field("pid") pid: Int): Call<StringResponse>


    @POST("product/update")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun updateGoods(@FieldMap map: HashMap<String, Any>): Call<StringResponse>

    @POST("product/add")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun addGoods(@FieldMap map: HashMap<String, Any>): Call<StringResponse>

    @POST("orders/android/findDjs")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun unReceiveList(@Field("shopId") shopId: Int): Call<OrderResponse>

    @POST("orders/android/acceptorder")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun receiveOrder(@Field("orderId") orderId: String): Call<ReceiveResponse>

    @POST("orders/android/findordersyjs")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun receivedList(@Field("shopId") shopId: Int, @Field("page") page: Int, @Field("size") size: Int): Call<OrderResponse>

    @POST("orders/find")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun completeList(@FieldMap map: HashMap<String, Any>): Call<CompleteResponse>

    @POST("product/update")
    @FormUrlEncoded
    @Headers("Generic-Params-enabled:enable")
    fun deleteGoods(@Field("id") id: Int, @Field("isDelete") isDelete: Int): Call<StringResponse>

    @GET("application/appversion?platform=Android")
    @Headers("Generic-Params-enabled:enable")
    fun checkVersion(): Call<VersionResponse>


}