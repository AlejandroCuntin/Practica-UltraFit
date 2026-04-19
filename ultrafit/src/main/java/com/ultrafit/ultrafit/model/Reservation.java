package com.ultrafit.ultrafit.model;

public class Reservation {
    //in the reservation we are gonna need the id of the member and the id of the trainer, and also
    //one id that identify the reservation to after put it in some data base
    private Long id;
    private Long memberId;
    private Long trainerId;
    private String date;
    private String time;
    private String level;
    private String username;
    //Now he are goona do the same thing that we do to member and trainer
    public Reservation() {}

    public Reservation(Long id, Long memberId, Long trainerId,
                       String date, String time, String level) {
        this.id = id;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.date = date;
        this.time = time;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLevel() {
        return level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
