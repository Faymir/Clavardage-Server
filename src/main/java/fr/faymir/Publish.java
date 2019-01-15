package fr.faymir;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "test" path)
 */
@Path("publish")
public class Publish {
    private static int a = 0;
    private int b = 0;
    @Inject
    private javax.inject.Provider<org.glassfish.grizzly.http.server.Request> grizzlyRequest;
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        b++;
        count();
        return "A = " + a + " b = " + b + "\tGot it! " + grizzlyRequest.get().getRemoteAddr();
    }

    private static void count(){
        a++;
    }
}
