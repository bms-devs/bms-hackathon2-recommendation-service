package org.bmshackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;


    @Autowired
    public RecommendationController(final RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @RequestMapping(value = "/recommended", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> recommended() {
        return new ResponseEntity<>(recommendationService.recommendedVideos(), HttpStatus.OK);
    }
}