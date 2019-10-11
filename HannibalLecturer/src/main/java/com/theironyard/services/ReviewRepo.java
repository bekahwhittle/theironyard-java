package com.theironyard.services;

import com.theironyard.entities.Lecture;
import com.theironyard.entities.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rdw1995 on 10/26/16.
 */
public interface ReviewRepo extends CrudRepository<Review, Integer> {
   Iterable<Review> findByLectureId(Integer lectureId);
}
