package br.ufg.inf.fs.business;

import java.util.ResourceBundle;

public class PostsSystems {
    private static final ResourceBundle POST_SYSTEM = ResourceBundle.getBundle("system");

    public static String getPost(String post) {
        if (POST_SYSTEM.containsKey(post)) {
            return POST_SYSTEM.getString(post);
        }

        return "";
    }

    public static String getPostDB() {
        String db = POST_SYSTEM.getString("dburl") + ", " + POST_SYSTEM.getString("user") + ", " + POST_SYSTEM.getString("password");
        return db;  
    }
}