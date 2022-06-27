package de.jukolai.jukolai;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;
public class HomeFragment extends Fragment {

    TextView textviewGreeting;
    ImageButton imageButtonSettings;
    TextView textViewFirstStart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        textviewGreeting = view.findViewById(R.id.textViewWelcome);
        imageButtonSettings = view.findViewById(R.id.imageButtonSettings);
        textViewFirstStart = view.findViewById(R.id.textviewFirstStart);

        textViewFirstStart.setText("Es ist so leise hier....\n \nEntdecke alle Künstler*innen auf dieser Plattform \uD83D\uDE00");



        imageButtonSettings.setOnClickListener(v -> {
            SettingsFragment settingFragment = new SettingsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, settingFragment);
            fragmentTransaction.commit();

        });

        setWelcomeText();



        return view;
    }

    private void setWelcomeText(){

        //get current Time
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (currentHour < 12){

            textviewGreeting.setText(R.string.goodMorning);

        }else if (currentHour < 18){

            textviewGreeting.setText(R.string.goodDay);

        }else if (currentHour > 18){

            textviewGreeting.setText(R.string.goodEvening);
        }
    }
}