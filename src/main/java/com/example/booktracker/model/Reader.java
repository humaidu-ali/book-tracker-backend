package com.example.booktracker.model;

import javax.persistence.*;

@Entity
@Table(name = "readers")
public class Reader {
    private Integer id;
    private String name;
    private Integer weeklyGoal;
    private Integer minutesRead;

    public Reader(){

    }

    public Reader(Integer id, String name, Integer weeklyGoal, Integer minutesRead) {
        this.id = id;
        this.name = name;
        this.weeklyGoal = weeklyGoal;
        this.minutesRead = minutesRead;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "weekly_goal", nullable = false)
    public Integer getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(Integer weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    @Column(name = "minutesRead", nullable = false)
    public Integer getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(Integer minutesRead) {
        this.minutesRead = minutesRead;
    }

    @Override
    public String toString() {
        return "Reader [id=" + id + ", name=" + name + ", weeklyGoal=" + weeklyGoal + ", minutesRead=" + minutesRead
                + "]";
    }
}
