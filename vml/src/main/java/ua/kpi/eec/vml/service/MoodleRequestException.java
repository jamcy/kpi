package ua.kpi.eec.vml.service;

public class MoodleRequestException extends Exception {
    private static final long serialVersionUID = -5527223795683162741L;

    public MoodleRequestException(Exception e) {
	super(e);
    }

    public MoodleRequestException(String message) {
	super(message);
    }

    public MoodleRequestException(String message, Exception e) {
	super(message, e);
    }
}
