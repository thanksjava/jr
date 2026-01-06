package com.jr.booking.controller;


import com.jr.booking.model.dto.TripInfo;
import com.jr.booking.service.TripSearchAggregator;
import com.jr.booking.service.TripSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v3/trips")
public class TripController {

    private final TripSearchService tripSearchService;

    public TripController(TripSearchService tripSearchService) {
        this.tripSearchService = tripSearchService;
    }

    @GetMapping("/search")
    public List<TripInfo> search(@RequestParam String userLevel) {
        return tripSearchService.search(userLevel);
    }
}