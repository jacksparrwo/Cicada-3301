package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.storage.StorageHandler;


public class SinglePlayerSong extends AppCompatActivity {

    private final StorageHandler mStorage = StorageHandler.getInstance();
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

    String [] oldieLyricsArray={" She told my \uD83D\uDC76 we'd \uD83D\uDD7A 'til 3, then she \uD83D\uDC40 at me\n Then showed a \uD83E\uDD33 my \uD83D\uDC76 \uD83D\uDE2D his \uD83D\uDC41️\uD83D\uDC41️ were like mine (oh, no) Cause we \uD83D\uDD7A on the floor in the round, \uD83D\uDC76",
    "    \uD83D\uDCA3 ",
    " I just can't get you out of my \uD83D\uDC86\u200D♀️\n \uD83D\uDC66\uD83C\uDFFC, your ❤️ is all I \uD83D\uDCAD about",
    " Put me up, put me down\n Put my \uD83D\uDC63 back on the ground\n Put me up, take my ❤ and make me \uD83D\uDE0A",
    " \uD83D\uDC83 \uD83D\uDC51",
    " There's not a \uD83D\uDC7B out there\n No one to \uD83D\uDC42 my \uD83D\uDE4F\uD83C\uDFFB\n Gimme, gimme, gimme a \uD83E\uDD35 after \uD83C\uDF19\n Won't somebody help me chase the \uD83D\uDC7B away",
    " Oh, I wanna \uD83D\uDC83 with somebody\n I wanna feel the \uD83D\uDD25 with somebody\n Yeah, I wanna \uD83D\uDC83 with somebody\n With somebody who ❤ me",
    " A little bit of \uD83D\uDC67\uD83C\uDFFC is what I \uD83D\uDC40\n A little bit of \uD83D\uDC67\uD83C\uDFFE in the ☀️\n A little bit of \uD83D\uDC67\uD83C\uDFFD all \uD83C\uDF15 long\n A little bit of \uD83D\uDC67\uD83C\uDFFB, here I am\n A little bit of you makes me your \uD83E\uDD35",
    " ➡️the \uD83C\uDF1C and \uD83D\uDD19",
    " ⏰ up before you \uD83D\uDEB6-\uD83D\uDEB6\n Don't leave me hanging on like a \uD83E\uDE80\n ⏰ up before you \uD83D\uDEB6-\uD83D\uDEB6\n I don't wanna miss it when you hit that high"};

    String [] partyLyricsArray={"      \uD83D\uDD18\uD83D\uDD18\uD83D\uDD18",
    "        \uD83C\uDDEB\uD83C\uDDF7 \uD83D\uDC8B",
    " It's my life\n It's now or never\n But I ain't gonna live forever\n I just want to live while I'm alive",
    " She's into superstitions\n \uD83D\uDC08\u200D⬛\uD83D\uDC08\u200D⬛ and voodoo \uD83C\uDF8E\n I feel a premonition\n That \uD83D\uDC67\uD83C\uDFFD's gonna make me fall",
    " Cause if you \uD83D\uDC4D it then you should have put a \uD83D\uDC8D on it\n If you \uD83D\uDC4D it then you shoulda put a \uD83D\uDC8D on it\n Don't be mad once you \uD83D\uDC40 that he want it\n If you \uD83D\uDC4D it then you shoulda put a \uD83D\uDC8D on it\n Oh, oh, oh"};

    String [] sadLyricsArray = { " \uD83D\uDDE3 them all I know now\n Shout it from the roof tops\n ✍\uD83C\uDFFC it on the \uD83C\uDF06 line\n   All we had is gone now",
    " This \uD83C\uDFD9️'s gonna ⛏️ my \uD83D\uDC96\n This \uD83C\uDFD9️'s gonna \uD83D\uDC96 me then leave me alone\n This \uD83C\uDFD9️'s got me chasing ⭐⭐\n It's been a couple months since I felt like I'm \uD83C\uDFE0",
    " So I \uD83D\uDC42 you found somebody else\n And at first I thought it was a \uD83E\uDD25",
    " Too \uD83E\uDDD2\uD83C\uDFFC, too \uD83E\uDD74 to realize\n That I should have bought you \uD83D\uDC90\n And held your \uD83D\uDD90️\n Should have gave you all my \uD83D\uDD57\n When I had the chance"};

