package org.example.Business.Dtos.User;

public class GetUserDto {
    public int id;
    public String name;
    public String surname;
    public String username;
    public int companyId;

    public GetUserDto(int companyId, int id, String name, String surname, String username) {
        this.companyId = companyId;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
}
