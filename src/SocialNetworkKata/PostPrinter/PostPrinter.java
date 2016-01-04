package SocialNetworkKata.PostPrinter;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;

import java.util.GregorianCalendar;
import java.util.List;

public abstract class PostPrinter {
    protected final OutputAdapter outputAdapter;

    public PostPrinter(OutputAdapter outputAdapter) {
        this.outputAdapter = outputAdapter;
    }

    public abstract void print(List<Post> list, GregorianCalendar now);

    protected String formatPost(Post post, GregorianCalendar now) {
        return String.format(
            "%s (%s)",
            post.getMessage(),
            getTimeDiffString(post, now)
        );
    }

    private String getTimeDiffString(Post post, GregorianCalendar to) {
        long qta = diffInSeconds(post, to);
        String unit = "second";

        if(qta >= 60) {
            unit = "minute";
            qta = qta / 60 % 60;
        }

        if(qta > 1)
            unit += "s";

        return String.format("%d %s ago", qta, unit);
    }

    private long diffInSeconds(Post post, GregorianCalendar to) {
        long diffMillis = to.getTimeInMillis() - post.getDate().getTimeInMillis();
        return diffMillis / 1000;
    }
}
