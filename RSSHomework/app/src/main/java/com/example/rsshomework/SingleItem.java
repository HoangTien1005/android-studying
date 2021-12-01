package com.example.rsshomework;

public class SingleItem {
    private String pubDate;
    private String title;
    private String description;
    private String link;
    public SingleItem(String _pubDate, String _title, String _description, String _link) {
        pubDate = _pubDate;
        description = _description;
        title = _title;
        link = _link;
    }

    public String getPubDate() { return pubDate; }

    public String getTitle() { return title;}

    public String getDescription() { return description; }

    public String getLink() { return link; }

    @Override
    public String toString() { return title; }
}
