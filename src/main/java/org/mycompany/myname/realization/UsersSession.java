package org.mycompany.myname.realization;

import org.mycompany.myname.accounts.UserProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersSession {
    public static Map<String, UserProfile> sessions= new HashMap<>();
    public static void addSession(String sessionId, UserProfile userProfile){
        sessions.put(sessionId, userProfile);
    }

    public static UserProfile getUserBySessionId(String sessionId){
        return sessions.get(sessionId);
    }

    public static void deleteSession(String sessionId){
        if(getUserBySessionId(sessionId)!=null)
            sessions.remove(sessionId);
    }
}
