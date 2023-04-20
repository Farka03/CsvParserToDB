package com.csv.parser.service;

import com.csv.parser.entity.Posting;

import java.time.LocalDate;
import java.util.List;

public interface PostingService {
    List<Posting> findByIsActiveAndPostDateBetween(boolean isActive, LocalDate startDate, LocalDate endDate);

    List<Posting> findByIsActiveAndAuthorizedDeliveryAndPostDateBetween(boolean isActive,
                                                                        boolean authorizedDelivery,
                                                                        LocalDate startDate, LocalDate endDate);
}
