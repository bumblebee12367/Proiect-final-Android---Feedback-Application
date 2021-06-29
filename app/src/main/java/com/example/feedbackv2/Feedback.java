package com.example.feedbackv2;

public class Feedback {
    public String to;
    public String from;
    public String feedback;
    public float rating;

    public Feedback() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Feedback(String to, String from, String feedback, float rating) {
        this.to = to;
        this.from = from;
        this.feedback = feedback;
        this.rating = rating;
    }
}
