package org.example.Data.Models.Company;

import jakarta.persistence.*;
import org.example.Data.Models.User.UserModel;

import java.util.List;

@Entity
@Table(name = "CompanyModel")
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public CompanyModel() {

    }

    public CompanyModel(String name) {
        this.name = name;
    }

    public CompanyModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<UserModel> Users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserModel> getUsers() {
        return Users;
    }

    public void setUsers(List<UserModel> users) {
        Users = users;
    }
}
