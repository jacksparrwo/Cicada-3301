package com.example.guessmysong.database;

public enum EFemaleDatabaseData implements IDatabaseData {
    QUEEN_OF_MUSIC,
    QUEEN_OF_JAZZ,
    QUEEN_OF_METAL,
    QUEEN_OF_ROCK;

    @Override
    public String getAchievementName() {
        return this.toString().substring(0, 1).toUpperCase() + this.toString().substring(1).toLowerCase().replaceAll("_", " ");
    }
}
