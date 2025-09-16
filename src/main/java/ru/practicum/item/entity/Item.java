package ru.practicum.item.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_available", nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Comment> comments;
}