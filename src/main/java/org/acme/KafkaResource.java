package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;

/**
 * This class is responsible for receiving messages from a Kafka topic and processing them.
 * It uses the `@Transactional` annotation to ensure that the database operations are performed in a single transaction.
 * The `@Incoming("power")` annotation specifies that this method should be called when a message with the key "power" is received on the Kafka topic.
 *
 * @author Kevin Dubois
 * @since 1.0
 *
 */
public class KafkaResource {

    /**
     * This method is called when a message with the key "power" is received on the Kafka topic.
     * It processes the message by extracting the device and power information, creating a new `Power` entity,
     * and persisting it in the database.
     *
     * @param devicePower
     */
    @Transactional
    @Incoming("power")
    public void receivePower(DevicePower devicePower) {      
        Log.info("Received data: Device : " + devicePower.device() + "with power: " + devicePower.power());
        var powerEntity = new Power();
        powerEntity.device=devicePower.device();
        powerEntity.power=devicePower.power();
        powerEntity.persist();
    }
    
}
