package com.guuguo.android.lib.view.simpleview

import am.drawable.DoubleCircleDrawable
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.flyco.roundview.RoundTextView
import com.guuguo.android.R

//import com.wang.avi.AVLoadingIndicatorView

/**
 * Created by Barry on 16/5/28.
 */
class SimpleView {

    private var text: String = ""
    private var hint: String = ""
    private var mOnClickListener: View.OnClickListener? = null
    private var mBtnText: String = ""
    private var isIconShow = true
    private var mIconRes = R.drawable.empty_cute_girl_box
    private var isWrapContent = true
    private var mStyle: Int = STYLE.normal

    private val mLLLayout: LinearLayout
    private val mImg: ImageView
    private val mTvText: TextView
    private val mTvHint: TextView
    private val mBtn: RoundTextView
//    private val mLoadingVIew: AVLoadingIndicatorView

    val view: View

    constructor(context: Context) : this(context, null)

    private val drawable: DoubleCircleDrawable

    constructor(context: Context, viewGroup: ViewGroup?) {
        view = View.inflate(context, R.layout.simple_empty_view, viewGroup)
        mLLLayout = view.findViewById(R.id.layoutEmpty) as LinearLayout
        mTvText = view.findViewById(R.id.tv_text) as TextView
        mTvHint = view.findViewById(R.id.tv_hint) as TextView
        mImg = view.findViewById(R.id.img) as ImageView
        mBtn = view.findViewById(R.id.btn_empty) as RoundTextView
        view.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(p0: View?) {
                setUIBeforeShow()
            }

            override fun onViewDetachedFromWindow(p0: View?) {
            }

        })
        drawable = DoubleCircleDrawable(context.resources.getDisplayMetrics().density)
    }

    object STYLE {
        val loading = 0
        val normal = 1
    }

    init {

    }

    fun style(style: Int): SimpleView {
        this.mStyle = style
        return this
    }


    fun text(text: String): SimpleView {
        this.text = text
        return this
    }

    fun hint(text: String): SimpleView {
        this.hint = text
        return this
    }

    fun btnText(text: String): SimpleView {
        this.mBtnText = text
        return this
    }

    fun btnListener(listener: View.OnClickListener?): SimpleView {
        this.mOnClickListener = listener
        return this
    }

    fun icon(iconRes: Int): SimpleView {
        this.mIconRes = iconRes
        return this
    }

    fun setIconShow(isIconShow: Boolean): SimpleView {
        this.isIconShow = isIconShow
        return this
    }

    fun setWrapContent(isWrapContent: Boolean): SimpleView {
        this.isWrapContent = isWrapContent
        return this
    }

    private fun setUIBeforeShow() {
        /**wrap_content */
        if (!isWrapContent) {
            val params = mLLLayout.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            mLLLayout.layoutParams = params
        } else {
            val params = mLLLayout.layoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            mLLLayout.layoutParams = params
        }

        mImg.visibility=View.VISIBLE
        /**loading */
        if (mStyle == STYLE.loading) {
            mImg.setImageDrawable(drawable)
            drawable.start()
        } else if (isIconShow) {
            /**icon */
            mImg.setImageResource(mIconRes)
        }else{
            mImg.visibility=View.GONE
        }

        /**button */
        if (TextUtils.isEmpty(mBtnText))
            mBtn.visibility = View.GONE
        else {
            mBtn.visibility = View.VISIBLE
            mBtn.text = mBtnText
            if (mOnClickListener != null)
                mBtn.setOnClickListener(mOnClickListener)
        }

        /**text */
        if (!TextUtils.isEmpty(text)) {
            mTvText.visibility = View.VISIBLE
            mTvText.text = text
        } else if (TextUtils.isEmpty(hint)) {
            hint = "没有数据"
        } else {
            mTvText.visibility = View.GONE
        }

        /**hint */
        if (!TextUtils.isEmpty(hint)) {
            mTvHint.visibility = View.VISIBLE
            mTvHint.text = hint
        } else {
            mTvHint.visibility = View.GONE
        }


    }
}