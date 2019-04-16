package com.example.hiep.bds.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail implements Parcelable {
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

    protected Detail(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            floor = null;
        } else {
            floor = in.readInt();
        }
        if (in.readByte() == 0) {
            bath = null;
        } else {
            bath = in.readInt();
        }
        if (in.readByte() == 0) {
            balcony = null;
        } else {
            balcony = in.readInt();
        }
        if (in.readByte() == 0) {
            toilet = null;
        } else {
            toilet = in.readInt();
        }
        if (in.readByte() == 0) {
            bedRoom = null;
        } else {
            bedRoom = in.readInt();
        }
        byte tmpDinningRoom = in.readByte();
        dinningRoom = tmpDinningRoom == 0 ? null : tmpDinningRoom == 1;
        byte tmpLivingRoom = in.readByte();
        livingRoom = tmpLivingRoom == 0 ? null : tmpLivingRoom == 1;
        if (in.readByte() == 0) {
            postId = null;
        } else {
            postId = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Detail> CREATOR = new Creator<Detail>() {
        @Override
        public Detail createFromParcel(Parcel in) {
            return new Detail(in);
        }

        @Override
        public Detail[] newArray(int size) {
            return new Detail[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (floor == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(floor);
        }
        if (bath == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(bath);
        }
        if (balcony == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(balcony);
        }
        if (toilet == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(toilet);
        }
        if (bedRoom == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(bedRoom);
        }
        parcel.writeByte((byte) (dinningRoom == null ? 0 : dinningRoom ? 1 : 2));
        parcel.writeByte((byte) (livingRoom == null ? 0 : livingRoom ? 1 : 2));
        if (postId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(postId);
        }
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }
}