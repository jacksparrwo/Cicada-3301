package com.example.guessmysong.firebase.database;

public class UserRewardSystem {
    private final int BASE_EXP = 300;
    private final int LVL_MULTIPLIER = 50;
    private int userExp;
    private int userLevel;

    public UserRewardSystem() {
        this.userExp = 0;
        this.userLevel = 0;
    }

    public int GetUserMaxExpForLvl(int userLevel) {
        int maxExp = 0;
        maxExp = BASE_EXP + userLevel * LVL_MULTIPLIER;
        return maxExp;
    }

    public int GetUserTotalExp(int userLevel) {
        int maxpExp = 0;
        maxpExp = userLevel * BASE_EXP + LVL_MULTIPLIER * (1 + userLevel) * userLevel / 2;
        return maxpExp;
    }



}
