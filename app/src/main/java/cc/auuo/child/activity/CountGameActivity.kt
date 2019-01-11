package cc.auuo.child.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SimpleAdapter
import cc.auuo.child.R
import cc.auuo.child.util.fullScreen
import cc.auuo.child.util.setPressStyle
import kotlinx.android.synthetic.main.activity_count_game.*
import java.util.*

class CountGameActivity : AppCompatActivity() {

    private val random = Random()

    companion object {
        val drawableIds = arrayOf(R.drawable.bear, R.drawable.bicycle, R.drawable.bug, R.drawable.butterfly,
                R.drawable.dog, R.drawable.grape, R.drawable.guitar, R.drawable.mango, R.drawable.umbrella)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        fullScreen()

        initListener()
        initTouch()
        initData()
    }

    private fun initData() {
        // init gridView
        getQuestion { answer, resId ->
            gridView.numColumns = answer
            if (answer > 3) {
                gridView.numColumns = if (answer % 2 == 0) answer / 2 else answer / 2 + 1
            }
            val data = ArrayList<Map<String, Any>>()
            for (i in 0 until answer) {
                val map = HashMap<String, Any>()
                map["img"] = resId
                data.add(map)
            }
            val adapter = SimpleAdapter(this, data, R.layout.count_game_item, arrayOf("img"), intArrayOf(R.id.countGameItemImg))
            gridView.adapter = adapter

            // todo init answer button
            firstAnswerButton.text = "8"
            secondAnswerButton.text = "6"
            thirdAnswerButton.text = "7"
        }
    }

    private fun getQuestion(callback: (Int, Int) -> Unit) {
        // 以后可能从网络获取
        val target = random.nextInt(10) + 1
        val resId = drawableIds[random.nextInt(drawableIds.size)]
        callback(target, resId)
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
