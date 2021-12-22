package id.panicdev.autocompleteplaground

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.runBlocking
import java.util.*


class AutoCompleteAdapter(
    private val mContext: Context,
    @LayoutRes private val layout: Int,
    mList: MutableList<String>,
    private val mTextView: MaterialAutoCompleteTextView,
    private val mTextViewParent: TextView,
) : ArrayAdapter<String>(mContext, layout, mList) {

    var filteredData: List<String> = listOf()

    private var mColorStr = "#AAA"


    fun color(colorResId: Int): AutoCompleteAdapter {
        val color = ContextCompat.getColor(mContext, colorResId)
        val colorRes = StringBuffer("#")
        appendHexString(Color.alpha(color) % 255, colorRes)
        appendHexString(Color.red(color), colorRes)
        appendHexString(Color.green(color), colorRes)
        appendHexString(Color.blue(color), colorRes)
        mColorStr = colorRes.toString()
        return this
    }

    private fun appendHexString(value: Int, sb: StringBuffer) {
        val color = Integer.toHexString(value)
        if (color.length == 1) {
            sb.append("0")
        }
        sb.append(color)
    }

    override fun getCount(): Int = filteredData.size

    override fun getItem(position: Int): String = filteredData[position]


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val newConvertView: View = if (convertView == null) {
            val view = LayoutInflater.from(mContext).inflate(layout, parent, false)
            viewHolder = ViewHolder(view, mTextView)
            view.tag = viewHolder
            view
        } else {
            viewHolder = convertView.tag as ViewHolder
            convertView
        }
        viewHolder.bind(getItem(position), mColorStr)
        return newConvertView
    }

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults = runBlocking {
            val filterResults = FilterResults()

            if (constraint != null) {
                val query = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }


                val filteredItem = printDurationMilli("FuzzySearch", "Search $query", mTextViewParent) {
                    mList.searchFor(query)
                }
                filterResults.values = filteredItem
                filterResults.count = filteredItem.size
            }
            filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null && results.count > 0) {
                filteredData = results.values as List<String>
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }

    override fun getFilter(): Filter {
        return filter
    }

}

private class ViewHolder(val view: View, val mTextView: MaterialAutoCompleteTextView) {
    val text: TextView = view.findViewById(R.id.text_view)

    fun bind(item: String, mColorStr: String) {
        var v = item
        val highLightSlr = mTextView.text.toString().trim { it <= ' ' }

        if (highLightSlr.isEmpty()) {
            text.text = v
        } else {
            try {
                v = v.replace(
                    "(?i)($highLightSlr)".toRegex(),
                    "<font color='$mColorStr'>$1</font>"
                )
                val htmlAsString: String = v
                val htmlAsSpanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(htmlAsString, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    Html.fromHtml(htmlAsString)
                } // used by TextView
                text.text = htmlAsSpanned

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}