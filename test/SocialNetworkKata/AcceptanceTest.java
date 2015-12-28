package SocialNetworkKata;

import SocialNetworkKata.IO.OutputAdapter;

import SocialNetworkKata.Repositories.InMemoryPostRepository;
import SocialNetworkKata.Repositories.InMemorySubscriptionRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.After;
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
            clock, outputAdapter,
            new InMemoryPostRepository(),
            new InMemorySubscriptionRepository()
        );
    }

    @After
    public void tearDown() {
        context.assertIsSatisfied();
    }

    @Test
    public void readingInexistentUsername_printsNoMessage() {
        setupClockAnswers(date(2015, 12, 28, 18, 38));
        socialNetwork.reading("Inexistent");
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

    @Test
    public void aliceReadsHerWall() {
        setupClockAnswers(
            date(2015, 12, 28, 16, 35, 0),
            date(2015, 12, 28, 16, 40, 0)
        );
        setupOutputAsserts(
            "Alice - I love the weather today (5 minutes ago)"
        );

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.wall("Alice");
    }

    @Test
    public void charlieSubscribesToAlicesTimelineAndReadHisWall() {
        setupClockAnswers(
            date(2015, 12, 28, 15, 50, 0),
            date(2015, 12, 28, 15, 54, 58),
            date(2015, 12, 28, 15, 55, 0)
        );
        setupOutputAsserts(
            "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)",
            "Alice - I love the weather today (5 minutes ago)"
        );

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.post("Charlie", "I'm in New York today! Anyone wants to have a coffee?");
        socialNetwork.subscribe("Charlie", "Alice");
        socialNetwork.wall("Charlie");
    }

    @Test
    public void fullStory() {
        setupClockAnswers(
            date(2015, 12, 28, 20, 00),
            date(2015, 12, 28, 20, 03),
            date(2015, 12, 28, 20, 04),

            date(2015, 12, 28, 20, 05),
            date(2015, 12, 28, 20, 05),

            date(2015, 12, 28, 20, 05, 10),

            date(2015, 12, 28, 20, 05, 12),
            date(2015, 12, 28, 20, 05, 25)
        );

        setupOutputAsserts(
            "I love the weather today (5 minutes ago)",

            "Good game though. (1 minute ago)",
            "Damn! We lost! (2 minutes ago)",

            "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)",
            "Alice - I love the weather today (5 minutes ago)",

            "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)",
            "Bob - Good game though. (1 minute ago)",
            "Bob - Damn! We lost! (2 minutes ago)",
            "Alice - I love the weather today (5 minutes ago)"
        );

        socialNetwork.post("Alice", "I love the weather today");
        socialNetwork.post("Bob", "Damn! We lost!");
        socialNetwork.post("Bob", "Good game though.");

        socialNetwork.reading("Alice");
        socialNetwork.reading("Bob");

        socialNetwork.post("Charlie", "I'm in New York today! Anyone wants to have a coffee?");

        socialNetwork.subscribe("Charlie", "Alice");
        socialNetwork.wall("Charlie");
        socialNetwork.subscribe("Charlie", "Bob");
        socialNetwork.wall("Charlie");
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
