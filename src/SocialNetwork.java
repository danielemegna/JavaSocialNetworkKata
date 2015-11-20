public class SocialNetwork {
    private final OutputAdapter output;

    public SocialNetwork(OutputAdapter output) {
        this.output = output;
    }

    public void reading(String username) {
        output.printNewMessage("");
    }
}
