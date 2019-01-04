package cc.auuo.child.util

import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by ashley on 2019/1/4
 */
fun AppCompatActivity.fullScreen() {
    supportActionBar?.hide()
    window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
}