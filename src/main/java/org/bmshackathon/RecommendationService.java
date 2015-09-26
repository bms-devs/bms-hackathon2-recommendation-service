package org.bmshackathon;

import org.bmshackathon.client.VideoMetadataFeignClient;
import org.bmshackathon.client.VideoReviewClient;
import org.bmshackathon.dto.Recommendation;
import org.bmshackathon.dto.VideoMetadata;
import org.bmshackathon.dto.VideoReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {


    VideoMetadataFeignClient videoMetadataFeignClient;
    VideoReviewClient videoReviewClient;

    @Autowired
    public RecommendationService(VideoMetadataFeignClient videoMetadataFeignClient, VideoReviewClient videoReviewClient) {
        this.videoMetadataFeignClient = videoMetadataFeignClient;
        this.videoReviewClient = videoReviewClient;
    }

    public List<Long> recommendedVideos() {
        List<Recommendation> recommended = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        videoMetadataFeignClient.findAll()
                .stream()
                .filter(metadata -> videoReviewClient.findByVideoId(metadata.getId()).size() > 0)
                .forEach(video -> recommended.add(new Recommendation(video.getId(),
                                            videoReviewClient.findByVideoId(video.getId())
                                                    .stream()
                                                    .mapToDouble(VideoReview::getRating)
                                                    .average()
                                                    .getAsDouble())));

        recommended.sort((v1, v2) -> v1.getAverageRating().compareTo(v2.getAverageRating()));

        recommended
                .stream()
                .limit(3)
                .forEach(recommendation -> ids.add(recommendation.getId()));

        return ids;
    }
}
