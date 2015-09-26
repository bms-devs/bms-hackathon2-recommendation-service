package org.bmshackathon.dto;

public class Recommendation {
    private Long id;
    private Double averageRating;

    public Recommendation(Long id, Double reviews) {
        this.id = id;
        this.averageRating = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
