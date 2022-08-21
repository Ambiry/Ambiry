package de.jukolai.ambiry;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView textviewGreeting;
    ImageButton imageButtonSettings;
    RecyclerView parentRecyclerView;
    private ChildModel childModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textviewGreeting = view.findViewById(R.id.textViewWelcome);
        imageButtonSettings = view.findViewById(R.id.imageButtonSettings);
        parentRecyclerView = view.findViewById(R.id.parentRecyclerview);


        imageButtonSettings.setOnClickListener(v -> {
            SettingsFragment settingFragment = new SettingsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, settingFragment);
            fragmentTransaction.commit();

        });


        setWelcomeText();


        // added in 5th category

        List<ChildModel> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new ChildModel("Science Cop", "childModel.getAuthor()","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","Max Doeckel und Jonathan Focke"));
        categoryItemList5.add(new ChildModel("1Live WDR 99 Probleme", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/1live-ninetynine-problems-mit-felix-lobrecht-neu-102~_v-Podcast.jpg","","","","","","Felix Lobrecht"));
        categoryItemList5.add(new ChildModel("Domian 2022", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg","","","","","","Juljano"));

        List<ChildModel> categoryItemList6 = new ArrayList<>();
        categoryItemList6.add(new ChildModel("Domian 2022", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg","","","","","","Juljano"));
        categoryItemList6.add(new ChildModel("test", "R.drawable.webp","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","Juljano"));
        categoryItemList6.add(new ChildModel("Domian 2022", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg","","","","","","Juljano"));


        List<ChildModel> categoryItemList7 = new ArrayList<>();
        categoryItemList7.add(new ChildModel("Science Cop", "childModel.getAuthor()","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","Max Doeckel und Jonathan Focke"));
        categoryItemList7.add(new ChildModel("1Live WDR 99 Probleme", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/1live-ninetynine-problems-mit-felix-lobrecht-neu-102~_v-Podcast.jpg","","","","","","Felix Lobrecht"));
        categoryItemList7.add(new ChildModel("Domian 2022", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg","","","","","","Juljano"));

        List<ChildModel> categoryItemList8 = new ArrayList<>();
        categoryItemList8.add(new ChildModel("Domian 2022", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg","","","","","","Juljano"));
        categoryItemList8.add(new ChildModel("test", "R.drawable.webp","https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg","","","","","","Juljano"));
        categoryItemList8.add(new ChildModel("Domian 2022", "R.drawable.webp","https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg","","","","","","Juljano"));



        List<ParentModel> allCategoryList = new ArrayList<>();
        allCategoryList.add(new ParentModel("Unsere Empfehlungen", categoryItemList5));
        allCategoryList.add(new ParentModel("Unsere Empfehlungen", categoryItemList6));
        allCategoryList.add(new ParentModel("Unsere Empfehlungen", categoryItemList7));
        allCategoryList.add(new ParentModel("Unsere Empfehlungen", categoryItemList8));






        setMainCategoryRecycler(allCategoryList);



        return view;
    }

    private void setMainCategoryRecycler(List<ParentModel> allCategoryList){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        parentRecyclerView.setLayoutManager(layoutManager);
        ParentRecyclerViewAdapter parentRecyclerViewAdapter = new ParentRecyclerViewAdapter(getContext(), allCategoryList);
        parentRecyclerView.setAdapter(parentRecyclerViewAdapter);

    }




    private void setWelcomeText() {

        //get current Time
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (currentHour < 12) {

            textviewGreeting.setText(R.string.goodMorning);

        } else if (currentHour < 18) {

            textviewGreeting.setText(R.string.goodDay);

        } else if (currentHour > 18) {

            textviewGreeting.setText(R.string.goodEvening);
        }
    }
}