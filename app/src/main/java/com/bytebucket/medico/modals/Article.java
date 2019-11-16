package com.bytebucket.medico.modals;

public class Article {
    String author, description, heading, dfuid, postdate;

    public Article() {
    }

    public Article(String author, String description, String heading, String dfuid, String postdate) {
        this.author = author;
        this.description = description;
        this.heading = heading;
        this.dfuid = dfuid;
        this.postdate = postdate;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getDfuid() {
        return dfuid;
    }

    public void setDfuid(String dfuid) {
        this.dfuid = dfuid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
