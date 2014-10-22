package com.andreicg.solution.dailyagenda.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticationUtil {

    static Map<String, Integer> sessionMap = new ConcurrentHashMap<String, Integer>();

    
    //phony temporary method
    public static boolean isAuthenticated(String sessionId){
	return true;
    }
    
    /*
    public static boolean isAuthenticated(String sessionId) {
	if (sessionMap.containsKey(sessionId)) {
	    return true;
	}
	return false;
    }*/
    
}
