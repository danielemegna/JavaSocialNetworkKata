import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

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

        for(Post post : found) {
            String message = String.format(
                "%s (%d minutes ago)",
                post.getMessage(),
                post.diffInMinutes(clock.now())
            );
            output.printNewMessage(message);
        }
    }

    private List<Post> postsOf(String username) {
        return this.posts.stream()
                .filter(post -> post.getUsername() == username)
                .collect(Collectors.toList());
    }

    public void post(String username, String message) {
        this.posts.add(new Post(username, message, clock.now()));
    }
}
