package ru.practicum.item.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.apache.catalina.User;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название вещи не может быть пустым")
    @Size(min = 1, max = 255, message = "Название не должно превышать 255 символов")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Описание вещи не может быть пустым")
    @Size(min = 1, max = 500, message = "Описание не должно превышать 500 символов")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Поле 'available' не может быть null")
    @Column(name = "available", nullable = false)
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public User getOwner() {
        return owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}