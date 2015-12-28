package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

public class ReadUserCommandMatcher extends CommandMatcher {

    public void execute(ISocialNetwork socialNetwork) {
        String username = matcher.group(1);
        socialNetwork.reading(username);
    }

    protected String commandRegex() {
        return "(.+)";
    }


}
