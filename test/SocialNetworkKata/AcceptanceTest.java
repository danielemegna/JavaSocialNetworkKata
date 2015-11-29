package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

public class AcceptanceTest {

    private Mockery context;
    private OutputAdapter outputAdapter;
    private Clock clock;
    private SocialNetwork socialNetwork;

    @Before
    public void setUp() {
        context = new Mockery();
        outputAdapter = context.mock(OutputAdapter.class);
        clock = context.mock(Clock.class);

        socialNetwork = new SocialNetwork(clock, outputAdapter);
    }

    @Test
    public void readingInexistentUsername_printsBlankLine() {
        context.checking(new Expectations() {{
            oneOf(outputAdapter).printNewMessage("");
        }});

        socialNetwork.reading("Alice");
        context.assertIsSatisfied();
    }

    @Test
    public void readingAPostedMessage() {
        context.checking(new Expectations() {{
            oneOf(outputAdapter).printNewMessage("I love the weather today (5 minutes ago)");
            exactly(2).of(clock).now();
            will(onConsecutiveCalls(
                    returnValue(new GregorianCalendar(2015, 11, 29, 00, 35)),
                    returnValue(new GregorianCalendar(2015, 11, 29, 00, 40))
            ));
        }});

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.reading("Alice");

        context.assertIsSatisfied();
    }
}
