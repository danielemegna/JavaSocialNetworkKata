package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;
import SocialNetworkKata.PostPrinter.TimelinePostPostPrinter;
import SocialNetworkKata.PostPrinter.WallPostPostPrinter;
import SocialNetworkKata.Repositories.PostRepository;
import SocialNetworkKata.Repositories.SubscriptionRepository;

import java.util.Collection;
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
        List<Post> posts = postRepository.getByUsername(username);
        new TimelinePostPostPrinter(output).print(posts, clock.now());
    }

    public void wall(String reader) {
        Collection<String> followed = subscriptionRepository.getFollowed(reader);
        List<Post> posts = postRepository.getForWall(reader, followed);
        new WallPostPostPrinter(output).print(posts, clock.now());
    }

    public void post(String username, String message) {
        postRepository.add(username, message, clock.now());
    }

    public void subscribe(String follower, String followed) {
       subscriptionRepository.add(follower, followed);
    }
}
