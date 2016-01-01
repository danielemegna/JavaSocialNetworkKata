package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;
import SocialNetworkKata.Repositories.PostRepository;
import SocialNetworkKata.Repositories.SubscriptionRepository;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public class SocialNetwork implements ISocialNetwork {
    private final Clock clock;
    private final OutputAdapter output;
    private final PostRepository postRepository;
    private SubscriptionRepository subscriptionRepository;


    public SocialNetwork(Clock clock, OutputAdapter output, PostRepository postRepository, SubscriptionRepository subscriptionRepository) {
        this.clock = clock;
        this.output = output;
        this.postRepository = postRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public void reading(String username) {
        printsPosts(postRepository.getByUsername(username));
    }

    public void wall(String reader) {
        Collection<String> followed = subscriptionRepository.getFollowed(reader);
        printsPostsWithAuthors(postRepository.getForWall(reader, followed));
    }

    public void post(String username, String message) {
        postRepository.add(username, message, clock.now());
    }

    public void subscribe(String follower, String followed) {
       subscriptionRepository.add(follower, followed);
    }

    private void printsPosts(List<Post> found) {
        GregorianCalendar now = clock.now();
        for(Post post : found)
            output.printNewMessage(post.toString(now));
    }

    private void printsPostsWithAuthors(List<Post> found) {
        GregorianCalendar now = clock.now();
        for(Post post : found)
            output.printNewMessage(post.toStringWithAuthor(now));
    }
}
