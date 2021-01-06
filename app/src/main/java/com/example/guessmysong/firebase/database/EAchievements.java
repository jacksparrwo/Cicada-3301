package com.example.guessmysong.firebase.database;

import com.example.guessmysong.firebase.IDatabaseData;

public enum EAchievements implements IDatabaseData {
    // christmas
    SPIRIT_OF_CHRISTMAS,
    ELF,
    SANTA,
    CHRISTMAS_MASTER,

    // kids
    TOY,
    TEDDYBEAR,
    DINOSAUR,
    TOY_MASTER,

    // love
    VALENTINE,
    CUPIDON,
    DOCTOR_LOVE,
    LOVE_MASTER,

    // oldie
    VINTAGE,
    MUMMY,
    ILIESCU,
    OLDIE_MASTER,

    // party
    ANIMAL,
    PARTY_SPIRIT,
    BEERPONG_PLAYER,
    PARTY_MASTER,

    // sad
    DEPRESSED,
    COLD_HEARTED,
    SOULLESS,
    SAD_MASTER, // suicidal, i am sorry

    // shower
    WASH_AND_SING,
    SING_LIKE_A_DOVE,
    HEAD_AND_MIC,
    SHOWER_MASTER,

    // summer
    PHINEAS_AND_FERB,
    SUNKISSED,
    HAWAIIAN_SURFER,
    SUMMER_MASTER;

    @Override
    public String getName() {
        return this.toString().substring(0, 1).toUpperCase() + this.toString().substring(1).toLowerCase().replaceAll("-", " ");
    }
}
