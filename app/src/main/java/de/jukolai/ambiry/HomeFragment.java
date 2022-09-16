package de.jukolai.ambiry;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView textviewGreeting;
    ImageButton imageButtonSettings;
    RecyclerView parentRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textviewGreeting = view.findViewById(R.id.textViewWelcome);
        imageButtonSettings = view.findViewById(R.id.imageButtonSettings);
        parentRecyclerView = view.findViewById(R.id.parentRecyclerview);
        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());


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
        categoryItemList5.add(new ChildModel("Science Cop", "childModel.getAuthor()", "https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg", "", "", "", "", "", "Max Doeckel und Jonathan Focke"));
        categoryItemList5.add(new ChildModel("1Live WDR 99 Probleme", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/1live-ninetynine-problems-mit-felix-lobrecht-neu-102~_v-Podcast.jpg", "", "", "", "", "", "Felix Lobrecht"));
        categoryItemList5.add(new ChildModel("Domian 2022", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg", "", "", "", "", "", "Domian"));
        categoryItemList5.add(new ChildModel("Domian 2022", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg", "", "", "", "", "", "Domain"));


        List<ChildModel> categoryItemList6 = new ArrayList<>();
        categoryItemList6.add(new ChildModel("Domian 2022", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg", "", "", "", "", "", "Domian"));
        categoryItemList6.add(new ChildModel("Verbrechen", "R.drawable.webp", "https://cdn-images-1.listennotes.com/podcasts/verbrechen-zeit-online-6QldG9PNafi-bVriuHG05X8.1400x1400.jpg", "", "", "", "", "", "Zeit"));
        categoryItemList6.add(new ChildModel("Tagesschau", "R.drawable.webp", "https://api.ardmediathek.de/image-service/images/urn:ard:image:08875b19a095a2a3?w=448&ch=ea8031e2bee51381", "", "", "", "", "", "Tagesschau"));


        List<ChildModel> categoryItemList7 = new ArrayList<>();
        categoryItemList7.add(new ChildModel("Science Cop", "childModel.getAuthor()", "https://www.quarks.de/wp-content/uploads/Quarks-Science-Cops-34.jpg", "", "", "", "", "", "Max Doeckel und Jonathan Focke"));
        categoryItemList7.add(new ChildModel("1Live WDR 99 Probleme", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/1live-ninetynine-problems-mit-felix-lobrecht-neu-102~_v-Podcast.jpg", "", "", "", "", "", "Felix Lobrecht"));
        categoryItemList7.add(new ChildModel("Domian 2022", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg", "", "", "", "", "", "Juljano"));

        List<ChildModel> categoryItemList8 = new ArrayList<>();
        categoryItemList8.add(new ChildModel("Domian 2022", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg", "", "", "", "", "", "Juljano"));
        categoryItemList8.add(new ChildModel("Tagesschau", "R.drawable.webp", "https://api.ardmediathek.de/image-service/images/urn:ard:image:08875b19a095a2a3?w=448&ch=ea8031e2bee51381", "", "", "", "", "", "Juljano"));
        categoryItemList8.add(new ChildModel("Domian 2022", "R.drawable.webp", "https://www1.wdr.de/mediathek/audio/sendereihen-bilder/domian-live-162~_v-Podcast.jpg", "", "", "", "", "", "Juljano"));


        List<ParentModel> allCategoryList = new ArrayList<>();
        allCategoryList.add(new ParentModel("Unsere Empfehlungen", categoryItemList5));
        allCategoryList.add(new ParentModel("Neue Folgen deiner Künstler", categoryItemList6));
        allCategoryList.add(new ParentModel("Die Top-10 in Deutschland heute", categoryItemList7));
        allCategoryList.add(new ParentModel("Andere Hören auch", categoryItemList8));


        setContentRecyclerview(allCategoryList);


        return view;
    }


    private void setContentRecyclerview(List<ParentModel> getContentList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        parentRecyclerView.setLayoutManager(layoutManager);
        ParentRecyclerViewAdapter parentRecyclerViewAdapter = new ParentRecyclerViewAdapter(getContext(), getContentList);
        parentRecyclerView.setNestedScrollingEnabled(false);
        parentRecyclerView.setAdapter(parentRecyclerViewAdapter);

    }


    private void setWelcomeText() {

        //get current Time
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (currentHour < 12) {

            textviewGreeting.setText(R.string.goodMorning);

        } else if (currentHour >= 20) {

            textviewGreeting.setText(R.string.goodDay);

        } else {

            textviewGreeting.setText(R.string.goodEvening);
        }
    }

}