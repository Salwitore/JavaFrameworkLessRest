package org.example.Data.Models.Bnet;

import jakarta.persistence.*;

@Entity
@Table(name = "bnetAuthSystem")
public class BnetAuthSystem {

    @Id
    @Column(name = "authSystemID",length = 20)
    private String authSystemId
}
