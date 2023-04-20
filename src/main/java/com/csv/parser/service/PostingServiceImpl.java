package com.csv.parser.service;

import com.csv.parser.entity.Posting;
import com.csv.parser.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostingServiceImpl implements PostingService {

    @Autowired
    private PostingRepository postingRepository;

    @Override
    public List<Posting> findByIsActiveAndPostDateBetween(boolean isActive, LocalDate startDate, LocalDate endDate) {
        return postingRepository.findByIsActiveAndPostDateBetween(isActive, startDate, endDate);
    }

    @Override
    public List<Posting> findByIsActiveAndAuthorizedDeliveryAndPostDateBetween(boolean isActive,
                                                                               boolean authorizedDelivery,
                                                                               LocalDate startDate,
                                                                               LocalDate endDate) {
        return postingRepository.findByIsActiveAndAuthorizedDeliveryAndPostDateBetween(isActive, authorizedDelivery,
                                                                                       startDate, endDate);
    }
}
