package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;

import SocialNetworkKata.Repositories.InMemoryPostRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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

        socialNetwork = new SocialNetwork(
            clock, outputAdapter, new InMemoryPostRepository()
        );
    }

    @Test
    public void readingInexistentUsername_printsBlankLine() {
        setupOutputAsserts("");

        socialNetwork.reading("Inexistent");
        context.assertIsSatisfied();
    }

    @Test
    public void readingAPostedMessage() {
        setupClockAnswers(
            date(2015, 11, 29, 00, 35),
            date(2015, 11, 29, 00, 40)
        );
        setupOutputAsserts("I love the weather today (5 minutes ago)");

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.reading("Alice");

        context.assertIsSatisfied();
    }

    @Test
    public void aliceAndBobPostingScenario() {
        setupClockAnswers(
            date(2015, 12, 06, 17, 5),
            date(2015, 12, 06, 17, 8),
            date(2015, 12, 06, 17, 9),
            date(2015, 12, 06, 17, 10),
            date(2015, 12, 06, 17, 10)
        );
        setupOutputAsserts(
            "I love the weather today (5 minutes ago)",
            "Good game though. (1 minute ago)",
            "Damn! We lost! (2 minutes ago)"
        );

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.post("Bob", "Damn! We lost!");
        socialNetwork.post("Bob", "Good game though.");

        socialNetwork.reading("Alice");
        socialNetwork.reading("Bob");
    }

    @Test
    public void readingMessageSomeSecondsAgo() {
        setupClockAnswers(
            date(2015, 12, 7, 12, 30, 0),
            date(2015, 12, 7, 12, 30, 2),
            date(2015, 12, 7, 12, 30, 3)
        );
        setupOutputAsserts(
            "It's sunny (1 second ago)",
            "I love the weather today (3 seconds ago)"
        );

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.post("Alice", "It's sunny");
        socialNetwork.reading("Alice");
    }

    private void setupOutputAsserts(String... messages) {
        context.checking(new Expectations() {{
            for(String message : messages)
                oneOf(outputAdapter).printNewMessage(message);
        }});
    }

    private void setupClockAnswers(GregorianCalendar... calendars) {
        org.jmock.api.Action[] calendarsToActions = Arrays.stream(calendars)
            .map(c -> org.jmock.Expectations.returnValue(c))
            .toArray(org.jmock.api.Action[]::new);

        context.checking(new Expectations() {{
            exactly(calendarsToActions.length).of(clock).now();
            will(onConsecutiveCalls(calendarsToActions));
        }});
    }

    private GregorianCalendar date(int y, int m, int d, int hh, int mm) {
        return new GregorianCalendar(y, m, d, hh, mm);
    }

    private GregorianCalendar date(int y, int m, int d, int hh, int mm, int ss) {
        return new GregorianCalendar(y, m, d, hh, mm, ss);
    }
}
