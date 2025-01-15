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

}
