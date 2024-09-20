package com.ecombackend.ecombackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String answer; // For security questions

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    private int role = 0; // Default role set to 0 (USER)

    public enum UserRole {
        USER(0),
        ADMIN(1);

        private final int value;

        UserRole(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static UserRole fromValue(int value) {
            for (UserRole role : values()) {
                if (role.getValue() == value) {
                    return role;
                }
            }
            throw new IllegalArgumentException("Invalid role value: " + value);
        }
    }
}
