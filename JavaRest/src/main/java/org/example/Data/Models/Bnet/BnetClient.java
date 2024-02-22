package org.example.Data.Models.Bnet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bnetClient")
public class BnetClient {

    @Column(name = "clientID" , length = 20 , nullable = false)
    private String clientID;

    @Column(name = "name",length = 255)
    private String name;

    @Column(name = "keyloakSecret",length = 255)
    private String keyloakSecret;

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyloakSecret() {
        return keyloakSecret;
    }

    public void setKeyloakSecret(String keyloakSecret) {
        this.keyloakSecret = keyloakSecret;
    }
}
