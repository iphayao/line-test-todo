package com.iphayao.linetest.todo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private TodoType type;
    private String userId;
    private String date;
    private String time;
    private String action;
    private boolean done;

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", type=" + type +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
