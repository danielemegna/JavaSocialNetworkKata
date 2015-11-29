package SocialNetworkKata.IO;

import SocialNetworkKata.ISocialNetwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInputAdapter implements InputAdapter {

    public static final String POST_MESSAGE_COMMAND_REGEX = "(.+) -> (.+)";

    private final ISocialNetwork socialNetwork;

    public ConsoleInputAdapter(ISocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public void command(String command) {

        Matcher matcher = Pattern
            .compile(POST_MESSAGE_COMMAND_REGEX)
            .matcher(command);

        if(matcher.matches()) {
            String username = matcher.group(1);
            String message = matcher.group(2);
            socialNetwork.post(username, message);
            return;
        }

        socialNetwork.reading(command);
    }
}
