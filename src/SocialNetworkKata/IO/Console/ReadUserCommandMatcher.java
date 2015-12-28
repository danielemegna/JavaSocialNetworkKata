package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadUserCommandMatcher implements CommandMatcher {


    private static final String READ_USER_COMMAND_REGEX = "(.+)";
    private Matcher matcher;

    public boolean match() {
        return matcher.matches();
    }

    public void execute(ISocialNetwork socialNetwork) {
        String username = matcher.group(1);
        socialNetwork.reading(username);
    }

    public void compile(String command) {
        matcher = Pattern
            .compile(READ_USER_COMMAND_REGEX)
            .matcher(command);
    }
}
