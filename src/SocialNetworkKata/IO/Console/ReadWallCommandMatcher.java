package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadWallCommandMatcher implements CommandMatcher {

    public static final String READ_WALL_COMMAND_REGEX = "(.+) wall";
    private Matcher matcher;

    public boolean match() {
        return matcher.matches();
    }

    public void execute(ISocialNetwork socialNetwork) {
        String username = matcher.group(1);
        socialNetwork.wall(username);
    }

    public void compile(String command) {
        matcher = Pattern
            .compile(READ_WALL_COMMAND_REGEX)
            .matcher(command);
    }
}
