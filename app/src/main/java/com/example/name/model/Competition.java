package com.example.name.model;

public class Competition {
    //    private String cid; //編號
    private String title;
    private String prize; //總獎金
    private String dead_line; //比賽截止
    private String people; //觸及人數
    private String pic; //照片
    private String winner; //得獎公告

    public Competition() {
    }

    public Competition(String title, String prize, String dead_line, String people, String pic, String winner) {
//        this.cid = cid;
        this.title = title;
        this.prize = prize;
        this.dead_line = dead_line;
        this.people = people;
        this.pic = pic;
        this.winner = winner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDead_line() {
        return dead_line;
    }

    public void setDead_line(String dead_line) {
        this.dead_line = dead_line;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
