package com.example.guessmysong.firebase.database;

import androidx.annotation.NonNull;

import com.example.guessmysong.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class UserRewardSystem {
    private final long BASE_EXP = 300;
    private final long LVL_MULTIPLIER = 50;
    private final long SONG_EXP = 50;
    private long userExp;
    private long userLevel;
    private static UserRewardSystem mUserRwSysInstance = null;

    private UserRewardSystem() {
        this.userExp = 0;
        this.userLevel = 0;

        MainActivity.UserRef.child("experience").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userExp = (Long) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MainActivity.UserRef.child("level").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userLevel = (Long) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static UserRewardSystem getInstance() {
        if(null == mUserRwSysInstance) {
            mUserRwSysInstance = new UserRewardSystem();
        }

        return mUserRwSysInstance;
    }

    public long GetUserMaxExpForLvl() {
        long maxExp = 0;
        maxExp = BASE_EXP + userLevel * LVL_MULTIPLIER;
        return maxExp;
    }

    public long GetUserTotalExp() {
        long maxpExp = 0;
        maxpExp = userLevel * BASE_EXP + LVL_MULTIPLIER * (1 + userLevel) * userLevel / 2 + userExp;
        return maxpExp;
    }

    public long UpdateUserExp() {
        long crtExp = userExp + SONG_EXP;

        if(crtExp >= GetUserMaxExpForLvl()) {
            crtExp %= GetUserMaxExpForLvl();
            userLevel+=1;
        }
        userExp = crtExp;

        return crtExp;
    }

    public long UpdateUserLevel() {
        return userLevel;
    }
}
