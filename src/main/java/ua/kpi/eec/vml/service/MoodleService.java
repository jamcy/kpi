package ua.kpi.eec.vml.service;

import ua.kpi.eec.vml.model.dto.AccountData;

public interface MoodleService {
    public AccountData authenticate(String username, String password) throws MoodleRequestException;
}
