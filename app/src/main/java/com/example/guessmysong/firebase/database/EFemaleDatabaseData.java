package com.example.guessmysong.firebase.database;

import com.example.guessmysong.firebase.IDatabaseData;

public enum EFemaleDatabaseData implements IDatabaseData {
    QUEEN_OF_MUSIC,
    QUEEN_OF_JAZZ,
    QUEEN_OF_METAL,
    QUEEN_OF_ROCK;

    @Override
    public String getName() {
        return this.toString().substring(0, 1).toUpperCase() + this.toString().substring(1).toLowerCase().replaceAll("_", " ");
    }
}
