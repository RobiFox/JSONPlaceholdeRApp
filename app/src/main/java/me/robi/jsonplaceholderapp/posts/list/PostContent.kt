package me.robi.jsonplaceholderapp.posts.list

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
object PostContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PostItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PostItem> = HashMap()

    public fun clear() {
        ITEMS.clear()
        ITEM_MAP.clear()
    }

    public fun addItem(item: PostItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A post item representing a piece of content.
     */
    data class PostItem(val id: String, val content: String, val details: String, val authorId: Int) {
        override fun toString(): String = content
    }
}