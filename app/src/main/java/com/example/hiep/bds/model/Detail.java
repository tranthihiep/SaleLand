package com.example.hiep.bds.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("floor")
    @Expose
    private Integer floor;
    @SerializedName("bath")
    @Expose
    private Integer bath;
    @SerializedName("balcony")
    @Expose
    private Integer balcony;
    @SerializedName("toilet")
    @Expose
    private Integer toilet;
    @SerializedName("bed_room")
    @Expose
    private Integer bedRoom;
    @SerializedName("dinning_room")
    @Expose
    private Boolean dinningRoom;
    @SerializedName("living_room")
    @Expose
    private Boolean livingRoom;
    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Detail() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBath() {
        return bath;
    }

    public void setBath(Integer bath) {
        this.bath = bath;
    }

    public Integer getBalcony() {
        return balcony;
    }

    public void setBalcony(Integer balcony) {
        this.balcony = balcony;
    }

    public Integer getToilet() {
        return toilet;
    }

    public void setToilet(Integer toilet) {
        this.toilet = toilet;
    }

    public Integer getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(Integer bedRoom) {
        this.bedRoom = bedRoom;
    }

    public Boolean getDinningRoom() {
        return dinningRoom;
    }

    public void setDinningRoom(Boolean dinningRoom) {
        this.dinningRoom = dinningRoom;
    }

    public Boolean getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(Boolean livingRoom) {
        this.livingRoom = livingRoom;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}