package com.wajahatkarim3.clapfab

import java.util.*


/**
 * Taken From
 * https://stackoverflow.com/questions/4753251/how-to-go-about-formatting-1200-to-1-2k-in-java#answer-30661479
 *
 * @author assylias
 * @version 1.0
 */
object NumberUtil {

    private val suffixes = TreeMap<Long, String>()

    init {
        suffixes[1_000L] = "K";
        suffixes[1_000_000L] = "M";
        suffixes[1_000_000_000L] = "G";
        suffixes[1_000_000_000_000L] = "T";
        suffixes[1_000_000_000_000_000L] = "P";
        suffixes[1_000_000_000_000_000_000L] = "E";
    }

    fun format(value: Int): String {
        if (value == Int.MIN_VALUE) return format(Int.MIN_VALUE + 1)
        if (value < 0) return "-" + format(-value)
        if (value < 1000) return value.toString() //deal with easy case

        val e = suffixes.floorEntry(value.toLong())
        val divideBy = e.key
        val suffix = e.value

        val truncated = (value / (divideBy!! / 10)).toFloat() //the number part of the output times 10
        val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
        return if (hasDecimal) (truncated / 10.0).toString() + suffix else (truncated / 10).toString() + suffix
    }

}