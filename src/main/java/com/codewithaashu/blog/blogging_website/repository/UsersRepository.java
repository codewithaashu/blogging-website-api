package com.codewithaashu.blog.blogging_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithaashu.blog.blogging_website.Entity.User;

@Repository // to create class to be repository class
// it provides a lot of function to perform CURD operation on database.
// it is interface which extends JpaRepository. It takes Entity name and primary
// key's data type.
public interface UsersRepository extends JpaRepository<User, Integer> {

    // you can also define custom method
    User findByEmail(String email);
}

// it is interface but at runtime spring container create a class i.e. proxy
// class which implements this interface. So, in further classes, we create
// object of this interface
