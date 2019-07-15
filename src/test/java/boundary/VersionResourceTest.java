package boundary;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.logging.Logger;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import com.java.ee.boundary.VersionResource;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
// import org.junit.Test;

/**
 * VersionResourceTest
 */
public class VersionResourceTest extends JerseyTest {

    protected Application configure() {
        ResourceConfig config = new ResourceConfig(VersionResource.class);

        // this here is required for dependenci injection
        config.register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(Logger.getAnonymousLogger());
            }
        });
        return config;
    }

    protected void configureClient(ClientConfig config) {
        config.register(JsonBindingFeature.class);
    }

    // @Test
    public void v1() {
        Response response = target("/version/v1").request().get();

        assertThat(response.getStatus(), is (200));
        assertThat(response.readEntity(String.class), is("v1.0"));

    }

    // @Test
    public void v2() {
        Response response = target("/version/v2").request().get();

        assertThat(response.getStatus(), is(200));
        assertThat(response.readEntity(String.class), is("v2.0"));

    }

}