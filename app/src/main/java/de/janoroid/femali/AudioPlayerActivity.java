package de.janoroid.femali;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity {

    ImageButton playButton,skippreviousButton,skipnextButton,descriptionButton,thumbupButton,commentButton,playlistaddButton,castButton,arrowDownButton;
    TextView textViewTitle,textViewremainingTime,textViewcurrentTime;
    SeekBar seekBarDuration;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        playButton = findViewById(R.id.playbutton);
        skipnextButton = findViewById(R.id.skipnextbutton);
        skippreviousButton = findViewById(R.id.skippreviousbutton);
        descriptionButton = findViewById(R.id.descriptionButton);
        thumbupButton = findViewById(R.id.thumbupButton);
        commentButton = findViewById(R.id.commentButon);
        playlistaddButton = findViewById(R.id.playlistadButton);
        castButton = findViewById(R.id.castbutton);
        textViewTitle = findViewById(R.id.textviewTitle);
        textViewremainingTime = findViewById(R.id.textviewremainingtime);
        textViewcurrentTime = findViewById(R.id.textviewcurrentime);
        seekBarDuration = findViewById(R.id.seekbar);
        arrowDownButton = findViewById(R.id.arrowButton);


        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://wdrmedien-a.akamaihd.net/medp/podcast/weltweit/fsk0/264/2646032/quarkssciencecops_2022-02-12_abkassierenmitbelebtemwasserderfallgrander_wdronline.mp3");
            mediaPlayer.prepare();
            playPause();
        } catch (IOException e) {
            e.printStackTrace();
        }


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playPause();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        skippreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipPrevious();
            }
        });

        skipnextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipNext();
            }
        });


        seekBarDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






    }

    private void playPause() throws IOException {
        if (!mediaPlayer.isPlaying()){
            playButton.setBackgroundResource(R.drawable.pauseicon);
            mediaPlayer.start();
           // mediaPlayer.prepareAsync();
            seekbarUpdateHandler.postDelayed(UpdateSeekbar, 0);
            textViewUpdateHandler.postDelayed(UpdateTextview,0);
        }else{
            playButton.setBackgroundResource(R.drawable.playicon);
            mediaPlayer.pause();
            seekbarUpdateHandler.removeCallbacks(UpdateSeekbar);
            textViewUpdateHandler.removeCallbacks(UpdateTextview);

        }

    }

    private void skipNext(){


    }

    private void skipPrevious(){


    }



    //updated every 50 millisecons the seekBar
    private Handler seekbarUpdateHandler = new Handler();
    private Runnable UpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            seekBarDuration.setMax(mediaPlayer.getDuration());
            seekBarDuration.setProgress(mediaPlayer.getCurrentPosition());
            seekbarUpdateHandler.postDelayed(this, 50);
        }
    };


    //updated every 50 millisecons the textviews
    private Handler textViewUpdateHandler = new Handler();
    private Runnable UpdateTextview = new Runnable() {
        @Override
        public void run() {
            textViewcurrentTime.setText(getTimeString(mediaPlayer.getCurrentPosition()));
            textViewremainingTime.setText(getTimeString(mediaPlayer.getDuration()));
            seekbarUpdateHandler.postDelayed(this, 50);
        }
    };



    // convert from millisecond to hours,minutes and seconds
    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }
}