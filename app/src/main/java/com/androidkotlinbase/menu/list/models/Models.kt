package com.androidkotlinbase.menu.list.models

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize

/**
 * Created by nalen on 07/09/20.
 */
object Models {
    @Keep
    @Parcelize
    data class Response(val data: MutableList<Results>): Parcelable {
        class Deserializer: ResponseDeserializable<Response> {
            override fun deserialize(content: String): Response? {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val jsonAdapter = moshi.adapter(Response::class.java)
                return jsonAdapter.fromJson(content)
            }
        }
    }

    @Keep
    @Parcelize
    data class Results(
        val mal_id: Int,
        val url: String? = "",
        val images: Images,
        val title: String? = "",
        val synopsis: String? = "",
        val score: Double? = 0.0
    ): Parcelable

    @Keep
    @Parcelize
    data class Images(
        val jpg: Image,
        val webp: Image
    ): Parcelable

    @Keep
    @Parcelize
    data class Image(
        val image_url: String,
        val small_image_url: String,
        val large_image_url: String
    ): Parcelable

    @Keep
    @Parcelize
    @Entity(tableName = "content")
    data class Content(
        @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id: Int,
        @ColumnInfo(name = "url")val url: String? = "",
        @ColumnInfo(name = "img_url")val image_url: String? = "",
        @ColumnInfo(name = "title")val title: String? = "",
        @ColumnInfo(name = "synopsis")val synopsis: String? = "",
        @ColumnInfo(name = "score")val score: Double? = 0.0
    ): Parcelable
}