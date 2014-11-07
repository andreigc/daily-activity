package com.andreicg.solution.dailyagenda.json;

public class LoginResponse {

    private String sessionId;
    private UserJson user;
    
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public UserJson getUser() {
        return user;
    }
    public void setUser(UserJson user) {
        this.user = user;
    }
    
    
    
}
