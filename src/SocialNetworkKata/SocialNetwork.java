package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;
import SocialNetworkKata.Repositories.PostRepository;
import java.util.GregorianCalendar;
import java.util.List;

public class SocialNetwork implements ISocialNetwork {
    private final Clock clock;
    private final OutputAdapter output;
    private final PostRepository postRepository;


    public SocialNetwork(Clock clock, OutputAdapter output, PostRepository postRepository) {
        this.clock = clock;
        this.output = output;
        this.postRepository = postRepository;
    }

    public void reading(String username) {

        List<Post> found = postRepository.getByUsername(username);
        if(found.size() == 0) {
            output.printNewMessage("");
            return;
        }

        GregorianCalendar now = clock.now();
        for(Post post : found) {
            String postToString = post.toString(now);
            output.printNewMessage(postToString);
        }
    }

    public void post(String username, String message) {
        postRepository.add(username, message, clock.now());
    }

    public void wall(String username) {
        GregorianCalendar now = clock.now();
        List<Post> posts = postRepository.getByUsername(username);
        for(Post post : posts)
            output.printNewMessage(post.toStringWithAuthor(now));
    }
}
