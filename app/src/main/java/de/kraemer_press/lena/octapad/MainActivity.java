package de.kraemer_press.lena.octapad;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/*
 * User Clicks Record -> Button Text Changes To Recording! Click To Stop.
 * User Plays -> Text View Shows: RECORDED: DRUMS [THE BUTTON PRESSED]
 * User Stops -> Button Text Changes to Record (Toast Shows Recorded Successfully!)
 * Also + Play Button Text has > + Recording available to Play
 * User Hits Play -> User's Music is Played Back!
 * Use Stack To Store User's Song!
 * Once The User Clicks record ->  recording = true!
 * Array of 10 the user clicks a button - insert ino the array the button number
 * 3 - loically name them 1 2 3 and click - insert but_num -> array
 * implement a push() function (insert the value) : 1,2,3 != 1 | 2,1 | 3,2,1 (stack logic)
 * click play -> reverse then play it!
 * 10
 */


public class MainActivity extends AppCompatActivity{

    MediaPlayer m = null;
    StackDS record = new StackDS();
    boolean recording = false;

    // After we exit the recording state, pop the stack to the array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tView = (TextView) findViewById(R.id.txtOut);

        Toast.makeText(getApplicationContext(), "Let Us Make Some Music", Toast.LENGTH_LONG).show();

        Button vDrum = (Button)findViewById(R.id.btnDrum);
        vDrum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlayMusic(v, Sound.DRUMS);
                    if (recording == true) {
                        record.push(1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        final Button kDrum = (Button)findViewById(R.id.btnSnare);
        kDrum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    PlayMusic(v, Sound.SNARE);
                    if (recording == true) {
                        record.push(2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button sDrum = (Button)findViewById(R.id.btnRim);
        sDrum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    PlayMusic(v, Sound.RIM_SHOT);
                    if (recording == true){
                        record.push(3);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        Button btnRecord = (Button)findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tView.setText("Recording");
                recording = true;
            }
        });
        Button btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tView.setText("Record");
                recording = false;
                for (int i = 9; i>= 0; i--){
                    if (StackDS.sArr[i] == 1){
                        try {
                            PlayMusic(v, Sound.DRUMS);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (StackDS.sArr[i] == 2) {
                        try {
                            PlayMusic(v, Sound.SNARE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (StackDS.sArr[i] == 3) {
                        try {
                            PlayMusic(v, Sound.RIM_SHOT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void PlayMusic(View v, String sound) throws IOException {

        //Comment

        m = new MediaPlayer();

        AssetFileDescriptor descriptor = getAssets().openFd(sound);
        m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
        descriptor.close();

        m.prepare();
        m.setVolume(1f, 1f);
        m.setLooping(false);
        m.start();
    }
}

