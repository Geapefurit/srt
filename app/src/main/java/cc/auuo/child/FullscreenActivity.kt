package cc.auuo.child

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cc.auuo.child.R.layout.activity_fullscreen
import cc.auuo.child.activity.CountGameActivity
import cc.auuo.child.util.fullScreen
import kotlinx.android.synthetic.main.activity_fullscreen.*
import org.jetbrains.anko.startActivity

class FullscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_fullscreen)

        fullScreen()
        initListener()
    }

    private fun initListener() {
        countGameButton.setOnClickListener {
            startActivity<CountGameActivity>()
        }
    }
}