    String showerLyricsArray [] ={ " The \uD83E\uDD47 years\n The \uD83E\uDD48 \uD83D\uDCA7\uD83D\uDCA7\n You wore a \uD83D\uDC54 like Richard Gere\n I want to get down\n You spin me around\n I stand on the borderline\n \uD83D\uDE2D at the \uD83C\uDF89",
    " You are my \uD83D\uDD25\n The one desire\n Believe when I \uD83D\uDDE3️\n I want it that way",
    " He won't be the one to \uD83D\uDE2D\n Let's not \uD83D\uDD2A the \uD83E\uDE83\n  Let's not \uD83C\uDFC1 a fight\n  It's not worth the \uD83C\uDFAD\n For a \uD83D\uDC84 \uD83E\uDD25",
    " Wanna have \uD83E\uDD2A\n \uD83D\uDC6F\u200D♀️\n Wanna have\n They just wanna\n They just wanna... ",
    " You're my \uD83D\uDC96, you're my \uD83D\uDC7B\n Yeah, a feelin' that our \uD83D\uDC96 will grow",
    " It's strange but it's true\n I can't get over the way you \uD83D\uDC96 me like you do\n But I have to be sure\n When I \uD83D\uDEB6\u200D♂️ out that \uD83D\uDEAA"};
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
            lyrics.setText("Can you guess the songs of your childhood cartoons?");
        }
        if(getIntent().getStringExtra("category").equals("sad")){
            img.setImageResource(R.drawable.ic_action_sad_black);
        }

        if(!mStorage.checkMediaPlayerIsPlaying()) {
            mStorage.startMediaPlayer();
        }
        showLyrics();


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
                if(data.equals("Mariah_Carey_All_I_Want_For_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[0]);
                }
                else if(data.equals("Michael_Buble_Its_Beginning_To_Look_A_Lot_Like_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[1]);
                }
                else if(data.equals("Jose_Feliciano_Feliz_Navidad.mp3")){
                    lyrics.setText(christmaslyricsArray[2]);
                }
                else if(data.equals("Michael_Buble_Holly_Jolly.mp3")){
                    lyrics.setText(christmaslyricsArray[3]);
                }
                else if(data.equals("Michael_Buble_I'll_Be_Home_For_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[4]);
                }
                else if(data.equals("Jingle_Bell_Rock.mp3")){
                    lyrics.setText(christmaslyricsArray[5]);
                }
                else if(data.equals("Wham_Last_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[6]);
                }
                else if(data.equals("Let_It_Snow_Let_It_Snow_Let_It_Snow.mp3")){
                    lyrics.setText(christmaslyricsArray[7]);
                }
                else if(data.equals("Justin_Bieber_Mistletoe.mp3")){
                    lyrics.setText(christmaslyricsArray[8]);
                }
                else if(data.equals("Brenda_Lee_Rockin'_Around_The_Christmas_Tree.mp3")){
                    lyrics.setText(christmaslyricsArray[9]);
                }
                else if(data.equals("Rudolph_The_Red_Nosed_Reindeer.mp3")){
                    lyrics.setText(christmaslyricsArray[10]);
                }
                else if(data.equals("Frank_Sinatra_Santa_Claus_Is_Coming_To_Town.mp3")){
                    lyrics.setText(christmaslyricsArray[11]);
                }
                else if(data.equals("Ariana_Grande_Santa_Tell_Me.mp3")){
                    lyrics.setText(christmaslyricsArray[12]);
                }
                else if(data.equals("Train_Shake_Up_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[13]);
                }
                else if(data.equals("Celine_Dion_So_This_Is_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[14]);
                }
                else if(data.equals("Michael_Buble_White_Christmas.mp3")){
                    lyrics.setText(christmaslyricsArray[15]);
                }
                else if(data.equals("Michael_Jackson_Billie_Jean.mp3")){
                    lyrics.setText(oldieLyricsArray[0]);
                }
                else if(data.equals("King_of_Africa_Bomba.mp3")){
                    lyrics.setText(oldieLyricsArray[1]);
                }
                else if(data.equals("Kylie_Minogue_Can't_Get_You_Out_Of_My_Head.mp3")){
                    lyrics.setText(oldieLyricsArray[2]);
                }
                else if(data.equals("Mr_President_Coco_Jambo.mp3")){
                    lyrics.setText(oldieLyricsArray[3]);
                }
                else if(data.equals("Abba_Dancing_Queen.mp3")){
                    lyrics.setText(oldieLyricsArray[4]);
                }
                else if(data.equals("Abba_Gimme_Gimme_Gimme.mp3")){
                    lyrics.setText(oldieLyricsArray[5]);
                }
                else if(data.equals("Whitney_Houston_I_Wanna_Dance_With_Somebody.mp3")){
                    lyrics.setText(oldieLyricsArray[6]);
                }
                else if(data.equals("Lou_Bega_Mambo_Nr_5.mp3")){
                    lyrics.setText(oldieLyricsArray[7]);
                }
                else if(data.equals("Savage_Garden_To_The_Moon_And_Back.mp3")){
                    lyrics.setText(oldieLyricsArray[8]);
                }
                else if(data.equals("Wham_Wake_Me_Up.mp3")){
                    lyrics.setText(oldieLyricsArray[9]);
                }
                else if(data.equals("Pussycat_Dolls_Buttons_(feat_Snoop_Dog).mp3")){
                    lyrics.setText(partyLyricsArray[0]);
                }
                else if(data.equals("Akcent_French_Kiss.mp3")){
                    lyrics.setText(partyLyricsArray[1]);
                }
                else if(data.equals("Bon_Jovi_Its_My_Life.mp3")){
                    lyrics.setText(partyLyricsArray[2]);
                }
                else if(data.equals("Ricky_Martin_Livin'_La_Vida_Loca.mp3")){
                    lyrics.setText(partyLyricsArray[3]);
                }
                else if(data.equals("Beyonce_Single_Ladies.mp3")){
                    lyrics.setText(partyLyricsArray[4]);
                }
                else if(data.equals("James_Arthur_Impossible.mp3")){
                    lyrics.setText(sadLyricsArray[0]);
                }
                else if(data.equals("Sam_Fischer_This_City.mp3")){
                    lyrics.setText(sadLyricsArray[1]);
                }
                else if(data.equals("The_1975_Somebody_Else.mp3")){
                    lyrics.setText(sadLyricsArray[2]);
                }
                else if(data.equals("When_I_Was_Your_Man_Bruno_Mars.mp3")){
                    lyrics.setText(sadLyricsArray[3]);
                }
                else if(data.equals("Alcazar_Crying_At_The_Discoteque.mp3")){
                    lyrics.setText(showerLyricsArray[0]);
                }
                else if(data.equals("Backstreet_Boys_I_Want_It_That_Way.mp3")){
                    lyrics.setText(showerLyricsArray[1]);
                }
                else if(data.equals("Beyoncé_Shakira_Beautiful_Liar.mp3")){
                    lyrics.setText(showerLyricsArray[2]);
                }
                else if(data.equals("Cyndi_Lauper_Girls_Just_Want_To_Have_Fun.mp3")){
                    lyrics.setText(showerLyricsArray[3]);
                }
                else if(data.equals("Modern_Talking_You're_My_Heart_You're_My_Soul.mp3")){
                    lyrics.setText(showerLyricsArray[4]);
                }
                else if(data.equals("Queen_I_Want_To_Break_sFree.mp3")){
                    lyrics.setText(showerLyricsArray[5]);
                }

                else {
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
        Intent songChoice = new Intent(this, SongChoice.class);
        songChoice.putExtra("category", getIntent().getStringExtra("category"));
        startActivity(songChoice);
    }
}