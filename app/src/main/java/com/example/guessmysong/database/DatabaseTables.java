package com.example.guessmysong.database;

public enum DatabaseTables implements IDatabaseData{
    USERS,
    SONGS;

    @Override
    public String getAchievementName() {
        return this.toString().toLowerCase().replaceAll("_", "-");
    }
}
