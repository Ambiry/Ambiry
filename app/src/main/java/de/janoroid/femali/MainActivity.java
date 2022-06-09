package de.janoroid.femali;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ArrayList<Object> links = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


        // if savedinstacestate is null, then show the HomeFragment
        if (savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        }

        }


    @Override
    protected void onStart() {

        laodDataFromDatabase laodDataFromDatabase = new laodDataFromDatabase();
        laodDataFromDatabase.execute();

        super.onStart();

    }


    //get the Links from Firebase und parsed the Podcasts
    private class laodDataFromDatabase extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            firebaseDatabase = FirebaseDatabase.getInstance("https://femali-default-rtdb.europe-west1.firebasedatabase.app");
            databaseReference = firebaseDatabase.getReference();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()){

                        links.add(snapshot.getValue());

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            RSSParser rssParser = new RSSParser();
            rssParser.execute();

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }




    // BottomNavigationMenu
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment fragment = null;

                    switch (item.getItemId()) {

                        case R.id.home:

                            fragment = new HomeFragment();
                            break;

                        case R.id.search:
                            fragment = new PodcastOverviewFragment();
                            break;

                        case R.id.library:

                            fragment = new LibraryFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    return true;
                }

            };
        }
