package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rdw1995 on 11/10/16.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByName (String name);

}
