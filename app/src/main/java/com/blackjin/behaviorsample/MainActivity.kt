package com.blackjin.behaviorsample

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val sampleAdapter by lazy { SampleAdapter() }

    private val getYMax by lazy {
        resources.getDimension(R.dimen.appbar_height) - resources.getDimension(
            R.dimen.toolbar_height
        )
    }

    private val fadeIn by lazy { AnimationUtils.loadAnimation(this, R.anim.fade_in) }

    private val fadeOut by lazy { AnimationUtils.loadAnimation(this, R.anim.fade_out) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTopBarAnimation()
        initRecyclerView()
        loadSample()
    }

    private fun initTopBarAnimation() {
        val appBarLayout = findViewById<AppBarLayout>(R.id.app_bar_layout)

        val back = findViewById<ImageView>(R.id.iv_back)
        val del = findViewById<ImageView>(R.id.iv_del)
        val share = findViewById<ImageView>(R.id.iv_share)

        appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                if (abs(verticalOffset) >= getYMax / 2) {
                    if (back.visibility != View.GONE) {
                        back.startAnimation(fadeOut)
                        del.startAnimation(fadeOut)
                        share.startAnimation(fadeOut)

                        back.visibility = View.GONE
                        del.visibility = View.GONE
                        share.visibility = View.GONE
                    }
                } else {
                    if (back.visibility != View.VISIBLE) {
                        back.startAnimation(fadeIn)
                        del.startAnimation(fadeIn)
                        share.startAnimation(fadeIn)

                        back.visibility = View.VISIBLE
                        del.visibility = View.VISIBLE
                        share.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun initRecyclerView() {
        with(findViewById<RecyclerView>(R.id.rv_main)) {
            adapter = sampleAdapter
        }
    }

    private fun loadSample() {
        sampleAdapter.notifySample()
    }
}