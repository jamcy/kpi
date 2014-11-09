package ua.kpi.eec.vml.service;

import ua.kpi.eec.vml.model.entity.Account;

public interface MoodleService {
    public Account authenticate(String username, String password) throws MoodleRequestException;
}
