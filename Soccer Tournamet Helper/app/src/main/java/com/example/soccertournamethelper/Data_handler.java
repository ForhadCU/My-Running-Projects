package com.example.soccertournamethelper;

public class Data_handler {
    private String rank;
    private String Team;
    private String MP;
    private String W;
    private String D;
    private String L;
    private String GF;
    private String GA;
    private String GD;
    private String Pts;

    public Data_handler(String counter, String teamName, String match, String preWin, String preDraw, String preLoss, String preGoalFor, String preGoalAgainst, String prePoints)
    {

    }

    public Data_handler(String rank, String team, String mp, String w, String d, String l, String gf, String ga, String gd, String pts) {
        this.rank = rank;
        this.Team = team;
        this.MP = mp;
        this.W = w;
        this.D = d;
        this.L = l;
        this.GF = gf;
        this.GA = ga;
        this.GD = gd;
        this.Pts = pts;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        this.Team = team;
    }

    public String getMP() {
        return MP;
    }

    public void setMP(String MP) {
        this.MP = MP;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        this.W = w;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        this.D = d;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        this.L = l;
    }

    public String getGF() {
        return GF;
    }

    public void setGF(String GF) {
        this.GF = GF;
    }

    public String getGA() {
        return GA;
    }

    public void setGA(String GA) {
        this.GA = GA;
    }

    public String getGD() {
        return GD;
    }

    public void setGD(String GD) {
        this.GD = GD;
    }

    public String getPts() {
        return Pts;
    }

    public void setPts(String pts) {
        this.Pts = pts;
    }
}
