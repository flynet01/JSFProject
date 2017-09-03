package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.Registration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by rpigeyre on 13/08/2017.
 */
@SessionScoped
// Add Serializable for clustering
public class RegistrationState implements Serializable {
    private Registration registration = new Registration();

    @Produces
    @Named
    public Registration getRegistration() {
        return registration;
    }
}
