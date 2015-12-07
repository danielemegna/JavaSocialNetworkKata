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

    public void post(String username, String message) {
        postRepository.add(username, message, clock.now());
    }
}
