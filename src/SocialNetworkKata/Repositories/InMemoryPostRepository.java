package SocialNetworkKata.Repositories;

import SocialNetworkKata.Model.Post;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class InMemoryPostRepository implements PostRepository {

    private List<Post> posts;

    public InMemoryPostRepository() {
        this.posts = new ArrayList<>();
    }

    @Override
    public List<Post> getByUsername(String username) {
        return this.posts.stream()
            .filter(post -> post.getUsername() == username)
            .sorted(comparing(Post::getDate).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public void add(String username, String message, GregorianCalendar date) {
        this.posts.add(new Post(username, message, date));
    }
}
