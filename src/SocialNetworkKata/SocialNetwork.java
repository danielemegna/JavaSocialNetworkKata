package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Model.Post;
import SocialNetworkKata.Repositories.PostRepository;
import SocialNetworkKata.Repositories.SubscriptionRepository;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

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
        GregorianCalendar now = clock.now();
        List<Post> found = postRepository.getByUsername(username);
        for(Post post : found) {
            output.printNewMessage(post.toString(now));
        }
    }

    public void post(String username, String message) {
        postRepository.add(username, message, clock.now());
    }

    public void wall(String reader) {
        GregorianCalendar now = clock.now();

        List<Post> readerPosts = postRepository.getByUsername(reader);

        List<Post> followedPosts = new ArrayList<>();
        for(String followed : subscriptionRepository.getFollowed(reader))
            followedPosts.addAll(postRepository.getByUsername(followed));

        List<Post> merged = new ArrayList<>();
        merged.addAll(readerPosts);
        merged.addAll(followedPosts);

        merged = merged.stream()
            .sorted(comparing(Post::getDate).reversed())
            .collect(Collectors.toList());

        for(Post post : merged)
            output.printNewMessage(post.toStringWithAuthor(now));
    }

    public void subscribe(String follower, String followed) {
       subscriptionRepository.add(follower, followed);
    }
}
