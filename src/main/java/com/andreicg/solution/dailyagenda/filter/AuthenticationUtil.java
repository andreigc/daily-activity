package com.andreicg.solution.dailyagenda.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticationUtil {

    static Map<String, Integer> sessionMap = new ConcurrentHashMap<String, Integer>();

    //hardcoded temporary method
    public static boolean isAuthenticated(String sessionId){
	return true;
    }


    //hardcoded temporary method
    public static int getUserId(String header) {
	if(header!=null)
	    return 1;
	return -1;
    }
}
