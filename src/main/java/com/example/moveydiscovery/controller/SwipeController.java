package com.example.moveydiscovery.controller;

import com.example.moveydiscovery.entity.Swipe;
import com.example.moveydiscovery.service.SwipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification/swipes")
public class SwipeController {
    private static final Logger log = Logger.getLogger(String.valueOf(SwipeController.class));
    private final SwipeService swipeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveSwipe(@RequestBody Swipe swipe) {
        log.info("saving swipe - " + swipe);
        swipeService.save(swipe);
    }
}
