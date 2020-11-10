package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {


    //@PersistenceUnit(unitName = "techblog")
    //private EntityManagerFactory emf;

    @Autowired
    private PostRepository repository;

    public PostService(){System.out.println("*** PostService ***");}


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
    //public ArrayList<Post> getAllPosts(){  //using without JDBC

    public List<Post> getAllPosts() {  //with JDBC
        //ArrayList<Post> posts = new ArrayList<>(); //without JPA
        return repository.getAllPosts(); //wth JPA
    }

        /** //without JDBC connection and without JPA, all the posts are created here rather than referring from database
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
         */

        /** //using JPA hibernate
         *
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("select p from Post p", Post.class);
        List<Post> resultList = query.getResultList();
         return resultList;
         }
         */

        /** //JDBC program to fetch records from table without JPA
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver"); //if the driver class is not found, ClassNotFoundException occurs

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres","postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from posts");
            while(rs.next()){
                Post post = new Post();
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                posts.add(post);
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return posts;
         }
         */


        public Post getOnePost() {
            return repository.getLatestPost();
        }

/**

    public ArrayList<Post> getOnePost() {
        ArrayList<Post> posts = new ArrayList<>();
//        Post post1 = new Post();
//        post1.setTitle("This is your Post");
//        post1.setBody("This is your Post Body: It has some valid content");
//        post1.setDate(new Date());
//        posts.add(post1);

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver"); //if the driver class is not found, ClassNotFoundException occurs

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres","postgres");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from posts where id=4");
            while(rs.next()){
                Post post = new Post();
                //rs.getSt
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                posts.add(post);
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return posts;
    }
 */

    //public void createPost (Post newPost){
    //}

    public void createPost(Post newPost) {
        newPost.setDate(new Date());
        repository.createPost(newPost);
        System.out.println("New Post: "+newPost);
    }

    public Post getPost(Integer postId) {
        return repository.getPost(postId);
    }

    public void updatePost(Post updatedPost) {
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId) {
        repository.deletePost(postId);
    }

}
