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
@Table(name = "cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    private UUID uuid;
    private String name;
    @Column(name = "price_in_usd")
    private String priceInUsd;
    private String description;
    @Column(name = "image_paths")
    private String imagePaths;

    @ManyToMany(mappedBy = "userFavoriteCars")
    private Set<User> users = new HashSet<>();

    public Car(String name, String priceInUsd, String description) {
        this.name = name;
        this.priceInUsd = priceInUsd;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!Objects.equals(uuid, car.uuid)) return false;
        if (!Objects.equals(name, car.name)) return false;
        if (!Objects.equals(priceInUsd, car.priceInUsd)) return false;
        if (!Objects.equals(description, car.description)) return false;
        return Objects.equals(imagePaths, car.imagePaths);
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (priceInUsd != null ? priceInUsd.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imagePaths != null ? imagePaths.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", priceInUsd='" + priceInUsd + '\'' +
                ", description='" + description + '\'' +
                ", imagePaths='" + imagePaths + '\'' +
                '}';
    }
}
