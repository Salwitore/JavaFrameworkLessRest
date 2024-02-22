package org.example.Data.Models.Bnet;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bnetRosterStatus", indexes = {@Index(name = "ix_from_to", columnList = "fromJID, toJID")})
public class BnetRosStEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "toJID" ,length = 255 , nullable = true)
    private String toJID;

    @Column(name = "fromJID",length = 255,nullable = true)
    private String fromJID;

    @Column(name = "lastadded",columnDefinition = "DATETIME(6)" , nullable = true)
    private LocalDateTime lastAdded;

    public BnetRosStEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToJID() {
        return toJID;
    }

    public void setToJID(String toJID) {
        this.toJID = toJID;
    }

    public String getFromJID() {
        return fromJID;
    }

    public void setFromJID(String fromJID) {
        this.fromJID = fromJID;
    }

    public LocalDateTime getLastAdded() {
        return lastAdded;
    }

    public void setLastAdded(LocalDateTime lastAdded) {
        this.lastAdded = lastAdded;
    }
}
