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

/**
 * This class is responsible for
 * 1. Providing a REST API to retrieve all devices
 * 2. Receiving power events from the PowerOut channel and processing them
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/power")
public class PowerResource {

    @Inject
    @Channel("power-out")
    Emitter<DevicePower> powerEmitter;

    /**
     * This method retrieves all devices from the database and returns them as a JSON array.
     *
     * @return A JSON array containing all devices
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Power> getDevices() {
        return Power.listAll();
    }

    /**
     * This method receives a power event from a POST request and processes it.
     * It sends the received power event to the PowerOut channel.
     *
     * @param devicePower The power event received
     * @return A success message indicating that the power event was processed successfully
     */
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
