package de.janoroid.femali;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PlaylistFragment extends Fragment {

    ImageButton createPlaylistImageButton;
    RecyclerView recyclerView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        createPlaylistImageButton = view.findViewById(R.id.imageButtonCreatePlaylist);
        recyclerView = view.findViewById(R.id.recyclerviewPlaylist);


        // Recylerview
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        createPlaylistImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPlaylist();
            }
        });



        return view;
    }

    private void createPlaylist(){

        View view = getLayoutInflater().inflate(R.layout.createplaylistdialog,null);

        // Fullscreen
        Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.setContentView(view);
        dialog.show();

        ImageButton backButton = new ImageButton(getActivity());
        EditText editTextCreatePlaylist = new EditText(getActivity());
        Button createPlaylistButton = new Button(getActivity());


        backButton = dialog.findViewById(R.id.imageButtonBackbutton);
        editTextCreatePlaylist = dialog.findViewById(R.id.editTextPlaylist);
        createPlaylistButton = dialog.findViewById(R.id.createPlaylistButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Erstellen!", Toast.LENGTH_SHORT).show();

            }
        });






    }
}