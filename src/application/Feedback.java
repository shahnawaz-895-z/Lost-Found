package application;

public class Feedback {
    private String username;
    private String feedbackText;

    public Feedback(String username, String feedbackText) {
        this.username = username;
        this.feedbackText = feedbackText;
    }

    public String getUsername() {
        return username;
    }

    public String getFeedbackText() {
        return feedbackText;
    }
}
