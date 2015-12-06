package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class SocialNetwork implements ISocialNetwork {
    private final Clock clock;
    private final OutputAdapter output;

    private List<Post> posts = new ArrayList<Post>();

    public SocialNetwork(Clock clock, OutputAdapter output) {
        this.clock = clock;
        this.output = output;
    }

    public void reading(String username) {

        List<Post> found = postsOf(username);
        if(found.size() == 0) {
            output.printNewMessage("");
            return;
        }

        GregorianCalendar now = clock.now();
        for(Post post : found) {
            long diffInMinutes = post.diffInMinutes(now);
            String message = String.format(
                "%s (%d %s ago)",
                post.getMessage(),
                diffInMinutes,
                diffInMinutes > 1 ? "minutes" : "minute"
            );
            output.printNewMessage(message);
        }
    }

    private List<Post> postsOf(String username) {
        return this.posts.stream()
            .filter(post -> post.getUsername() == username)
            .sorted(comparing(Post::getDate).reversed())
            .collect(Collectors.toList());
    }

    public void post(String username, String message) {
        this.posts.add(new Post(username, message, clock.now()));
    }
}
