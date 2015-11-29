package SocialNetworkKata;

import SocialNetworkKata.IO.ConsoleInputAdapter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

public class ConsoleInputAdapterTest {

    private Mockery context;
    private ISocialNetwork socialNetwork;
    private ConsoleInputAdapter consoleInputAdapter;

    @Before
    public void setUp() {
        context = new Mockery();
        socialNetwork = context.mock(ISocialNetwork.class);
        consoleInputAdapter = new ConsoleInputAdapter(socialNetwork);
    }

    @Test
    public void passingUsername_willTriggerReadingCommand() {
        context.checking(new Expectations() {{
            oneOf(socialNetwork).reading("Username");
        }});

        consoleInputAdapter.command("Username");
        context.assertIsSatisfied();
    }

    @Test
    public void postAMessage() {
        context.checking(new Expectations() {{
            oneOf(socialNetwork).post("Username", "This is the message");
        }});

        consoleInputAdapter.command("Username -> This is the message");
        context.assertIsSatisfied();
    }
}
