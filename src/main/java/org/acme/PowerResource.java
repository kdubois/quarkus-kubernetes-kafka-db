package org.acme;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.quarkus.logging.Log;

@Path("/power")
public class PowerResource {

    @Inject
    @Channel("power-out")
    Emitter<DevicePower> powerEmitter;


    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Power> getDevices() {
        return Power.listAll();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String receivePowerEvent(DevicePower devicePower ) {
        Log.info("received power event for device " + devicePower.device() + " with power: " + devicePower.power());
        
        powerEmitter.send(devicePower);

        return "Great Success!";
    }


}
