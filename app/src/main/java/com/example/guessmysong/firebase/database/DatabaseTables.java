package com.example.guessmysong.firebase.database;

import com.example.guessmysong.firebase.IDatabaseData;

public enum DatabaseTables implements IDatabaseData {
    USERS,
    SONGS;

    @Override
    public String getName() {
        return this.toString().toLowerCase().replaceAll("_", "-");
    }
}
