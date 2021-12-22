package id.panicdev.autocompleteplaground;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class AcAdapter extends ArrayAdapter<String> {

    final Context context;
    final int resource;
    private List<String> mListData, baseList;


    public AcAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        baseList = new ArrayList<>(items);
        mListData = new ArrayList<>();
        Log.i("AcAdapter", "TempItemSize = " + baseList.size());
    }

    public void setData(List<String> list) {
        mListData = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
        }
        String product = getItem(position);
        if (product != null) {
            TextView name = view.findViewById(R.id.text_view);
            if (name != null) {
                name.setText(product);
            }
        }
        return view;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return mListData.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    final Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return resultValue.toString();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            ArrayList<String> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                suggestions.addAll(baseList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
//                exResult = FuzzySearch.extractAll(filterPattern, baseList)
                for (String dish : baseList) {
                    int ratio = FuzzySearch.partialRatio(filterPattern, dish);
                    Log.w("Ratio: ", ratio + " is in: " + dish);
                    if (ratio >= 85) {
                        Log.w("suggested: ", filterPattern + " is in: " + dish);
                        suggestions.add(dish);
                    }
                }
            }

            //alternative without fuzzy
                /*for (Product product : tempItems) {
                    if (product.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(product);
                    }
                }*/

            filterResults.values = suggestions;
            filterResults.count = suggestions.size();
            return filterResults;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mListData.clear();
            mListData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}