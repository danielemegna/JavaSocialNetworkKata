package SocialNetworkKata.Repositories;

import java.util.Collection;

public interface SubscriptionRepository {
    void add(String follower, String followed);
    Collection<String> getFollowed(String username);
}
