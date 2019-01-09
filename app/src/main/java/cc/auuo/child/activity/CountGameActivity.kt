package cc.auuo.child.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import cc.auuo.child.R
import cc.auuo.child.util.fullScreen
import kotlinx.android.synthetic.main.activity_count_game.*

class CountGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        fullScreen()
        initListener()
        initTouch()
    }

    private fun initListener() {
        closeButton.setOnClickListener {
            this.finish()
        }
    }

    private fun initTouch() {
        closeButton.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.setBackgroundResource(R.drawable.close_button_press)
            } else if (event.action == MotionEvent.ACTION_UP) {
                v.setBackgroundResource(R.drawable.close_button)
            }
            return@setOnTouchListener false
        }
    }
}
