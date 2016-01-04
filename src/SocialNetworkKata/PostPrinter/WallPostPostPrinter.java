package SocialNetworkKata.PostPrinter;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;

import java.util.GregorianCalendar;
import java.util.List;

public class WallPostPostPrinter extends PostPrinter {

    public WallPostPostPrinter(OutputAdapter outputAdapter) {
        super(outputAdapter);
    }

    public void print(List<Post> list, GregorianCalendar now) {
        for(Post post : list)
            outputAdapter.out(
                    String.format("%s - %s", post.getUsername(), formatPost(post, now))
            );
    }
}
