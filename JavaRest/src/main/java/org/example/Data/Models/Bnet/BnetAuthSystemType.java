package org.example.Data.Models.Bnet;

import jakarta.persistence.*;

@Entity
@Table(name = "bnetAuthSystemType")
public class BnetAuthSystemType {

    @Id
    @Column(name = "authType" , length = 1 , nullable = false)
    private String authType;

    @Column(name = "description" , length = 64 , nullable = false)
    private String description;

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
