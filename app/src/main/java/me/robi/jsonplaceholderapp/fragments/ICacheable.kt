package me.robi.jsonplaceholderapp.fragments

/**
 * Stores a cache when the same URL is fetched.
 */
interface ICacheable {
    fun reFetchIfCached(): Boolean;
}