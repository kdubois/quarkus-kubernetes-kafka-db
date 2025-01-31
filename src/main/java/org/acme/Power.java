package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

/**
 * Power entity.
 */
@Entity
public class Power extends PanacheEntity {
    public String device;
    public int power;

    static void updatePower(DevicePower devicePower) {
        Power existingDevice = Power.find("device", devicePower.device()).firstResult();
        if (existingDevice != null) {
            // update
            existingDevice.power = existingDevice.power + devicePower.power();
            existingDevice.persist();
        } else {
            var powerEntity = new Power();
            powerEntity.device=devicePower.device();
            powerEntity.power=devicePower.power();
            powerEntity.persist();
        }
    }
}
