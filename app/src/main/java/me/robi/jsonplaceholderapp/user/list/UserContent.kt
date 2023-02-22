package me.robi.jsonplaceholderapp.user.list

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object UserContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<UserItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<Int, UserItem> = HashMap()

    fun addItem(item: UserItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createItem(position: Int): UserItem {
        return UserItem(position, "Item " + position)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class UserItem(val id: Int, val name: String) {
        override fun toString(): String = name
    }
}