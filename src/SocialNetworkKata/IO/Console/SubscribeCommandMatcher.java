package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

public class SubscribeCommandMatcher extends CommandMatcher {

    public void execute(ISocialNetwork socialNetwork) {
        String follower = matcher.group(1);
        String followed = matcher.group(2);
        socialNetwork.subscribe(follower, followed);
    }

    protected String commandRegex() {
        return "(.+) follows (.+)";
    }
}
