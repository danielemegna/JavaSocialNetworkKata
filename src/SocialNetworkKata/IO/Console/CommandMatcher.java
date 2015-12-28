package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

public interface CommandMatcher {
    void compile(String command);
    boolean match();
    void execute(ISocialNetwork socialNetwork);
}
