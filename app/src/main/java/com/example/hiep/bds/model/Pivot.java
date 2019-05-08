package com.example.hiep.bds.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pivot {

    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("distance_id")
    @Expose
    private Integer distanceId;
    @SerializedName("meters")
    @Expose
    private Integer meters;

    public Pivot(Integer meters) {
        this.meters = meters;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getDistanceId() {
        return distanceId;
    }

    public void setDistanceId(Integer distanceId) {
        this.distanceId = distanceId;
    }

    public Integer getMeters() {
        return meters;
    }

    public void setMeters(Integer meters) {
        this.meters = meters;
    }

}