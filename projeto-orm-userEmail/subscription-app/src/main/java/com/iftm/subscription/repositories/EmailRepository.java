package com.iftm.subscription.repositories;

import com.iftm.subscription.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("SELECT e FROM Email e WHERE e.to = :toEmail")
    List<Email> findAllByRecipient (@Param("toEmail") String toEmail);

    @Query("SELECT e FROM Email e WHERE e.from = :fromEmail")
    List<Email> findAllBySender (@Param("fromEmail") String fromEmail);

}
