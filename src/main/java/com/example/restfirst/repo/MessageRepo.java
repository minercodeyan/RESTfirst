package com.example.restfirst.repo;

import com.example.restfirst.model.User;
import com.example.restfirst.model.communicationEntities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MessageRepo extends JpaRepository<Message,Long> {

    String FIND_BY_ID_GROUP = "SELECT * FROM massages WHERE user_id IN "+
            "(SELECT id FROM user WHERE group_id = :groupId)";


   @Query(value = FIND_BY_ID_GROUP,nativeQuery = true)
    List<Message> findByGroup(@Param("groupId")Long groupId);

    List<Message> findByUserIn(Collection<User> user);
}
