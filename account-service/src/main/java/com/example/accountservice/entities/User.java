package com.example.accountservice.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Table(name = "user")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;


    @Length(message = "username must has length between 4-30", min = 4, max = 30)
    @NotNull(message = "username cannot be null")
    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    @Length(message = "password must has length between 6-30", min = 6, max = 30)
    @NotNull(message = "password cannot be null")
    @Column(name = "password", nullable = false, length = 30)
    private String password;


    @Length(message = "fname length cannot be over 50 characters", max = 50)
    @NotNull(message = "fname cannot be null")
    @Column(name = "fname", nullable = false, length = 50)
    private String fname;

    @Length(message = "lname length cannot be over 50 characters", max = 50)
    @NotNull(message = "lname cannot be null")
    @Column(name = "lname", nullable = false, length = 50)
    private String lname;

    @Length(message = "telephone number length must be 10 characters", min = 10, max = 10)
    @Column(name = "tel", length = 10)
    private String tel;

    @Email(message = "email is not correct")
    @NotNull(message = "email cannot be null")
    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Embedded
    private DateTimeAudit dateTimeAudit = new DateTimeAudit();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}