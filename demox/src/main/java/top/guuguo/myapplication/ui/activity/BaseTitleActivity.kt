package top.guuguo.myapplication.ui.activity

import android.support.v7.widget.Toolbar
import com.guuguo.android.lib.app.LBaseActivitySupport
import top.guuguo.myapplication.R

class BaseTitleActivity : LBaseActivitySupport() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_base_title
    }

    override fun getToolBar(): Toolbar= findViewById(R.id.id_tool_bar)

    override fun getHeaderTitle(): String {
        return "simple view"
    }
}
