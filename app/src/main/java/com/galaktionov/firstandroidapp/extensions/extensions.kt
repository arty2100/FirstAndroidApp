package com.galaktionov.firstandroidapp.extensions

import com.galaktionov.firstandroidapp.dto.Location

infix fun Double.x(that: Double) = Location(lat = this, long = that)


