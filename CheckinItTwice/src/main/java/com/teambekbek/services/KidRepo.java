package com.teambekbek.services;

import com.teambekbek.entities.Kid;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rdw1995 on 12/16/16.
 */
public interface KidRepo extends CrudRepository<Kid, Integer> {
}
