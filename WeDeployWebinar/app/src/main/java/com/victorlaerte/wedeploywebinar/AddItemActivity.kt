package com.victorlaerte.wedeploywebinar

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.wedeploy.android.Callback
import com.wedeploy.android.WeDeploy
import com.wedeploy.android.transport.Response
import org.json.JSONObject
import java.lang.Exception

/**
 * @author Victor Oliveira
 */
class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_activity)
        weDeploy = WeDeploy.Builder().build()

        val container = findViewById<LinearLayout>(R.id.content)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            val name = nameEditText.text
            val description = descriptionEditText.text

            if (name.isNotEmpty()) {

                val itemJSON = JSONObject()

                itemJSON.put("name", name)
                itemJSON.put("description", description)

                weDeploy.data(MainActivity.URL)
                    .create("items", itemJSON)
                    .execute(object: Callback {
                        override fun onSuccess(response: Response?) {
                            Snackbar.make(container, "Item Added", Snackbar.LENGTH_LONG)
                                .show()
                        }

                        override fun onFailure(e: Exception?) {
                            e?.let {
                                Log.d("test", e.message)

                                Snackbar.make(container, e.message!!, Snackbar.LENGTH_LONG)
                                    .show()
                            }
                        }
                    })

            } else {

                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Please fill the name")
                    .setNeutralButton("OK", {
                        dialog, which ->
                    })
                    .show()
            }
        }
    }

    lateinit var weDeploy: WeDeploy
}