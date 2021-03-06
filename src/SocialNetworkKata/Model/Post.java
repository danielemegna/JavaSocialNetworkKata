package SocialNetworkKata.Model;

import java.util.GregorianCalendar;

public class Post {
    private final String username;
    private final String message;
    private final GregorianCalendar date;

    public Post(String username, String message, GregorianCalendar date) {
        this.username = username;
        this.message = message;
        this.date = date;
    }

    public String getUsername() {
        return this.username;
    }

    public String getMessage() {
        return message;
    }

    public GregorianCalendar getDate() {
        return date;
    }

}
