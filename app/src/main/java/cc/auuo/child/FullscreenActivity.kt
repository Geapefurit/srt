package cc.auuo.child

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import cc.auuo.child.R.layout.activity_fullscreen
import kotlinx.android.synthetic.main.activity_fullscreen.*

class FullscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_fullscreen)

        hide()
    }

    /**
     * 全屏
     */
    private fun hide() {
        supportActionBar?.hide()
        window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}
