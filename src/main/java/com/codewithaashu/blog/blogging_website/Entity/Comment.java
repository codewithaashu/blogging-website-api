package com.codewithaashu.blog.blogging_website.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//Entity is used to mapping the table. it define the table name and their field
@Entity // to make class as an entity
@Table(name = "comments") // define the table name
@Getter // to access these field outside the class
@Setter // to set these field from outside the class
@Data
public class Comment {
        // define all the fields

        // create primary key
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String content;

        // automaticaly generated createdAt time
        @CreationTimestamp
        private LocalDateTime createdAt;

        // define the relationship

        // a post have many comments
        @ManyToOne // define the type of relationship
        @JoinColumn(name = "post_id", // specifies the name of foreign key column in this table
                        referencedColumnName = "id" // specified the name of primary key in parent table i.e (post)
        )
        private Post post;

        // a user is associated to many comment
        @ManyToOne
        @JoinColumn(name = "user_id", // specifies the name of foreign key column in this table
                        referencedColumnName = "id" // specified the name of primary key in parent table i.e (user
        )
        private User user;
}
