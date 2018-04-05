package com.victorlaerte.wedeploywebinar

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
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
class AddItemActivity : AppCompatActivity(), Callback {

    override fun onSuccess(response: Response?) {
        Snackbar.make(container, "Item Saved", Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onFailure(e: Exception?) {
        e?.let {
            Snackbar.make(container, e.message!!, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_activity)

        weDeploy = WeDeploy.Builder().build()
        container = findViewById(R.id.content)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val addButton = findViewById<Button>(R.id.addButton)

        val parcelable = intent.extras?.getParcelable<Item>("item")

        parcelable?.let {
            itemId = it.id
            nameEditText.setText(it.name)
            descriptionEditText.setText(it.description)
        }

        addButton.setOnClickListener {
            saveItem(nameEditText, descriptionEditText)
        }
    }

    private fun saveItem(nameEditText: EditText, descriptionEditText: EditText) {
        val name = nameEditText.text
        val description = descriptionEditText.text

        if (name.isNotEmpty()) {

            val itemJSON = JSONObject()

            itemJSON.put("name", name)
            itemJSON.put("description", description)

            if (itemId.isEmpty()) {

                weDeploy.data(MainActivity.URL)
                    .create("items", itemJSON)
                    .execute(this)

            } else {

                weDeploy.data(MainActivity.URL)
                    .update("items/$itemId", itemJSON)
                    .execute(this)
            }

        } else {
            showError()
        }
    }

    private fun showError() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage("Please fill the name")
            .setNeutralButton("OK", { dialog, which ->
            })
            .show()
    }

    lateinit var container: LinearLayout
    lateinit var weDeploy: WeDeploy
    var itemId = ""
}