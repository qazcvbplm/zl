package com.dingdian.order.ui.main.order

import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blankj.utilcode.util.SizeUtils
import com.dingdian.order.R
import com.dingdian.order.databinding.CustomTabItemBinding
import com.dingdian.order.databinding.OrderFragmentBinding
import com.dingdian.order.ui.base.MVVMFragment
import com.dingdian.order.ui.main.order.complete.CompleteFragment
import com.dingdian.order.ui.main.order.received.ReceivedFragment
import com.dingdian.order.ui.main.order.unreceive.UnReceiveFragment
import com.shizhefei.view.indicator.FixedIndicatorView
import com.shizhefei.view.indicator.IndicatorViewPager
import com.shizhefei.view.indicator.slidebar.ColorBar
import com.shizhefei.view.indicator.slidebar.TextWidthColorBar
import com.shizhefei.view.indicator.transition.OnTransitionTextListener
import kotlinx.android.synthetic.main.order_fragment.*

class OrderFragment : MVVMFragment<OrderViewModel, OrderFragmentBinding>() {

    lateinit var titles: Array<String>

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun createViewModel(): OrderViewModel = getViewModel(OrderViewModel::class.java)

    override fun getLayout(): Int = R.layout.order_fragment

    override fun initView() {
        super.initView()
        titles = resources.getStringArray(R.array.order_tab)

        val adapter = PagerAdapter(childFragmentManager)
        val indicatorViewPager = IndicatorViewPager(indicatorView, viewPager)
        indicatorViewPager.adapter = adapter
        indicatorView.splitMethod = FixedIndicatorView.SPLITMETHOD_WRAP
        val scrollBar = TextWidthColorBar(
                context, indicatorView, ContextCompat.getColor(context!!, R.color.colorPrimary),
                SizeUtils.applyDimension(3f, TypedValue.COMPLEX_UNIT_MM).toInt()
        )
        indicatorViewPager.setIndicatorScrollBar(scrollBar)
        indicatorViewPager.setIndicatorOnTransitionListener(
                OnTransitionTextListener().setSizeId(
                        context, R.dimen.size15mm, R.dimen.size13mm
                ).setColor(ContextCompat.getColor(context!!, R.color.colorPrimary), ContextCompat.getColor(context!!, R.color.darkgray))
        )

    }

    override fun initData() {

    }

    inner class PagerAdapter(fragmentManager: FragmentManager?) :
            IndicatorViewPager.IndicatorFragmentPagerAdapter(fragmentManager) {

        override fun getViewForTab(position: Int, convertView: View?, container: ViewGroup?): View {
            val binding = if (convertView == null)
                DataBindingUtil.inflate<CustomTabItemBinding>(
                        LayoutInflater.from(container!!.context),
                        R.layout.custom_tab_item, container,
                        false
                )
            else DataBindingUtil.bind(convertView)
            binding.title.text = titles[position]
            return binding.root
        }

        override fun getFragmentForPage(position: Int): Fragment {
            return when (position) {
                0 -> UnReceiveFragment.newInstance()
                1 -> ReceivedFragment.newInstance()
                else -> CompleteFragment.newInstance()
            }
        }

        override fun getCount(): Int = titles.size

    }

}
