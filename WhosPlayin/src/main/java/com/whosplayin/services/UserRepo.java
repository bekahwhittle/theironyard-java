package com.whosplayin.services;

import com.whosplayin.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rdw1995 on 11/15/16.
 */
public interface UserRepo extends CrudRepository<User, Integer> {
    User findFirstByUsername (String username);
}
