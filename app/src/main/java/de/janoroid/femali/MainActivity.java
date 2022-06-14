package de.janoroid.femali;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<Object> links;

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

        new laodDataFromDatabase().execute();

        super.onStart();

    }


    //get the Links from Firebase
    private class laodDataFromDatabase extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            firebaseDatabase = FirebaseDatabase.getInstance("https://femali-default-rtdb.europe-west1.firebasedatabase.app");
            databaseReference = firebaseDatabase.getReference();


            // Read from the database
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    links = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                     links.add(String.valueOf(dsp.getValue())); //add links into arraylist


                }
                     Log.d("TAG", "Value is: " + links.get(3));
                     onPostExecute(links);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException());
                }
            });

            return null;
        }

        protected void onPostExecute(ArrayList getArrayListLinks) {

            new RSSParser().execute(getArrayListLinks);

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }




    // BottomNavigationMenu
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {
               Fragment fragment = null;

                switch (item.getItemId()) {

                    case R.id.home:

                        fragment = new HomeFragment();
                        break;

                    case R.id.search:

                        fragment = new SearchFragment();
                        break;

                    case R.id.library:

                        fragment = new LibraryFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                return true;
            };
        }
