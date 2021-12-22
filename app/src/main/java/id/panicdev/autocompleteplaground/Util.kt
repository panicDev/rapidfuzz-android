package id.panicdev.autocompleteplaground

import android.content.Context
import androidx.annotation.RawRes
import me.xdrop.fuzzywuzzy.FuzzySearch
import java.io.*

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

    val results = FuzzySearch.extractAll(search, strings).apply { sortByDescending { it.score } }
    return results.map { result -> elements.find { it.first == result.string }!!.second }
}

fun Collection<String>.searchFor(search: String): List<String> {
    val results = FuzzySearch.extractAll(search, filter { it.contains(search, true) }).apply { sortByDescending { it.score } }
    return results.map { it.string }
}