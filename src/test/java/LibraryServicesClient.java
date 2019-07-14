import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.java.ee.domain.Author;
import com.java.ee.domain.Book;

import org.glassfish.jersey.jsonb.JsonBindingFeature;

/**
 * LibraryServicesClient
 */
public class LibraryServicesClient {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    public static void main(String[] args) {

        // Construct a JAX-RS Client using the Builder
        Client client = ClientBuilder
                            .newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .register(JsonBindingFeature.class)
                            .build();
        // Construct a web target
        WebTarget api = client.target("http://localhost:8080").path("/app/api");

        LOGGER.log(Level.INFO,"Get List of Books");
        List<Book> books = api
                            .path("/books")
                            .request()
                            .accept(MediaType.APPLICATION_JSON)
                            .get(bookList());
        books.forEach(book -> LOGGER.log(Level.INFO, "{0}",book));

        //  unknownbook
        LOGGER.log(Level.INFO, "Get Unknown book by ISBN");
        Response response = api
                                .path("/books")
                                .path("/{isbn}")
                                .resolveTemplate("isbn", "12356")
                                .request()
                                .accept(MediaType.APPLICATION_JSON)
                                .get();
        assert response.getStatus() == 404;


        // Create a new Book
        Book book = new Book("98765432","Building Web Services with Java 8");
        Author autor = new Author();
        autor.setName("M.-Leader Reimer");
        book.setAuthor(autor);

        LOGGER.log(Level.INFO, "Creating new {0}.", book);
        response = api
                    .path("/books")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(book));
        assert response.getStatus() == 201;

        URI bookUri = response.getLocation();
        LOGGER.log(Level.INFO, "Get Created Book with URI {0}.",bookUri);
        Book createdBook = client
                                .target(bookUri)
                                .request()
                                .accept(MediaType.APPLICATION_JSON)
                                .get(Book.class);
        assert book.equals(createdBook);

        // Delete Book
        LOGGER.log(Level.INFO, "DELETE Book with URI {0},",bookUri);
        response = client
                        .target(bookUri)
                        .request()
                        .delete();

        assert response.getStatus() == 200;


        client.close();
    }

    private static GenericType<List<Book>> bookList(){
        return new GenericType<List<Book>>(){

        };
    }
    
}