package fr.faymir.Model;

import java.io.Serializable;
import java.util.Vector;

public class ConnectedUsers implements Serializable {
    public static Vector<User> connectedUsers = new Vector<>();


    public static void add(User user){
        connectedUsers.add(user);
    }

    public static boolean remove(User user){
        return connectedUsers.remove(user);
    }

    public static boolean contains(String username){
        for (User connectedUser : connectedUsers) {
            if (connectedUser.getUsername().equalsIgnoreCase(username))
                return true;
        }
        return false;
    }
}
