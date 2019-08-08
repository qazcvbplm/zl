package com.dingdian.order.ui.base


import com.dingdian.order.api.ApiService
import com.dingdian.order.api.response.*
import com.dingdian.order.bean.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(apiService: ApiService) : BaseRepository(apiService) {

    fun login(name: String, password: String, callback: DataViewModel.NetworkCallback<UserTemp>) {
        callback.onStart()
        apiService.login(name, password).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun cancel(id: String, callback: DataViewModel.NetworkCallback<UserTemp>) {
        callback.onStart()
        apiService.cancel(id).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun tx(id: String, callback: DataViewModel.NetworkCallback<UserTemp>) {
        callback.onStart()
        apiService.tx(id).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun shopInfo(shopId: String, callback: DataViewModel.NetworkCallback<ShopList>) {
        callback.onStart()
        apiService.shopInfo(shopId).enqueue(object : Callback<ShopInfoResponse> {
            override fun onFailure(call: Call<ShopInfoResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<ShopInfoResponse>, response: Response<ShopInfoResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun shopStatistics(
            beginTime: String,
            endTime: String,
            shopId: String,
            callback: DataViewModel.NetworkCallback<ShopStatisticsBean>
    ) {
        callback.onStart()
        apiService.shopStatistics(beginTime, endTime, shopId).enqueue(object : Callback<ShopStatisticsResponse> {
            override fun onFailure(call: Call<ShopStatisticsResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<ShopStatisticsResponse>, response: Response<ShopStatisticsResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun commentList(page: Int, size: Int, shopId: String, callback: DataViewModel.NetworkCallback<CommentList>) {
        callback.onStart()
        apiService.commentList(shopId, page, size).enqueue(object : Callback<CommentResponse> {
            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun shopUpdate(map: HashMap<String, Any>, callback: DataViewModel.NetworkCallback<String>) {
        callback.onStart()
        apiService.shopUpdate(map).enqueue(object : Callback<StringResponse> {
            override fun onFailure(call: Call<StringResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<StringResponse>, response: Response<StringResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun goodTypeList(shopId: String, callback: DataViewModel.NetworkCallback<GoodTypeList>) {
        callback.onStart()
        apiService.goodTypeList(shopId).enqueue(object : Callback<GoodTypeResponse> {
            override fun onFailure(call: Call<GoodTypeResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<GoodTypeResponse>, response: Response<GoodTypeResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

    fun goodsList(productCategoryId: Int, callback: DataViewModel.NetworkCallback<GoodsList>) {
        callback.onStart()
        apiService.goodsList(productCategoryId).enqueue(object : Callback<GoodsListResponse> {
            override fun onFailure(call: Call<GoodsListResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<GoodsListResponse>, response: Response<GoodsListResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

    fun removeAttr(id: Int, callback: DataViewModel.NetworkCallback<String>) {
        callback.onStart()
        apiService.removeAttr(id).enqueue(object : Callback<StringResponse> {
            override fun onFailure(call: Call<StringResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<StringResponse>, response: Response<StringResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

    fun addAttr(
            attributeName: String,
            attributePrice: String,
            pid: Int,
            callback: DataViewModel.NetworkCallback<String>
    ) {
        callback.onStart()
        apiService.addAttr(attributeName, attributePrice, pid).enqueue(object : Callback<StringResponse> {
            override fun onFailure(call: Call<StringResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<StringResponse>, response: Response<StringResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

    fun updateGoods(map: HashMap<String, Any>, callback: DataViewModel.NetworkCallback<String>) {
        callback.onStart()
        apiService.updateGoods(map).enqueue(object : Callback<StringResponse> {
            override fun onFailure(call: Call<StringResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<StringResponse>, response: Response<StringResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun addGoods(map: HashMap<String, Any>, callback: DataViewModel.NetworkCallback<String>) {
        callback.onStart()
        apiService.addGoods(map).enqueue(object : Callback<StringResponse> {
            override fun onFailure(call: Call<StringResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<StringResponse>, response: Response<StringResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun delGoods(id: Int, isDelete: Int, callback: DataViewModel.NetworkCallback<String>) {
        callback.onStart()
        apiService.deleteGoods(id, isDelete).enqueue(object : Callback<StringResponse> {
            override fun onFailure(call: Call<StringResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<StringResponse>, response: Response<StringResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun unReceiveList(shopId: Int, callback: DataViewModel.NetworkCallback<OrderList>) {
        callback.onStart()
        apiService.unReceiveList(shopId).enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

    fun receiveOrder(orderId: String, callback: DataViewModel.NetworkCallback<MakeOrder>) {
        callback.onStart()
        apiService.receiveOrder(orderId).enqueue(object : Callback<ReceiveResponse> {
            override fun onFailure(call: Call<ReceiveResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<ReceiveResponse>, response: Response<ReceiveResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun receivedList(shopId: Int, page: Int, size: Int, callback: DataViewModel.NetworkCallback<OrderList>) {
        callback.onStart()
        apiService.receivedList(shopId, page, size).enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                callback.onResponse(call, response)
            }

        })

    }

    fun completeList(map: HashMap<String, Any>, callback: DataViewModel.NetworkCallback<CompleteList>) {
        callback.onStart()
        apiService.completeList(map).enqueue(object : Callback<CompleteResponse> {
            override fun onFailure(call: Call<CompleteResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<CompleteResponse>, response: Response<CompleteResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

    fun checkVersion(callback: DataViewModel.NetworkCallback<VersionBean>) {
        callback.onStart()
        apiService.checkVersion().enqueue(object : Callback<VersionResponse> {
            override fun onFailure(call: Call<VersionResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<VersionResponse>, response: Response<VersionResponse>) {
                callback.onResponse(call, response)
            }

        })
    }

}