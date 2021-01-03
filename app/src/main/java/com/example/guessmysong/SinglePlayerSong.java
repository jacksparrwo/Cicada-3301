package com.example.guessmysong;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.storage.StorageHandler;


public class SinglePlayerSong extends AppCompatActivity {

    private StorageHandler mStorage = StorageHandler.getInstance();
    EditText songName = null;
    TextView lyrics;

    String [] christmaslyricsArray={" I don't want a lot for \uD83C\uDF84\n There is just ☝ thing I need\n I don't care about the \uD83C\uDF81 \uD83C\uDF81\n Underneath the \uD83C\uDF84",
            " It's beginning to \uD83D\uDC40 a lot like \uD83C\uDF84\n Everywhere you \uD83D\uDEB6",
            " I wanna wish you a \uD83E\uDD17  \uD83C\uDF84\n I wanna wish you a \uD83E\uDD17  \uD83C\uDF84\n I wanna wish you a \uD83E\uDD17  \uD83C\uDF84\n From the ⬇️ of my \uD83D\uDC97",
            " Have a \uD83D\uDE07, \uD83E\uDD17  \uD83C\uDF84\n It's the best ⏱️ of the \uD83D\uDCC5\n I don't know if there'll be ❄️\n But have a ☕ of \uD83E\uDD17",
            " I'll be \uD83C\uDFE0 for \uD83C\uDF84\n If only in my \uD83D\uDCA4",
            " Jingle \uD83D\uDD14, jingle \uD83D\uDD14, jingle \uD83D\uDD14 \uD83C\uDFB8\n Jingle \uD83D\uDD14 \uD83D\uDD14 chime in jingle \uD83D\uDD14 ⏱️\n \uD83D\uDC83 and prancin' in Jingle \uD83D\uDD14 \uD83D\uDFE6\n In the \uD83E\uDDCA \uD83D\uDCA8",
            " Last \uD83C\uDF84 I gave you my \uD83D\uDC96\n But the very next day you gave it away\n This \uD83D\uDCC5 , to \uD83D\uDCBE me from \uD83D\uDCA7 \uD83D\uDCA7\n I'll give it to someone special",
            " Oh, the ☁️ outside is \uD83C\uDF01\n But the \uD83D\uDD25 is so delightful\n And since we've no \uD83C\uDFE0 to\uD83D\uDEB6\n Let it \uD83C\uDF28️, let it \uD83C\uDF28️, let it \uD83C\uDF28",
            " It's the most beautiful ⌚ of the \uD83D\uDCC5\n \uD83D\uDCA1 fill the \uD83D\uDEE3️\uD83D\uDEE3️, spreading so much \uD83E\uDD17 \n I should be playing in the ☃️ ❄️\n But I'ma be under the \uD83E\uDDE6",
            " \uD83C\uDFB8  ⭕ the \uD83C\uDF84\n At the \uD83C\uDF84 \uD83E\uDD73 hop\n \uD83E\uDDE6 hung where you can \uD83D\uDC40\n Every \uD83D\uDC91\uD83C\uDFFD tries to \uD83D\uDED1",
            " Then one \uD83C\uDF01 \uD83C\uDF84 Eve\n \uD83C\uDF85\uD83C\uDFFC came to say\n Rudolph, with your \uD83D\uDC43\uD83C\uDFFD so \uD83D\uDEA8\n Won't you guide my \uD83D\uDEF7 \uD83C\uDF03?",
            " You better \uD83D\uDC40\n You better ❗ \uD83D\uDE2D\n You better ❗ \uD83D\uDE4E\u200D♂️\n I'm telling you why\n \uD83C\uDF85\uD83C\uDFFD is coming to \uD83C\uDF07",
            " \uD83C\uDF85\uD83C\uDFFD, tell me if you're really there\n Don't make me \uD83C\uDF42 in ❤️ again\n If he won't be here next \uD83D\uDCC5",
            " \uD83E\uDD1D up the \uD83E\uDD17\n ⏰ up the \uD83E\uDD17\n \uD83E\uDD1D up the h\uD83E\uDD17\n It's \uD83C\uDF84 ⏱️",
            " A very merry \uD83C\uDF84\n And a \uD83E\uDD17 \uD83C\uDF86\n Let's hope it's a good one\n Without any fears",
            " I'm \uD83D\uDCA4 of a \uD83C\uDFD4️ \uD83C\uDF84\n Just like the ones I used to know"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmas);
        mStorage.PlayRandomSong(getIntent().getStringExtra("category"));

