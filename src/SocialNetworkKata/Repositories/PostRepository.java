package SocialNetworkKata.Repositories;

import SocialNetworkKata.Model.Post;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public interface PostRepository {
    List<Post> getByUsername(String username);
    List<Post> getForWall(String reader, Collection<String> followed);
    void add(String username, String message, GregorianCalendar date);
}
