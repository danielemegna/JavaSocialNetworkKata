package SocialNetworkKata.IO.Console;

import SocialNetworkKata.IO.InputAdapter;
import SocialNetworkKata.ISocialNetwork;

import java.util.ArrayList;
import java.util.List;

public class ConsoleInputAdapter implements InputAdapter {

    private final ISocialNetwork socialNetwork;
    private final List<CommandMatcher> commandMatchers;

    public ConsoleInputAdapter(ISocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
        this.commandMatchers = new ArrayList<CommandMatcher>() {{
            add(new PostMessageCommandMatcher());
            add(new ReadWallCommandMatcher());
            add(new SubscribeCommandMatcher());
            add(new ReadUserCommandMatcher());
        }};
    }

    public void command(String command) {

        for(CommandMatcher matcher : commandMatchers) {
            matcher.compile(command);
            if(matcher.match()) {
                matcher.execute(socialNetwork);
                return;
            }
        }

        throw new RuntimeException("Command not found");
    }

}
