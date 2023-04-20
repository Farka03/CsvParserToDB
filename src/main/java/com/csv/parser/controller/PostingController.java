package com.csv.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.csv.parser.entity.Posting;
import com.csv.parser.service.PostingServiceImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/postings")
public class PostingController {

    @Autowired
    private PostingServiceImpl postingServiceImpl;

    @GetMapping("/{period}")
    public List<Posting> getPostings(@PathVariable String period, @RequestParam(required = false) boolean authorized) {
        LocalDate startDate;
        LocalDate endDate = LocalDate.now();
        if (period.equals("day")) {
            startDate = endDate;
        } else if (period.equals("month")) {
            startDate = endDate.withDayOfMonth(1);
        } else if (period.equals("quarter")) {
            startDate = endDate.withMonth(((endDate.getMonthValue() - 1) / 3) * 3 + 1).withDayOfMonth(1);
        } else if (period.equals("year")) {
            startDate = endDate.withDayOfYear(1);
        } else {
            throw new IllegalArgumentException("Invalid period: " + period);
        }

        if (authorized) {
            return postingServiceImpl.findByIsActiveAndAuthorizedDeliveryAndPostDateBetween(true, true, startDate, endDate);
        } else {
            return postingServiceImpl.findByIsActiveAndPostDateBetween(true, startDate, endDate);
        }
    }
}


