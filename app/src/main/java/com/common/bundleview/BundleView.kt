package com.common.bundleview

/**
 * Created by Kirill Stoianov on 21/02/2019.
 */
interface BundleView {

    fun setData()

    fun showTooltip()
    fun hideTooltip()

    fun setChoose(choose: Boolean)

    class Builder{}
}
