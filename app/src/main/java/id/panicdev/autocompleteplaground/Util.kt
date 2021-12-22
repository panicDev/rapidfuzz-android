package id.panicdev.autocompleteplaground

import android.content.Context
import android.util.Log.d
import android.widget.TextView
import androidx.annotation.RawRes
import id.panicdev.rapidfuzz.RapidFuzzCached
import java.io.*
import kotlin.math.min
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

object Util {
    fun getJsonFromRaw(context: Context, @RawRes value: Int): String {
        val raw = context.resources.openRawResource(value)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        raw.use { rawData ->
            val reader: Reader = BufferedReader(InputStreamReader(rawData, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }
}

fun <E> Collection<E>.searchFor(search: String, getString: (E) -> String): List<E> {
    val strings = ArrayList<String>()
    val elements = ArrayList<Pair<String, E>>()

    forEach {
        val string = getString(it)
        if (getString(it).contains(search, true)) {
            strings += string
            elements += string to it
        }
    }

    val results =
        RapidFuzzCached.extractAll(search, strings).apply { sortByDescending { it.score } }
    return results.map { result -> elements.find { it.first == result.string }!!.second }
}

fun Collection<String>.searchFor(search: String): List<String> {
    val results = RapidFuzzCached.extractAll(search, filter { it.contains(search, true) })
        .apply { sortByDescending { it.score } }
    return results.map { it.string }
}

fun <R> printDurationNano(tag: String, suffix: String = "", execution: () -> R): R {

    var result: R? = null

    val duration = measureNanoTime {
        result = execution()
    }

    d(tag, "$suffix Duration is $duration ns")

    return result ?: throw Exception("No Result in execution Lambda")
}

fun <R> printDurationMilli(
    tag: String,
    suffix: String = "",
    textView: TextView,
    execution: () -> R
): R {

    var result: R? = null

    val duration = measureTimeMillis {
        result = execution()
    }

    textView.text = "$suffix in $duration ms"
    d(tag, "$suffix Duration is $duration ms")

    return result ?: throw Exception("No Result in execution Lambda")
}

fun damerau_levenshtein(first_string: String, second_string: String): Int {
    val n: Int = first_string.length
    val m: Int = second_string.length
    val dp: Array<Array<Int>> = Array(n + 1) { Array(m + 1) { 1000 } }
    for (i in 0 until n + 1) dp[i][0] = i
    for (j in 0 until m + 1) dp[0][j] = j

    for (i in 1 until n + 1) {
        for (j in 1 until m + 1) {
            dp[i][j] = dp[i - 1][j - 1]
            if (first_string[i - 1] != second_string[j - 1]) dp[i][j] += 1
            dp[i][j] = min(dp[i][j], dp[i - 1][j] + 1)
            dp[i][j] = min(dp[i][j], dp[i][j - 1] + 1)
            if (i > 1 && j > 1 && first_string[i - 1] == second_string[j - 2] && first_string[i - 2] == second_string[j - 1])
                dp[i][j] = min(dp[i][j], dp[i - 2][j - 2] + 1)
        }
    }
    return dp[n][m]
}

fun levenshtein(lhs: CharSequence, rhs: CharSequence): Int {
    if (lhs == rhs) {
        return 0
    }
    if (lhs.isEmpty()) {
        return rhs.length
    }
    if (rhs.isEmpty()) {
        return lhs.length
    }

    val lhsLength = lhs.length + 1
    val rhsLength = rhs.length + 1

    var cost = Array(lhsLength) { it }
    var newCost = Array(lhsLength) { 0 }

    for (i in 1 until rhsLength) {
        newCost[0] = i

        for (j in 1 until lhsLength) {
            val match = if (lhs[j - 1] == rhs[i - 1]) 0 else 1

            val costReplace = cost[j - 1] + match
            val costInsert = cost[j] + 1
            val costDelete = newCost[j - 1] + 1

            newCost[j] = min(min(costInsert, costDelete), costReplace)
        }

        val swap = cost
        cost = newCost
        newCost = swap
    }

    return cost[lhsLength - 1]
}