package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostMessageCommandMatcher implements CommandMatcher {

    private static final String POST_MESSAGE_COMMAND_REGEX = "(.+) -> (.+)";
    private Matcher matcher;

    public boolean match() {
        return matcher.matches();
    }

    public void execute(ISocialNetwork socialNetwork) {
        String username = matcher.group(1);
        String message = matcher.group(2);
        socialNetwork.post(username, message);
    }

    public void compile(String command) {
        matcher = Pattern
        .compile(POST_MESSAGE_COMMAND_REGEX)
        .matcher(command);
    }
}
