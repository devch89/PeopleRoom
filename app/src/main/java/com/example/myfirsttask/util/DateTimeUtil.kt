package com.example.myfirsttask.util

import java.text.SimpleDateFormat

object DateTimeUtil {

    fun String.toDate(dateFormat: String = "dd/MM/yyyy"): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat(dateFormat)
        return formatter.format(parser.parse(this))

    }
}