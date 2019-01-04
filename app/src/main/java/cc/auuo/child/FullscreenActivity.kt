package cc.auuo.child

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cc.auuo.child.R.layout.activity_fullscreen
import cc.auuo.child.util.fullScreen

class FullscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_fullscreen)

        fullScreen()
    }
}