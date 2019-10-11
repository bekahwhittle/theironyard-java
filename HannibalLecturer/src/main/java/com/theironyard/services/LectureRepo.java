package com.theironyard.services;

import com.theironyard.entities.Lecture;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rdw1995 on 10/26/16.
 */
public interface LectureRepo extends CrudRepository<Lecture, Integer> {
}
