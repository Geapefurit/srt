package cc.auuo.child.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cc.auuo.child.R
import cc.auuo.child.util.fullScreen
import kotlinx.android.synthetic.main.activity_count_game.*

class CountGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        fullScreen()
        initListener()
    }

    private fun initListener() {
        closeButton.setOnClickListener {
            this.finish()
        }
    }
}
