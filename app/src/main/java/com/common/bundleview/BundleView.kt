package com.common.bundleview

/**
 * Created by Kirill Stoianov on 21/02/2019.
 */
interface BundleView {

    enum class Type { SIMPLE, SIMPLE_OLD_PRICE, DISCOUNT, FEATURE }

    fun setBundle(bundle: Any)
    fun setBundleType(type: Type)

    fun showTooltip()
    fun hideTooltip()
    fun animateTooltip()

    fun setChoose(choose: Boolean)
}