package com.example.fragmenthomework;

public class Member {
    public static Member[] MEMBERS = {
        new Member("Nguyễn Minh Phụng", "19120622", "19CTT4", 8.5),
                new Member("Nguyễn Đức Phát Tài", "19120641", "19CTT3", 7.5),
                new Member("Lê Đức Tâm", "1912064", "19CTT2", 6.5),
                new Member("Nguyễn Hoàng Tiến", "19120678", "19CTT1", 5.5)
    };
    public String name;
    public String id;
    public String classroom;
    public double score;

    public Member(String name, String id, String classroom, double score) {
        this.name = name;
        this.id = id;
        this.classroom = classroom;
        this.score = score;
    }
}

