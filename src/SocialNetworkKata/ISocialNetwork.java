package SocialNetworkKata;

public interface ISocialNetwork {
    void reading(String username);
    void wall(String username);
    void post(String username, String message);
    void subscribe(String follower, String followed);
}
