package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by rdw1995 on 10/22/16.
 */
public interface UserRepository extends CrudRepository <User, Integer> {
     User findByUsername (String username);
//     User findByName (String name);
}
