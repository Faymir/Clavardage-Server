package fr.faymir;

import fr.faymir.Model.ConnectedUsers;
import fr.faymir.Model.Type;
import fr.faymir.Model.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

@Path("subscribe")
public class Subscribe {
    private static final String defaultUsername = "#$#%$%%&&dsfduhsi$%*⁽";
    @Inject
    private javax.inject.Provider<org.glassfish.grizzly.http.server.Request> grizzlyRequest;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@DefaultValue(defaultUsername) @QueryParam("username") String username, @Context UriInfo uriInfo){
        JSONObject obj = new JSONObject();
        System.out.println("username = [" + username + "], uriInfo = [" + uriInfo.getRequestUri().getQuery() + "]");
        if (uriInfo.getQueryParameters().size() == 0 || username.equals(defaultUsername)) {
            obj.put("Type", Type.BAD_USERNAME);
            obj.put("message", "No username provided");
        }
        else if(ConnectedUsers.contains(username)){
            obj.put("Type", Type.BAD_USERNAME);
            obj.put("message", "Username already exist");
        }
        else{
            ConnectedUsers.add(new User(grizzlyRequest.get().getRemoteAddr(), true, username, UUID.randomUUID().toString()));
            obj.put("Type", Type.GOOD_USERNAME);
            obj.put("number", ConnectedUsers.connectedUsers.size());
            obj.put("message", "ok");
            obj.put("users", Base64.encodeBase64String(SerializationUtils.serialize(ConnectedUsers.connectedUsers)));
        }
        return obj.toString();
    }



}
