package com.group.genshinProg.model.DTO;

import lombok.Data;

import java.io.Serializable;


public class ProbabilityDTO implements Serializable {
    private Double fiveStarProbability;
    private Double fourStarProbability;

    public ProbabilityDTO (Double fourStarProbability, Double fiveStarProbability) {
        this.fourStarProbability = fourStarProbability;
        this.fiveStarProbability = fiveStarProbability;
    }

    public void setFiveStarProbability(Double fiveStarProbability) {this.fiveStarProbability = fiveStarProbability;}
    public void setFourStarProbability (Double fourStarProbability) {this.fourStarProbability = fourStarProbability;}

    public Double getFiveStarProbability() {return fiveStarProbability;}
    public Double getFourStarProbability() {return fourStarProbability;}
}
