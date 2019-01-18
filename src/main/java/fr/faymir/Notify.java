package fr.faymir;

import fr.faymir.Model.ConnectedUsers;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class Notify {
    private static final String defaultData = "#$#%$%%&&dsfduhsi$%*⁽";
    @Inject
    private javax.inject.Provider<org.glassfish.grizzly.http.server.Request> grizzlyRequest;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String data, @Context UriInfo uriInfo){
        ConnectedUsers.updateUsersStatus();
//        MultivaluedMap<String, String> params= uriInfo.getQueryParameters();
//        System.out.println("data = [" + data + "], uriInfo = [" + uriInfo.getRequestUri() + "]");
        JSONObject obj = new JSONObject("{\"uniqueId\": \"sdfds-dfsf5-5df5d\"}");

        return obj.toString();
    }
}
