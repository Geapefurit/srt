package cc.auuo.child.activity

import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.SimpleAdapter
import cc.auuo.child.R
import cc.auuo.child.util.fullScreen
import cc.auuo.child.util.setPressStyle
import kotlinx.android.synthetic.main.activity_count_game.*
import org.jetbrains.anko.toast
import java.util.*

class CountGameActivity : AppCompatActivity() {

    private val random = Random()
    private val handler = Handler()
    private var answer = 0
    private var marked = BooleanArray(0)
    private var numMarked = 0

    private lateinit var soundPool: SoundPool
    private lateinit var numberAndIdMapping: Map<Int, Int>

    companion object {
        private val DRAWABLE_ID = arrayOf(R.drawable.bear, R.drawable.bicycle, R.drawable.bug, R.drawable.butterfly,
                R.drawable.dog, R.drawable.grape, R.drawable.guitar, R.drawable.mango, R.drawable.umbrella)

        private const val DELAY = 2300L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        fullScreen()

        initSound()
        initListener()
        initTouch()
        initData()
    }
    
    private fun initSound() {
        val builder = SoundPool.Builder()
        soundPool = builder.setMaxStreams(3).build()

        numberAndIdMapping = mapOf(1 to soundPool.load(applicationContext, R.raw.one, 1),
                2 to soundPool.load(applicationContext, R.raw.two, 1),
                3 to soundPool.load(applicationContext, R.raw.three, 1),
                4 to soundPool.load(applicationContext, R.raw.four, 1),
                5 to soundPool.load(applicationContext, R.raw.five, 1),
                6 to soundPool.load(applicationContext, R.raw.six, 1),
                7 to soundPool.load(applicationContext, R.raw.seven, 1),
                8 to soundPool.load(applicationContext, R.raw.eight, 1),
                9 to soundPool.load(applicationContext, R.raw.nine, 1),
                10 to soundPool.load(applicationContext, R.raw.ten, 1))
    }

    private fun initData() {
        numMarked = 0
        getQuestion { answer, resId ->
            this.answer = answer
            marked = BooleanArray(answer)
            setGrid(answer, resId)
            setAnswerButton(answer)
        }
    }

    private fun getQuestion(callback: (Int, Int) -> Unit) {
        // 以后可能从网络获取
        val target = random.nextInt(10) + 1
        val resId = DRAWABLE_ID[random.nextInt(DRAWABLE_ID.size)]

        callback(target, resId)
    }

    private fun setGrid(count: Int, resId: Int) {
        gridView.numColumns = count
        if (count > 3) {
            gridView.numColumns = if (count % 2 == 0) count / 2 else count / 2 + 1
        }
        val data = ArrayList<Map<String, Any>>()
        for (i in 0 until count) {
            val map = HashMap<String, Any>()
            map["img"] = resId
            data.add(map)
        }
        val adapter = SimpleAdapter(this, data, R.layout.count_game_item, arrayOf("img"), intArrayOf(R.id.countGameItemImg))
        gridView.adapter = adapter
    }

    private fun setAnswerButton(answer: Int) {
        val answers = IntArray(3)
        val nums = (1..10).filter { it != answer }.toIntArray().shuffle()
        answers[0] = answer
        answers[1] = nums[0]
        answers[2] = nums[1]

        answers.shuffle()

        firstAnswerButton.text = answers[0].toString()
        secondAnswerButton.text = answers[1].toString()
        thirdAnswerButton.text = answers[2].toString()
    }

    private fun initListener() {
        closeButton.setOnClickListener {
            this.finish()
        }

        firstAnswerButton.setOnClickListener(::verifyAnswer)
        secondAnswerButton.setOnClickListener(::verifyAnswer)
        thirdAnswerButton.setOnClickListener(::verifyAnswer)

        gridView.setOnItemClickListener { _, view, _, id ->
            if (marked[id.toInt()]) {
                return@setOnItemClickListener
            }
            runOnUiThread {
                marked[id.toInt()] = true
                numMarked += 1
                playNumberSound(numMarked)
                view.alpha = 0.35F
            }
        }
    }

    private fun playNumberSound(num: Int) {
        if (num < 1 || num > 10)
            return
        soundPool.play(numberAndIdMapping[num]!!, 1F, 1F, 0, 0, 1F)
    }

    private fun verifyAnswer(view: View) {
        if (this.answer == 0)
            return

        val buttonText = (view as Button).text
        if (buttonText == answer.toString()) {
            this.answer = 0
            toast("答案正确")
            reloadDataLater()
        } else {
            toast("答案错误")
        }
    }

    private fun reloadDataLater() {
        handler.postDelayed({
            initData()
        }, DELAY)
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

    private fun IntArray.shuffle(): IntArray {
        for (i in 0 until this.size) {
            val randomIndex = random.nextInt(this.size)
            val t = this[i]
            this[i] = this[randomIndex]
            this[randomIndex] = t
        }
        return this
    }
}
