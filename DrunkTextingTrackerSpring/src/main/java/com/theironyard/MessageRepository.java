package com.theironyard;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Created by rdw1995 on 10/21/16.
 */

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByAuthorOrTextOrDrunk (String author, String text, String Drunk);
}
