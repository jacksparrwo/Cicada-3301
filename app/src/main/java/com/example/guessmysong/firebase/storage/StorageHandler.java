package com.example.guessmysong.firebase.storage;

import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.guessmysong.MainActivity;
import com.example.guessmysong.firebase.IDatabaseData;
import com.example.guessmysong.firebase.database.UserRewardSystem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class StorageHandler {

    public interface IListenerType {
        public void onObjectReady(String title);
        public void onDataLoaded(String data);
    }

    private StorageReference mStorage;
    private static StorageHandler mStorageInstance = null;
    private List<StorageReference> mItem = null;
    private String currentSong = "";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private IListenerType listener;
    private UserRewardSystem rewardSystem;
    private String songType = "";
    private long songIndex = 0;
    private boolean guessed = false;
    private int totalNumberOfSongs = 0;

    private StorageHandler() {
        this.mStorage = FirebaseStorage.getInstance("gs://universityproject-2b5cd.appspot.com").getReference();
        this.rewardSystem = UserRewardSystem.getInstance();
        this.listener = null;
    }

    public void setListener(IListenerType listener) {
        this.listener = listener;
    }

    public static StorageHandler getInstance() {
        if(null == mStorageInstance) {
            mStorageInstance = new StorageHandler();
        }

        return mStorageInstance;
    }

    public void getMusicByType(IDatabaseData type) {
        mStorage.child(((EMusicTypes)type).getNameWithLyrics()).listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                System.out.println(listResult.getPrefixes());
            }
        });

    }

    private void InitMediaPlayer() {
        if((mediaPlayer != null) || mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
    }

    public void ResetMediaPlayer() {
        mediaPlayer.reset();
    }

    public void GetSongsFromCategory(String type) {

        final StorageReference currentFolder = mStorage.child(type);
        currentFolder.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                int numberOfItems = listResult.getItems().size();
            }
        });
    }

    public void PlayChosenSong(final String type, final int index) {
        InitMediaPlayer();
        final StorageReference currentFolder = mStorage.child(type);

        currentFolder.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                String path = listResult.getItems().get(index).toString();
                songIndex = index;
                currentSong = path.substring("gs://universityproject-2b5cd.appspot.com".length() + "/".length() + type.length() + "/".length());

                if (listener != null) {
                    listener.onDataLoaded(currentSong); // <---- fire listener here
                }

                String modifPath = path.substring("gs://universityproject-2b5cd.appspot.com".length());
                mStorage.child(modifPath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        try {
                            mediaPlayer.setDataSource(uri.toString());
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void PlayRandomSong(final String type) {
        InitMediaPlayer();
        final StorageReference currentFolder = mStorage.child(type);
        songType = type;
        guessed = false;

        currentFolder.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                totalNumberOfSongs = listResult.getItems().size();
                Random random = new Random();
                int index = random.nextInt(totalNumberOfSongs);
                String path = listResult.getItems().get(index).toString();
                songIndex = index;
                currentSong = path.substring("gs://universityproject-2b5cd.appspot.com".length() + "/".length() + type.length() + "/".length());

                if (listener != null) {
                    listener.onDataLoaded(currentSong); // <---- fire listener here
                }

                String modifPath = path.substring("gs://universityproject-2b5cd.appspot.com".length());
                mStorage.child(modifPath).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        try {
                            mediaPlayer.setDataSource(uri.toString());
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.start();
                                }
                            });
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public int CheckSong(String song) {
        int res = 0;

        if(song.replaceAll(" ", "_").equals(currentSong.replace(".mp3", "")) && (false == guessed)) {
            String s = rewardSystem.GetSongListIndexesCategory(songType);
            String sSplit[] = s.split(", ");
            res = 1;
            guessed = true;

            for(int i=0; i<s.length();i++) {
                long charToLong = Character.getNumericValue(sSplit[i].charAt(0));
                long charToLong2 = !Character.isDigit(sSplit[i].charAt(1)) ? -1 : Character.getNumericValue(sSplit[i].charAt(1));
                charToLong = charToLong2 == -1 ? charToLong : charToLong * 10 + charToLong2;

                if(charToLong == songIndex) {
                    res = 2;
                }
            }
            if(res == 1) {
                UpdateUserRewards();
            }
        }

        return res;
    }

    private void UpdateUserRewards() {
        MainActivity.UserRef.child("experience").setValue(rewardSystem.UpdateUserExp());
        MainActivity.UserRef.child("level").setValue(rewardSystem.UpdateUserLevel());
        MainActivity.UserRef.child("totalexperience").setValue(rewardSystem.GetUserTotalExp());
        MainActivity.UserRef.child("songsguessed").setValue(rewardSystem.UpdateSongsGuessed());
        MainActivity.UserRef.child("categorysongsguessed").child(songType).setValue(rewardSystem.UpdateSongsGuessedCategory(songType));
        MainActivity.UserRef.child("achievements").child(songType).setValue(rewardSystem.UpdateAchievementsCategory(songType));
        MainActivity.UserRef.child("categorysongsguessednumbers").child(songType).setValue(rewardSystem.UpdateSongIndexesCategory(songType, songIndex, currentSong.replaceAll("_", " ").replace(".mp3", "")));
    }

    public boolean checkMediaPlayerIsPlaying() {
        boolean playing = false;
        if(mediaPlayer.isPlaying())
            playing = true;
        return playing;
    }

    public void startMediaPlayer(){
        if(!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }

    public int GetTotalNumberOfSongs() {
        return totalNumberOfSongs;
    }

}
