package com.example.guessmysong.database;

public enum EMaleDatabaseData implements IDatabaseData {
    KING_OF_MUSIC,
    KING_OF_JAZZ,
    KING_OF_METAL,
    KING_OF_ROCK;

    @Override
    public String getAchievementName() {
        return this.toString().substring(0, 1).toUpperCase() + this.toString().substring(1).toLowerCase().replaceAll("_", " ");
    }
}
