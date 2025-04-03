package com.tcars.maincarsservice.dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private UUID uuid;
    private String name;
    private String surname;
    @Column(name = "avatar_path")
    private String avatarPath;
    private String login;
    @Column(name = "password")
    private String hashedPassword;
    private String phone;

    @ManyToMany
    @JoinTable(name = "users_favorite_cars",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    private Set<Car> userFavoriteCars = new HashSet<>();

    public User(String name, String surname, String avatarPath, String login, String hashedPassword, String phone) {
        this.name = name;
        this.surname = surname;
        this.avatarPath = avatarPath;
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(uuid, user.uuid)) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!Objects.equals(avatarPath, user.avatarPath)) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(hashedPassword, user.hashedPassword))
            return false;
        return Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (avatarPath != null ? avatarPath.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (hashedPassword != null ? hashedPassword.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", login='" + login + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
