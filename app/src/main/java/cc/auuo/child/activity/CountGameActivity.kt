package cc.auuo.child.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cc.auuo.child.R
import cc.auuo.child.util.fullScreen
import cc.auuo.child.util.setPressStyle
import kotlinx.android.synthetic.main.activity_count_game.*

class CountGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        fullScreen()

        initListener()
        initTouch()
        initData()
    }

    private fun initData() {
        firstAnswerButton.text = "8"
        secondAnswerButton.text = "6"
        thirdAnswerButton.text = "7"
    }

    private fun initListener() {
        closeButton.setOnClickListener {
            this.finish()
        }
    }

    private fun initTouch() {
        closeButton.setOnTouchListener { v, event ->
            setPressStyle(v, event, R.drawable.close_button, R.drawable.close_button_press)
        }
        firstAnswerButton.setOnTouchListener { v, event ->
            setPressStyle(v, event, R.drawable.button_pink, R.drawable.button_pink_press)
        }
        secondAnswerButton.setOnTouchListener { v, event ->
            setPressStyle(v, event, R.drawable.button_green, R.drawable.button_green_press)
        }
        thirdAnswerButton.setOnTouchListener { v, event ->
            setPressStyle(v, event, R.drawable.button_blue, R.drawable.button_blue_press)
        }
    }
}
