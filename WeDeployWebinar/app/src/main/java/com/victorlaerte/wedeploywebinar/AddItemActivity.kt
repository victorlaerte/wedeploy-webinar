package com.victorlaerte.wedeploywebinar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout

/**
 * @author Victor Oliveira
 */
class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_activity)

        val container = findViewById<LinearLayout>(R.id.content)
    }
}