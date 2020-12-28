package com.example.guessmysong.firebase.storage;

import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.guessmysong.firebase.IDatabaseData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class StorageHandler {

    private StorageReference mStorage;
    private static StorageHandler mStorageInstance = null;
    private List<StorageReference> mItem = null;
    private String currentSong = null;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    private StorageHandler() {
        mStorage = FirebaseStorage.getInstance("gs://universityproject-2b5cd.appspot.com").getReference();
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

    public void PlayRandomSong(final String type) {
        InitMediaPlayer();
        final StorageReference currentFolder = mStorage.child(type);

        currentFolder.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                int numberOfItems = listResult.getItems().size();
                Random random = new Random();
                int index = random.nextInt(numberOfItems);
                String path = listResult.getItems().get(index).toString();
                currentSong = path.substring("gs://universityproject-2b5cd.appspot.com".length() + "/".length() + type.length() + "/".length());
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

    public boolean CheckSong(String song) {
        boolean res = false;

        if(song.replaceAll(" ", "_").equals(currentSong.replace(".mp3", ""))) {
            res = true;
        }

        return res;
    }
}
