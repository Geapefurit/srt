package cc.auuo.child.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cc.auuo.child.R
import cc.auuo.child.util.fullScreen

class CountGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_game)

        fullScreen()
    }
}
