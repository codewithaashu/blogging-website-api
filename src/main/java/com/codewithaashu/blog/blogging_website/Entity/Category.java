package com.codewithaashu.blog.blogging_website.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//Entity is used to mapping the table. it define the table name and their field
@Entity // to make class as an entity
@Table(name = "categories") // define the table name
@Getter // to access these field outside the class
@Setter // to set these field from outside the class
@Data
public class Category {
    // define all the field

    // define primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically different-different value
    private Integer id;

    @Column(name = "category_title", nullable = false, unique = false)
    private String title;
    @Column(name = "column_description")
    private String description;

    @CreationTimestamp // automatically add createdAt column
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // create a relationship

    // a category has many posts. category is independent(parent) and post is
    // dependent(child)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    // When a category entity is persisted, updated, or deleted, all associated
    // posts entities will also be persisted, updated, or deleted.
    private List<Post> posts = new ArrayList<>();
}
