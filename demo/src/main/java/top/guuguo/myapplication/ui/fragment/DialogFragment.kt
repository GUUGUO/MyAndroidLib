package top.guuguo.myapplication.ui.fragment

import android.app.Activity
import com.guuguo.android.dialog.utils.DialogSettings
import com.guuguo.android.lib.app.BaseCupertinoTitleActivity
import com.guuguo.android.lib.app.LBaseActivitySupport
import com.guuguo.android.lib.app.LBaseFragmentSupport
import kotlinx.android.synthetic.main.fragment_dialog.*
import top.guuguo.myapplication.R

class DialogFragment : LBaseFragmentSupport() {
    override fun getLayoutResId() = R.layout.fragment_dialog
    override fun getHeaderTitle() = "dialogFragment"
    override fun initView() {
        super.initView()
        btn_theme.setOnClickListener {
            if (DialogSettings.tip_theme == DialogSettings.THEME_LIGHT) {
                DialogSettings.tip_theme = DialogSettings.THEME_DARK
            } else {
                DialogSettings.tip_theme = DialogSettings.THEME_LIGHT
            }
        }
        btn_loading.setOnClickListener { dialogLoadingShow("加载中") }
        btn_error.setOnClickListener { dialogErrorShow("出错了") }
        btn_message.setOnClickListener { dialogMsgShow("天气很好", "知道了", null) }
//        btn_message.setOnClickListener {  TipDialog.show(activity,"可以了",TipDialog.STATE_STYLE.loading) }
        btn_warning.setOnClickListener {
            dialogWarningShow("确定继续吗", "取消", "确定")
        }
        btn_success.setOnClickListener { dialogCompleteShow("可以了") }
    }

    companion object {
        fun intentTo(activity: Activity) {
            LBaseActivitySupport.intentTo(activity, DialogFragment::class.java, BaseCupertinoTitleActivity::class.java)
        }
    }
}