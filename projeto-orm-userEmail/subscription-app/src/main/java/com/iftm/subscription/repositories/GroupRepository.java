package com.iftm.subscription.repositories;

import com.iftm.subscription.models.Group;
import com.iftm.subscription.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByLink(@Param("link") String link);

    List<Group> findByName(@Param("name") String name);

}
