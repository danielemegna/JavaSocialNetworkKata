import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class AcceptanceTest {

    @Test
    public void readingInexistentUsername_printsBlankLine() {
        Mockery context = new Mockery();
        OutputAdapter outputAdapter = context.mock(OutputAdapter.class);
        SocialNetwork sn = new SocialNetwork(outputAdapter);

        context.checking(new Expectations() {{
            oneOf(outputAdapter).printNewMessage("");
        }});

        sn.reading("Alice");
        context.assertIsSatisfied();
    }
}
