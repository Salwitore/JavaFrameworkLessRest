package org.example.Data.Models.User;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.example.Data.Models.Company.CompanyModel;

@Entity
@Table(name = "UserModel")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String username;

    public UserModel(){

    }

    public UserModel(String name , String surname , String username ){
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    public CompanyModel getCompany() {
        return company;
    }

    public void setCompany(CompanyModel company) {
        this.company = company;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

