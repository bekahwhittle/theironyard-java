package com.teambekbek.services;

import com.teambekbek.entities.Elves;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rdw1995 on 12/19/16.
 */
public interface ElfRepo extends CrudRepository<Elves, Integer> {
}
