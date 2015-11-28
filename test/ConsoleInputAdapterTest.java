import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class ConsoleInputAdapterTest {

    @Test
    public void passingUsername_willTriggerReadingCommand() {
        Mockery context = new Mockery();
        ISocialNetwork socialNetwork = context.mock(ISocialNetwork.class);
        InputAdapter consoleInputAdapter = new ConsoleInputAdapter(socialNetwork);

        context.checking(new Expectations() {{
            oneOf(socialNetwork).reading("Username");
        }});

        consoleInputAdapter.command("Username");
        context.assertIsSatisfied();
    }
}
