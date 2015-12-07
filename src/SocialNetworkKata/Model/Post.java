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

    public String toString(GregorianCalendar now) {
        return String.format(
            "%s (%s)",
            getMessage(),
            getTimeDiffString(now)
        );
    }

    private String getTimeDiffString(GregorianCalendar to) {
        String unit = "minute";
        long qta = diffInMinutes(to);

        if(qta == 0) {
            qta = diffInSeconds(to);
            unit = "second";
        }

        if(qta > 1)
            unit += "s";

        return String.format("%d %s ago", qta, unit);
    }

    private long diffInMinutes(GregorianCalendar to) {
        return diffInSeconds(to) / 60 % 60;
    }

    private long diffInSeconds(GregorianCalendar to) {
        long diffMillis = to.getTimeInMillis() - getDate().getTimeInMillis();
        return diffMillis / 1000;
    }
}
