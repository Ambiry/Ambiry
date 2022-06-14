package de.janoroid.femali;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;

public class SearchFragment extends Fragment {
    SearchView searchView;
    RecyclerView recyclerViewCategory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);
        recyclerViewCategory = view.findViewById(R.id.recyclerviewCategory);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(getActivity(),2));

        // data to populate the RecyclerView with
        ArrayList<String> category = new ArrayList<>();
        category.add("Charts");
        category.add("Wissenschaft");
        category.add("Liebe und Sex");
        category.add("Deutsche Podcasts");
        category.add("Hörspiele");
        category.add("Hörbücher");


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), category);
        recyclerViewCategory.setAdapter(adapter);


        searchView.setQueryHint(getString(R.string.browse_everything));

        //Applies white color on searchview text und title
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = searchView.findViewById(id);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.WHITE);


        searchView.setOnClickListener(v -> {

            //enable the user to click anywhere on the SearchView
            searchView.setIconified(false);
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return view;
    }
}