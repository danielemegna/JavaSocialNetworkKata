public class ConsoleInputAdapter implements InputAdapter {

    private final ISocialNetwork socialNetwork;

    public ConsoleInputAdapter(ISocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public void command(String command) {
        socialNetwork.reading(command);
    }
}
