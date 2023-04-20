package com.csv.parser.repository;
import com.csv.parser.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Integer> {
    List<Posting> findByIsActiveAndPostDateBetween(boolean isActive, LocalDate startDate, LocalDate endDate);

    List<Posting> findByIsActiveAndAuthorizedDeliveryAndPostDateBetween(boolean isActive,
                                                                        boolean authorizedDelivery,
                                                                        LocalDate startDate, LocalDate endDate);
}
