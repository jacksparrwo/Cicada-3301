package com.example.guessmysong.firebase.database;

import androidx.annotation.NonNull;

import com.example.guessmysong.MainActivity;
import com.example.guessmysong.firebase.storage.EMusicTypes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserRewardSystem {

    /*
    0 - christmas
    1 - kids
    2 - love
    3 - oldie
    4 - party
    5 - sad
    6 - shower
    7 - summer
     */
    private final long BASE_EXP = 300;
    private final long LVL_MULTIPLIER = 50;
    private final long SONG_EXP = 50;
    private final long ACHIEVEMENT_UPLINE = 5;
    private final long ACHIEVEMENT_GRADE1 = 1;
    private final long ACHIEVEMENT_GRADE2 = 2;
    private final long ACHIEVEMENT_GRADE3 = 3;
    private final long ACHIEVEMENT_GRADE4 = 4;
    private long userExp;
    private long userLevel;
    private long songsGuessed;
    private long []categorySongsGuessed = new long[8];
    private ArrayList<String> achievements = new ArrayList<>(8);
    private static UserRewardSystem mUserRwSysInstance = null;

    private UserRewardSystem() {
        this.userExp = 0;
        this.userLevel = 0;
        this.songsGuessed = 0;

        for(int i=0; i<8; i++) {
            this.categorySongsGuessed[i] = 0;
        }

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

        MainActivity.UserRef.child("songsguessed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                songsGuessed = (Long) snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MainActivity.UserRef.child("categorysongsguessed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;

                for(DataSnapshot child: snapshot.getChildren()) {
                    categorySongsGuessed[i] = (long) child.getValue();
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MainActivity.UserRef.child("achievements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child: snapshot.getChildren()) {
                    String rez = (String) child.getValue();
                    achievements.add(rez);
                }
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

    public long UpdateSongsGuessed() {
        songsGuessed += 1;
        return songsGuessed;
    }

    public long UpdateSongsGuessedCategory(String category) {
        int catNum = 0;
        if(category.equals(EMusicTypes.CHRISTMAS.getName())) {
            catNum = 0;
        } else if(category.equals(EMusicTypes.KIDS.getName())) {
            catNum = 1;
        } else if(category.equals(EMusicTypes.LOVE.getName())) {
            catNum = 2;
        } else if(category.equals(EMusicTypes.OLDIE.getName())) {
            catNum = 3;
        } else if(category.equals(EMusicTypes.PARTY.getName())) {
            catNum = 4;
        } else if(category.equals(EMusicTypes.SAD.getName())) {
            catNum = 5;
        } else if(category.equals(EMusicTypes.SHOWER.getName())) {
            catNum = 6;
        } else if(category.equals(EMusicTypes.SUMMER.getName())) {
            catNum = 7;
        }

        this.categorySongsGuessed[catNum] += 1;
        return this.categorySongsGuessed[catNum];
    }

    public String UpdateAchievementsCategory(String category) {
        int catNum = 0;
        if(category.equals(EMusicTypes.CHRISTMAS.getName())) {
            catNum = 0;
        } else if(category.equals(EMusicTypes.KIDS.getName())) {
            catNum = 1;
        } else if(category.equals(EMusicTypes.LOVE.getName())) {
            catNum = 2;
        } else if(category.equals(EMusicTypes.OLDIE.getName())) {
            catNum = 3;
        } else if(category.equals(EMusicTypes.PARTY.getName())) {
            catNum = 4;
        } else if(category.equals(EMusicTypes.SAD.getName())) {
            catNum = 5;
        } else if(category.equals(EMusicTypes.SHOWER.getName())) {
            catNum = 6;
        } else if(category.equals(EMusicTypes.SUMMER.getName())) {
            catNum = 7;
        }

        if(this.categorySongsGuessed[catNum] >= ACHIEVEMENT_UPLINE * ACHIEVEMENT_GRADE4) {
            if(!this.achievements.get(catNum).contains(EAchievements.values()[catNum*4+3].getName())) {
                this.achievements.set(catNum, this.achievements.get(catNum) + ", " + EAchievements.values()[catNum * 4 + 3].getName());
            }
        } else if(this.categorySongsGuessed[catNum] >= ACHIEVEMENT_UPLINE * ACHIEVEMENT_GRADE3) {
            if(!this.achievements.get(catNum).contains(EAchievements.values()[catNum*4+2].getName())) {
                this.achievements.set(catNum, this.achievements.get(catNum) + ", " + EAchievements.values()[catNum * 4 + 2].getName());
            }
        } else if(this.categorySongsGuessed[catNum] >= ACHIEVEMENT_UPLINE * ACHIEVEMENT_GRADE2) {
            if(!this.achievements.get(catNum).contains(EAchievements.values()[catNum*4+1].getName())) {
                this.achievements.set(catNum, this.achievements.get(catNum) + ", " + EAchievements.values()[catNum * 4 + 1].getName());
            }
        } else if(this.categorySongsGuessed[catNum] >= ACHIEVEMENT_UPLINE * ACHIEVEMENT_GRADE1) {
            if(!this.achievements.get(catNum).contains(EAchievements.values()[catNum*4+0].getName())) {
                this.achievements.set(catNum, this.achievements.get(catNum) + EAchievements.values()[catNum * 4 + 0].getName());
            }
        }

        return this.achievements.get(catNum);
    }
}
