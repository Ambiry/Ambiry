package de.janoroid.femali;
import static android.content.Context.MODE_PRIVATE;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class SettingsFragment extends Fragment {

    TextView textviewGotoWebsite;
    SwitchCompat streamingMobileSwitch, downloadMobileSwitch,enableNotificationSwitch;
    Spinner soundQualitySpinner;
    ImageButton ImageButtonBackButton;
    Button logoutButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textviewGotoWebsite = view.findViewById(R.id.textviewWebsite);
        streamingMobileSwitch = view.findViewById(R.id.mobileStreaming);
        downloadMobileSwitch = view.findViewById(R.id.mobileDownload);
        enableNotificationSwitch = view.findViewById(R.id.enableNotification);
        soundQualitySpinner = view.findViewById(R.id.soundqualitySpinner);
        ImageButtonBackButton = view.findViewById(R.id.imageButtonBackbutton);

        logoutButton = view.findViewById(R.id.buttonLogout);

        getSettingsStates();


        textviewGotoWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the Browser
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.Janoroid.de/")));
            }
        });

        streamingMobileSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((isChecked)) {

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("streamingMobile", MODE_PRIVATE).edit();
                    editor.putBoolean("streamingstate", streamingMobileSwitch.isChecked()); // value to store
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("streamingMobile", MODE_PRIVATE).edit();
                    editor.putBoolean("streamingstate", streamingMobileSwitch.isChecked()); // value to store
                    editor.commit();
                }

            }
        });

        downloadMobileSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("downloadingMobile", MODE_PRIVATE).edit();
                    editor.putBoolean("downloadingstate", downloadMobileSwitch.isChecked()); // value to store
                    editor.apply();
                } else {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("downloadinMobile", MODE_PRIVATE).edit();
                    editor.putBoolean("downloadingstate", downloadMobileSwitch.isChecked()); // value to store
                    editor.apply();
                }
            }
        });

        enableNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("enableNotification", MODE_PRIVATE).edit();
                    editor.putBoolean("notificationstate", enableNotificationSwitch.isChecked()); // value to store
                    editor.apply();
                } else {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("enableNotification", MODE_PRIVATE).edit();
                    editor.putBoolean("notificationstate", enableNotificationSwitch.isChecked()); // value to store
                    editor.apply();
                }
            }
        });

        ImageButtonBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //The Backbutton
                Intent backToHome = new Intent(getActivity(), MainActivity.class);
                startActivity(backToHome);
                getActivity().finish();
            }
        });



        //LogOut
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        return view;
    }


    private void getSettingsStates(){

        // get Settings state from the Switchs
        SharedPreferences sharedPreferencesStreamingMobile = getActivity().getPreferences(MODE_PRIVATE);
        Boolean streamingMobileStateStreaming = sharedPreferencesStreamingMobile.getBoolean("streamingstate",true);

        streamingMobileSwitch.setChecked(streamingMobileStateStreaming);


        SharedPreferences sharedPreferencesDownloading = getActivity().getPreferences(MODE_PRIVATE);
        Boolean downloadingState = sharedPreferencesDownloading.getBoolean("downloadingstate",true);
        downloadMobileSwitch.setChecked(downloadingState);


        SharedPreferences sharedPreferencesenableNotification = getActivity().getPreferences(MODE_PRIVATE);
        Boolean enableNotificationState = sharedPreferencesenableNotification.getBoolean("notificationstate",true);
        enableNotificationSwitch.setChecked(enableNotificationState);
    }
}