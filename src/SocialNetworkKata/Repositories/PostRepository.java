package SocialNetworkKata.Repositories;

import SocialNetworkKata.Model.Post;

import java.util.GregorianCalendar;
import java.util.List;

public interface PostRepository {
    List<Post> getByUsername(String username);

    void add(String username, String message, GregorianCalendar date);
}
