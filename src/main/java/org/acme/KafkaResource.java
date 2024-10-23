package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;

public class KafkaResource {
    
    @Transactional
    @Incoming("power")
    public void receivePower(DevicePower devicePower) {      
        Log.info("Received data: Device : " + devicePower.device() + "with power: " + devicePower.power());
        Power powerEntity = new Power();
        powerEntity.device=devicePower.device();
        powerEntity.power=devicePower.power();
        powerEntity.persist();        
    }
    
}
