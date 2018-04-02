package com.victorlaerte.wedeploywebinar

import org.json.JSONArray


/**
 * @author Victor Oliveira
 */
class Parser {

    companion object {
        fun parse(body: String): List<Item> {

            val jsonArray = JSONArray(body)
            val items = mutableListOf<Item>()

            for (index in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(index)

                val id = json.getString("id")
                val name = if (json.has("name")) json.getString("name") else ""
                val description = if (json.has("description")) json.getString("description") else ""

                items.add(Item(id, name, description))
            }

            return items
        }
    }
}