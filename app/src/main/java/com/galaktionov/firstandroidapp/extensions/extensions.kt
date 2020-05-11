package com.galaktionov.firstandroidapp.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.galaktionov.firstandroidapp.dto.Location
import io.ktor.client.HttpClient

infix fun Double.x(that: Double) = Location(lat = this, long = that)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
fun HttpClient.install(){

}