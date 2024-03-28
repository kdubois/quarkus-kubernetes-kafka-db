package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;

public class KafkaResource {
    
    @Transactional
    @Incoming("power")
    public void receivePower(DevicePower power) {      
        Log.info("Updated Device : " + power.device + "with power: " + power.power);
        DevicePower.persist(power);
        
    }
    
}
