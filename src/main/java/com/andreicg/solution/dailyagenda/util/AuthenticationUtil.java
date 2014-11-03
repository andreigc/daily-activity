package com.andreicg.solution.dailyagenda.util;

import java.util.UUID;

import com.andreicg.solution.dailyagenda.model.User;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

public class AuthenticationUtil {

    static BiMap<String, User> _sMap = HashBiMap.create();
    static BiMap<String, User> sessionMap = Maps.synchronizedBiMap(_sMap);

    public static boolean isAuthenticated(String sessionId){
	return sessionMap.containsKey(sessionId);
    }
    
    public static boolean isAuthenticated(User user){
	return sessionMap.containsValue(user);
    }
    
    public static String getSessionId(User user){
	if(sessionMap.containsValue(user)){
	    return sessionMap.inverse().get(user);
	}
	return null;
    }

    public static int getUserId(String sessionId) {
	if(sessionMap.containsKey(sessionId)){
	    return sessionMap.get(sessionId).getID();
	}
	return -1;
    }
    
    public static String doLogin(User user){
	UUID uuid = UUID.randomUUID();
	String sessionId = uuid.toString();
	sessionMap.put(sessionId,user);
	return sessionId;
    }
    
}
