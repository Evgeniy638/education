package com.example.myapplication;

public class Bet {
    private int id;
    private String teamHome;
    private String teamGuest;
    private int betTeamHome;
    private int betTeamGuest;

    public int getId() {
        return id;
    }

    public String getTeamHome() {
        return teamHome;
    }

    public String getTeamGuest() {
        return teamGuest;
    }

    public int getBetTeamHome() {
        return betTeamHome;
    }

    public int getBetTeamGuest() {
        return betTeamGuest;
    }

    Bet(int id, String teamHome, String teamGuest, int betTeamHome, int betTeamGuest){
        this.id = id;
        this.teamHome = teamHome;
        this.teamGuest = teamGuest;
        this.betTeamHome = betTeamHome;
        this.betTeamGuest = betTeamGuest;
    }
}
