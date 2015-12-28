package SocialNetworkKata;

public interface ISocialNetwork {
    void reading(String username);
    void post(String username, String message);
    void wall(String username);
}
