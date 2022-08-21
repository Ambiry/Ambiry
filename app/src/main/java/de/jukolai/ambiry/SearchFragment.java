package de.jukolai.ambiry;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    SearchView searchView;
    RecyclerView recyclerViewCategory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerViewCategory = view.findViewById(R.id.recyclerviewCategory);
        searchView = view.findViewById(R.id.searchView);
        searchView.setQueryHint(getString(R.string.browse_everything));







        //Applies white color on searchview text und title
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = searchView.findViewById(id);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20);
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



        // added in 5th category
        List<ChildModel> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new ChildModel("test", "R.drawable.webp","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","KuchenTV, Juljano"));
        categoryItemList5.add(new ChildModel("test", "R.drawable.webp","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","KuchenTV, Juljano"));

        List<ChildModel> categoryItemList6 = new ArrayList<>();
        categoryItemList5.add(new ChildModel("test", "R.drawable.webp","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","KuchenTV, Juljano"));
        categoryItemList5.add(new ChildModel("test", "R.drawable.webp","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","KuchenTV, Juljano"));



        List<ParentModel> allCategoryList = new ArrayList<>();
        allCategoryList.add(new ParentModel("Beliebt", categoryItemList5));
        allCategoryList.add(new ParentModel("Best of Oscars", categoryItemList6));



        setMainCategoryRecycler(allCategoryList);

        return view;
    }

    private void setMainCategoryRecycler(List<ParentModel> allCategoryList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCategory.setLayoutManager(layoutManager);
        ParentRecyclerViewAdapter parentRecyclerViewAdapter = new ParentRecyclerViewAdapter(getContext(), allCategoryList);
        recyclerViewCategory.setAdapter(parentRecyclerViewAdapter);

    }
}