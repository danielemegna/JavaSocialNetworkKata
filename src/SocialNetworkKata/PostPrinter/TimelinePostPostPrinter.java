package SocialNetworkKata.PostPrinter;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;

import java.util.GregorianCalendar;
import java.util.List;

public class TimelinePostPostPrinter extends PostPrinter {

    public TimelinePostPostPrinter(OutputAdapter outputAdapter) {
        super(outputAdapter);
    }

    public void print(List<Post> list, GregorianCalendar now) {
        for(Post post : list)
            outputAdapter.out(formatPost(post, now));
    }
}
