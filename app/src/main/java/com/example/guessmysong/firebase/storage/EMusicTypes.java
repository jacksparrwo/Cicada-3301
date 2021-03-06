package com.example.guessmysong.firebase.storage;

import com.example.guessmysong.firebase.IDatabaseData;

public enum EMusicTypes implements IDatabaseData {
    ROCK,
    JAZZ,
    LOVE,
    SHOWER,
    PARTY,
    SUMMER,
    OLDIE,
    KIDS,
    SAD,
    CHRISTMAS;

    @Override
    public String getName() {
        return this.toString().toLowerCase();
    }

    public String getNameWithLyrics() {
        return this.toString().toLowerCase() + "/full";
    }

    public String getNameNoLyrics() {
        return this.toString().toLowerCase() + "/nolyrics";
    }
}
