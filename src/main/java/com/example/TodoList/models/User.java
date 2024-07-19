package com.example.TodoList.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message="Username is required")
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String repassword;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
    private List<TodoItem> todoItems;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.email = "";
    }

    @Override
    public String toString() {
        return String.format("TodoItem{id=%d, username='%s', password='%s', email='%s'}",
        id, username, password, email);
    }
}
