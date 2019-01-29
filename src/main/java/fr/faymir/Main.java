package fr.faymir;

import fr.faymir.Model.ConnectedUsers;
import fr.faymir.Model.Database;
import fr.faymir.Model.ScanMessage;
import fr.faymir.Model.ServerUser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 *
 *  * subscribe 	=> to the server
 *  * publish		=> their status
 *  * notify		=> in case of any changes
 *  *
 *
 */
//@ApplicationPath(rest") // set the path to REST web services
public class Main {
    // Base URI the Grizzly HTTP server will listen on

    public static final String BASE_URI = "http://" + ScanMessage.getIp() + ":8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in fr.faymir package
        final ResourceConfig rc = new ResourceConfig().packages("fr.faymir");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        ConnectedUsers.connectedServerUsers = Database.getInstance().selectAll();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdown();
        for(ServerUser u: ConnectedUsers.connectedServerUsers){
            if(Database.getInstance().checkUserExist(u.getUniqueId()))
                Database.getInstance().update(u.getUsername(), u);
            else
                Database.getInstance().insert(u);
        }
    }
}

