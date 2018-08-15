package com.example.shivamagarwal.noticeboard;

public class MyNotice {
    public String heading;
    public String postedon;
    public String postedby;
    public String message;

    public MyNotice() {
    }

    public MyNotice(String heading, String message, String postedon, String postedby) {
        this.heading = heading;
        this.postedon = postedon;
        this.postedby = postedby;
        this.message = message;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getPostedon() {
        return postedon;
    }

    public void setPostedon(String postedon) {
        this.postedon = postedon;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
