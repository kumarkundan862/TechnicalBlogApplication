package technicalblog.service;

import org.springframework.stereotype.Service;
import technicalblog.model.Post;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PostService {
    /**
     * A default constructor is always created and called by the JVM in order to instantiate the object whenever a new
     object is created  and we use new keyword for creating an object.
     But here no new keyword is used rather Spring does this by the annotation @ComponentScan.
     So it scans all the components(Spring identifies the components by the annotations @Service, @Repositories)
     and add to the spring container by creating an object for each of them. And to instantiate the object, the Spring
     Container calls the default constructor.
     so here it is done by Spring Container, but to make it understand/visible we have explicitly written
     to make you aware by printing System.out.print statement.
     *
     public PostService() {
     System.out.println("****PostService Instantiated by the Spring****");
     }
     */

    public ArrayList<Post> getAllPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        post1.setTitle("Post 1");
        post1.setBody("Post Body 1");
        post1.setDate(new Date());

        Post post2 = new Post();
        post2.setTitle("Post 2");
        post2.setBody("Post Body 2");
        post2.setDate(new Date());

        Post post3 = new Post();
        post3.setTitle("Post 3");
        post3.setBody("Post Body 3");
        post3.setDate(new Date());

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        return posts;
    }
}