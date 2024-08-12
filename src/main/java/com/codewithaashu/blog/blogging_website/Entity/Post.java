package com.codewithaashu.blog.blogging_website.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@Table(name = "posts")
public class Post {
    // define all the fields

    // create primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generate diffferent value
    private Integer id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String slug;
    private String description;
    private String image;

    // cataegory;

    private Boolean status;

    @CreationTimestamp // automatically add createdAt column
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // now we will define relation with other tables

    // many posts has one user
    @ManyToOne // define the relationship
    @JoinColumn(name = "user_id", // name of foreign key column in this table
            referencedColumnName = "id"// name of primary key column in parent table (i.e. user)
    ) // relationship is define by join column. by default, it takes primary key of
      // table . it is optional
    private User user;

    // many post has one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    // we specify the foriegn key column in this table. reference name is optional.
    // it takes primary key of category by default
    private Category category;

    // a post has many comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    // we want to add some default value of columns
    @PrePersist
    public void prePersist() {
        if (status == null) {
            setStatus(true);
        }
        if (image == null) {
            setImage(
                    "https://soliloquywp.com/wp-content/uploads/2016/08/How-to-Set-a-Default-Featured-Image-in-WordPress.png");
        }
    }
}
