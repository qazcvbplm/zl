package com.dingdian.order.ui.goods

import android.arch.lifecycle.Observer
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.dingdian.order.R
import com.dingdian.order.bean.GoodsAttr
import com.dingdian.order.bean.GoodsBean
import com.dingdian.order.comm.ArouterPath
import com.dingdian.order.comm.LocalUser
import com.dingdian.order.databinding.ActivityEditBinding
import com.dingdian.order.event.RemoveAttrEvent
import com.dingdian.order.imageUrl
import com.dingdian.order.ui.base.MVVMActivity
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_edit.*
import org.greenrobot.eventbus.Subscribe

@Route(path = ArouterPath.edit)
class EditActivity : MVVMActivity<EditViewModel, ActivityEditBinding>() {

    companion object {
        const val GOODS_KEY = "goods_key"
        const val BUNDLE_KEY = "bundle_key"
        const val CATEGORYID_KEY = "category_id_key"
    }

    val adapter by lazy { AttrListAdapter(this) }

    var goodsBean: GoodsBean? = null

    var categoryId = ""

    override fun createViewModel(): EditViewModel = getViewModel(EditViewModel::class.java)

    override fun getLayout(): Int = R.layout.activity_edit

    override fun enableImmersionBar(): Boolean = true

    override fun initImmersionBar(immersionBar: ImmersionBar?): ImmersionBar? {
        return immersionBar?.statusBarColor(R.color.defaultWhite)
                ?.statusBarDarkFont(true)
    }

    override fun initView() {
        super.initView()
        mBinding?.vm = mViewModel
        toolbar.setNavigationOnClickListener { finish() }
        val bundle = intent.getBundleExtra(BUNDLE_KEY)

        listView.adapter = adapter
        if (bundle != null && bundle[GOODS_KEY] != null) {
            goodsBean = bundle[GOODS_KEY] as GoodsBean
            goodsName.setText(goodsBean?.productName)
            goodsImage.imageUrl(goodsBean?.productImage)
            discount.setText(goodsBean?.discount.toString())
            stock.setText(goodsBean?.stock)

            boxPriceSwitch.isChecked = goodsBean?.boxPriceFlag == 1
            showFlag.isChecked = goodsBean?.isShow == 1
            stockFlag.isChecked = goodsBean?.stockFlag == 1

            goodsBean?.attribute?.apply {
                adapter.list.addAll(this)
            }
        } else {
            categoryId = intent.getStringExtra(CATEGORYID_KEY)
        }

        add.setOnClickListener {
            val name = attrName.text.toString()
            val price = attrPrice.text.toString()

            if (name.isNullOrBlank() || price.isNullOrBlank()) {
                ToastUtils.showShort("请输入规格名称和价格")
            }


            try {
                val attr = GoodsAttr()
                attr.name = name
                attr.price = price.toDouble()

                if (goodsBean == null) {
                    //添加
                    adapter.list.add(0, attr)
                    adapter.notifyDataSetChanged()
                } else {
                    //更新
                    goodsBean?.let {
                        mViewModel?.addAttr(attr, it.id, adapter)
                    }
                }

            } catch (e: Exception) {
                ToastUtils.showShort("价格不合法")
            }


        }

        mViewModel?.addSuccessEvent?.observe(this, Observer {
            ToastUtils.showShort("保存成功")
            finish()
        })

        mViewModel?.updateSuccessEvent?.observe(this, Observer {
            ToastUtils.showShort("保存成功")
            finish()
        })

        saveBtn.setOnClickListener {
            val hashMap = LinkedHashMap<String, Any>()
            if (goodsBean == null) {
                //添加
                val productImage = "http://ops.sunwou.com/042e963f-856e-4de9-85b1-644344ff6d5b"
                val productName = goodsName.text.toString()
                val discount = discount.text.toString()
                val stock = stock.text.toString()
                val sale = 0
                val boxPriceFlag = if (boxPriceSwitch.isChecked) 1 else 0

                val productCategoryId = this.categoryId
                val shopId = LocalUser.getUser().value!!.id
                val schoolId = LocalUser.getUser().value!!.schoolId
                val isShow = if (showFlag.isChecked) 1 else 0
                val stockFlag = if (stockFlag.isChecked) 1 else 0

                var attributeName = ""
                var attributePrice = ""
                if (adapter.list.size > 0) {
                    for (i in 0 until adapter.list.size) {
                        attributeName += adapter.list[i].name
                        attributePrice += adapter.list[i].price
                        if (i != adapter.list.lastIndex) {
                            attributeName = "$attributeName,"
                            attributePrice = "$attributePrice,"
                        }
                    }
                }

                hashMap["productImage"] = productImage
                hashMap["productName"] = productName
                hashMap["discount"] = discount
                hashMap["stock"] = stock
                hashMap["sale"] = sale
                hashMap["boxPriceFlag"] = boxPriceFlag
                hashMap["productCategoryId"] = productCategoryId
                hashMap["shopId"] = shopId
                hashMap["schoolId"] = schoolId
                hashMap["isShow"] = isShow
                hashMap["stockFlag"] = stockFlag
                hashMap["attributeName"] = attributeName
                hashMap["attributePrice"] = attributePrice

                mViewModel?.addGoods(hashMap)
            } else {
                //更新
                // productImage；productName；sale；discount；boxPriceFlag（默认关闭）; productCategoryId; shopId; schoolId；isShow
                goodsBean?.let {
                    val productImage = it.productImage
                    val productName = goodsName.text.toString()
                    val discount = discount.text.toString()
                    val stock = stock.text.toString()
                    val sale = it.sale
                    val boxPriceFlag = if (boxPriceSwitch.isChecked) 1 else 0

                    val productCategoryId = it.productCategoryId
                    val shopId = it.shopId
                    val schoolId = it.schoolId
                    val isShow = if (showFlag.isChecked) 1 else 0
                    val stockFlag = if (stockFlag.isChecked) 1 else 0

                    var attributeName = ""
                    var attributePrice = ""
                    if (adapter.list.size > 0) {
                        for (i in 0 until adapter.list.size) {
                            attributeName += adapter.list[i].name
                            attributePrice += adapter.list[i].price
                            if (i != adapter.list.lastIndex) {
                                attributeName = "$attributeName,"
                                attributePrice = "$attributePrice,"
                            }
                        }
                    }

                    hashMap["productImage"] = productImage
                    hashMap["productName"] = productName
                    hashMap["discount"] = discount
                    hashMap["stock"] = stock
                    hashMap["sale"] = sale
                    hashMap["boxPriceFlag"] = boxPriceFlag
                    hashMap["productCategoryId"] = productCategoryId
                    hashMap["shopId"] = shopId
                    hashMap["schoolId"] = schoolId
                    hashMap["isShow"] = isShow
                    hashMap["stockFlag"] = stockFlag
                    hashMap["id"] = it.id
                    hashMap["attributeName"] = attributeName
                    hashMap["attributePrice"] = attributePrice
                    mViewModel?.updateGoods(hashMap)
                }
            }

        }

    }

    override fun initData() {

    }

    @Subscribe
    fun onEventRemoveAttr(event: RemoveAttrEvent) {
        if (goodsBean == null) {
            adapter.list.removeAt(event.position)
            adapter.notifyDataSetChanged()
        } else {
            mViewModel?.removeAttr(adapter.list[event.position].id, adapter, event.position)
        }
    }

}
