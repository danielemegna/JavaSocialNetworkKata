package SocialNetworkKata;

import SocialNetworkKata.IO.Console.ConsoleInputAdapter;
import SocialNetworkKata.IO.InputAdapter;
import SocialNetworkKata.IO.OutputAdapter;
import SocialNetworkKata.Repositories.InMemoryPostRepository;
import SocialNetworkKata.Repositories.InMemorySubscriptionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) throws IOException {

        Clock clock = () -> new GregorianCalendar();
        OutputAdapter output = message -> System.out.println(message);
        SocialNetwork socialNetwork = new SocialNetwork(
            clock, output,
            new InMemoryPostRepository(),
            new InMemorySubscriptionRepository()
        );
        InputAdapter inputAdapter = new ConsoleInputAdapter(socialNetwork);

        BufferedReader reader = getBufferedReader();
        while(true)
            inputAdapter.command(reader.readLine());

    }

    private static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

}
