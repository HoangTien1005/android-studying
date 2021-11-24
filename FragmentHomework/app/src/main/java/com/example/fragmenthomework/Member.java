package com.example.fragmenthomework;

import java.util.ArrayList;
import java.util.List;

public class Member {

    public static List<Member> MEMBERS = new ArrayList<>();
    public String name;
    public String id;
    public String classroom;
    public double score;

    public Member(String name, String id, String classroom, float score) {
        this.name = name;
        this.id = id;
        this.classroom = classroom;
        this.score = score;
    }
}

