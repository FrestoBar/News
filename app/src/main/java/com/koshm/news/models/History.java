package com.koshm.news.models;

import androidx.annotation.Nullable;

public class History {
    private int id;
    private String title;
    private String url;

    public History(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof History) {
            History history = (History) obj;
            return history.title.equals(title) && history.url.equals(url);
        }
        return false;
    }
}
