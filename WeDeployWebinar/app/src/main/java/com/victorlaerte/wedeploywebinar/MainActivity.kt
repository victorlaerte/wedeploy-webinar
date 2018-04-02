package com.victorlaerte.wedeploywebinar

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.list_item_recycler_view)

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
    }

    fun refreshRecyclerView(recyclerView: RecyclerView, items: List<Item>) {
        if (recyclerView.adapter == null) {
            val linearLayoutManager = LinearLayoutManager(this)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = ItemAdapter(items)

        } else {
            (recyclerView.adapter as ItemAdapter).items = items
            recyclerView.adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val URL = "https://data-meetup.wedeploy.io"
    }
}
