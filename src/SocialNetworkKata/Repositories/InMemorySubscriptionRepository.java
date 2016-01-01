package SocialNetworkKata.Repositories;

import java.util.*;

public class InMemorySubscriptionRepository implements SubscriptionRepository {

    private Map<String, Set<String>> subscriptions = new HashMap<>();

    public void add(String follower, String followed) {
        initFollowerIfNeeded(follower);
        storeSubscription(follower, followed);
    }

    public Collection<String> getFollowed(String username) {
        if(!knownUsername(username))
            return new HashSet<>();

        return subscriptions.get(username);
    }

    private void initFollowerIfNeeded(String username) {
        if(!knownUsername(username))
            subscriptions.put(username, new HashSet<>());
    }

    private void storeSubscription(String follower, String followed) {
        subscriptions.get(follower).add(followed);
    }

    private boolean knownUsername(String username) {
        return subscriptions.containsKey(username);
    }
}
