package com.example.kotlinday5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product_table")
data class Products(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val brand: String,
    @SerializedName("thumbnail")
    @ColumnInfo(name = "url")
    val url: String,
    val price: Double,
    val rating: Float
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Products

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (brand != other.brand) return false
        if (url != other.url) return false
        if (price != other.price) return false
        return rating == other.rating
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + brand.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + rating.hashCode()
        return result
    }

    override fun toString(): String {
        return "Products(id=$id, title='$title', description='$description', brand='$brand', url='$url', price=$price, rating=$rating)"
    }
}