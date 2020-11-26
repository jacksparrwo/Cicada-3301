package com.example.guessmysong.firebase.storage;

import com.example.guessmysong.firebase.IDatabaseData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class StorageHandler {

    private StorageReference mStorage;
    private static StorageHandler mStorageInstance = null;
    private List<StorageReference> mItem = null;

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
                System.out.println("\n\n\n\n\n\n");
                System.out.println(listResult.getPrefixes());
            }
        });

    }
}
