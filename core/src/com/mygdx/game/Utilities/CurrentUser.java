package com.mygdx.game.Utilities;

public class CurrentUser {
    private static String currentUser;
    private static int currentUserID;

    public static void logoutCurrentUser() {
        setCurrentUserID(0);
        setCurrentUser("");
    }
    public static void setCurrentUser(String username) {
        currentUser = username;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static int getCurrentUserID() {
        return currentUserID;
    }

    public static void setCurrentUserID(int userID) {
        currentUserID = userID;
    }
}

