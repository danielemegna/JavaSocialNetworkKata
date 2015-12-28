package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

public class PostMessageCommandMatcher extends CommandMatcher {

    public void execute(ISocialNetwork socialNetwork) {
        String username = matcher.group(1);
        String message = matcher.group(2);
        socialNetwork.post(username, message);
    }

    protected String commandRegex() {
        return "(.+) -> (.+)";
    }
}