        ImageView img = (ImageView) findViewById(R.id.logoImg);
        lyrics =(TextView) findViewById(R.id.lyrics);

        if(getIntent().getStringExtra("category").equals("christmas")){
            img.setImageResource(R.drawable.ic_christmas_black_2);
        }
        if(getIntent().getStringExtra("category").equals("love")){
            img.setImageResource(R.drawable.ic_action_love_black);
        }
        if(getIntent().getStringExtra("category").equals("shower")){
            img.setImageResource(R.drawable.ic_action_shower_black);
        }
        if(getIntent().getStringExtra("category").equals("party")){
            img.setImageResource(R.drawable.ic_action_party_black);
        }
        if(getIntent().getStringExtra("category").equals("summer")){
            img.setImageResource(R.drawable.ic_action_summer_black);
        }
        if(getIntent().getStringExtra("category").equals("oldie")){
            img.setImageResource(R.drawable.ic_action_oldie_black);
        }
        if(getIntent().getStringExtra("category").equals("kids")){
            img.setImageResource(R.drawable.ic_action_kids_black);
        }
        if(getIntent().getStringExtra("category").equals("sad")){
            img.setImageResource(R.drawable.ic_action_sad_black);
        }

        if(!mStorage.checkMediaPlayerIsPlaying()) {
            mStorage.startMediaPlayer();
        }
        showLyrics();

        /* */
        //lyrics.setText(christmaslyricsArray[14]);

        songName = findViewById(R.id.songName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStorage.ResetMediaPlayer();
    }

    public void showLyrics(){
        mStorage.setListener(new StorageHandler.IListenerType() {
            @Override
            public void onObjectReady(String title) {

            }

            @Override
            public void onDataLoaded(String data) {
                if(data.equals("all_i_want_for_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[0]);
                }
                else if(data.equals("beginning_to_look_a_lot_like_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[1]);
                }
                else if(data.equals("feliz_navidad.mp3")){
                    lyrics.setText(christmaslyricsArray[2]);
                }
                else if(data.equals("holly_jolly.mp3")){
                    lyrics.setText(christmaslyricsArray[3]);
                }
                else if(data.equals("home_for_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[4]);
                }
                else if(data.equals("jingle_bell_rock.mp3")){
                    lyrics.setText(christmaslyricsArray[5]);
                }
                else if(data.equals("last_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[6]);
                }
                else if(data.equals("let_it_snow.mp3")){
                    lyrics.setText(christmaslyricsArray[7]);
                }
                else if(data.equals("mistletoe.mp3")){
                    lyrics.setText(christmaslyricsArray[8]);
                }
                else if(data.equals("rockin_around_the_christmas_tree.mp3")){
                    lyrics.setText(christmaslyricsArray[9]);
                }
                else if(data.equals("rudolph.mp3")){
                    lyrics.setText(christmaslyricsArray[10]);
                }
                else if(data.equals("santa_claus_is_coming_to_town.mp3")){
                    lyrics.setText(christmaslyricsArray[11]);
                }
                else if(data.equals("santa_tell_me.mp3")){
                    lyrics.setText(christmaslyricsArray[12]);
                }
                else if(data.equals("shake_up_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[13]);
                }
                else if(data.equals("so_this_is_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[14]);
                }
                else if(data.equals("white_christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[15]);
                } else {
                    lyrics.setText("buba");
                }
            }
        });

    }

    public void onClickCheck(View v){
        String song = songName.getText().toString().toLowerCase().trim();
        boolean res = mStorage.CheckSong(song);

        if(res) {
            Toast.makeText(SinglePlayerSong.this, "You got this song!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SinglePlayerSong.this, "Not correct!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeMusicOnClick(View v) {

    }
}