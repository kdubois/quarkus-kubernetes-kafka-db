package org.acme;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/power")
public class PowerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<DevicePower> getDevices() {
        return DevicePower.listAll();
    }
}
