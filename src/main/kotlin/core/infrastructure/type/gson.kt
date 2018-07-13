package core.infrastructure.type

import com.google.gson.GsonBuilder

/**
 * @author Clément Garbay
 */
object GsonSerializer {

    val gson = GsonBuilder().setPrettyPrinting().create()!!

    inline fun <reified T> toJson(src: T) = gson.toJson(src)
    inline fun <reified T> fromJson(json: String) = gson.fromJson(json, T::class.java)
}