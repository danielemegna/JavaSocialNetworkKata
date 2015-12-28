package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

public class ReadWallCommandMatcher extends CommandMatcher {

    public void execute(ISocialNetwork socialNetwork) {
        String username = matcher.group(1);
        socialNetwork.wall(username);
    }

    protected String commandRegex() {
        return "(.+) wall";
    }
}
