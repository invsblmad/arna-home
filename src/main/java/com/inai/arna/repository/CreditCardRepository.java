package com.inai.arna.repository;

import com.inai.arna.dto.response.CreditCardResponse;
import com.inai.arna.model.card.CreditCard;
import com.inai.arna.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    @Query("select new com.inai.arna.dto.response.CreditCardResponse(c.number, c.firstName, c.lastName, " +
            "c.expiryDate, c.type, c.isDefault) from CreditCard c where c.user.id = :userId")
    List<CreditCardResponse> findByUserId(@Param("userId") Integer userId);
    Optional<CreditCard> findByUserAndNumber(User user, String number);
    Optional<CreditCard> findByUserAndIsDefault(User user, boolean isDefault);
}
