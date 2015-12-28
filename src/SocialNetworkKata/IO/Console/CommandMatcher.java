package SocialNetworkKata.IO.Console;

import SocialNetworkKata.ISocialNetwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandMatcher {

    protected Matcher matcher;

    public boolean match() {
        return matcher.matches();
    }

    public void compile(String command) {
        matcher = Pattern
            .compile(commandRegex())
            .matcher(command);
    }

    public abstract void execute(ISocialNetwork socialNetwork);
    protected abstract String commandRegex();
}
