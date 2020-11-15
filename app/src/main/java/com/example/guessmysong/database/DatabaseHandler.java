package com.example.guessmysong.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseHandler {
    private DatabaseReference mDatabase;
    private DatabaseHandler mDBInstance = null;

    private DatabaseHandler() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseHandler getDatabase() {
        if(null == mDBInstance) {
            mDBInstance = new DatabaseHandler();
        }

        return mDBInstance;
    }
}
