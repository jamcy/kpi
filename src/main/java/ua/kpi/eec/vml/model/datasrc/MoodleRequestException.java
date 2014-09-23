package ua.kpi.eec.vml.model.datasrc;

public class MoodleRequestException extends Exception {

    public MoodleRequestException(String message) {
        super(message);
    }

    public MoodleRequestException(Exception e) {
        super(e);
    }

}
