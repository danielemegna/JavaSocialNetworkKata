package SocialNetworkKata.Repositories;

import java.util.Set;

public interface SubscriptionRepository {
    void add(String follower, String followed);
    Set<String> getFollowed(String username);
}
