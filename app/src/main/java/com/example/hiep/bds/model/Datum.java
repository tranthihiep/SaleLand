package com.example.hiep.bds.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;


public class Datum implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("area")
    @Expose
    private Integer area;
    @SerializedName("price")
    @Expose
    private Long price;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("negotiable")
    @Expose
    private Integer negotiable;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @Override
    public String toString() {
        return "Datum{"
                + "id="
                + id
                + ", title='"
                + title
                + '\''
                + ", slug='"
                + slug
                + '\''
                + ", image='"
                + image
                + '\''
                + ", purpose='"
                + purpose
                + '\''
                + ", address='"
                + address
                + '\''
                + ", latitude='"
                + latitude
                + '\''
                + ", longitude='"
                + longitude
                + '\''
                + ", description='"
                + description
                + '\''
                + ", area="
                + area
                + ", price="
                + price
                + ", unit='"
                + unit
                + '\''
                + ", negotiable="
                + negotiable
                + ", status='"
                + status
                + '\''
                + ", views="
                + views
                + ", typeId="
                + typeId
                + ", districtId="
                + districtId
                + ", userId="
                + userId
                + ", createdAt='"
                + createdAt
                + '\''
                + ", updatedAt='"
                + updatedAt
                + '\''
                + ", user="
                + user
                + ", district="
                + district
                + ", detail="
                + detail
                + ", propertyType="
                + propertyType
                + ", distances="
                + distances
                + '}';
    }

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("detail")
    @Expose
    private Detail detail = new Detail();
    @SerializedName("property_type")
    @Expose
    private PropertyType propertyType;
    @SerializedName("distances")
    @Expose
    private List<Distance> distances = null;

    protected Datum(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        slug = in.readString();
        image = in.readString();
        purpose = in.readString();
        address = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            area = null;
        } else {
            area = in.readInt();
        }
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readLong();
        }
        unit = in.readString();
        if (in.readByte() == 0) {
            negotiable = null;
        } else {
            negotiable = in.readInt();
        }
        status = in.readString();
        if (in.readByte() == 0) {
            views = null;
        } else {
            views = in.readInt();
        }
        if (in.readByte() == 0) {
            typeId = null;
        } else {
            typeId = in.readInt();
        }
        if (in.readByte() == 0) {
            districtId = null;
        } else {
            districtId = in.readInt();
        }
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        createdAt = in.readString();
        updatedAt = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        district = in.readParcelable(District.class.getClassLoader());
        detail = in.readParcelable(Detail.class.getClassLoader());
        propertyType = in.readParcelable(PropertyType.class.getClassLoader());
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    public Datum() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(Integer negotiable) {
        this.negotiable = negotiable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
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

        parcel.writeString(title);
        parcel.writeString(slug);
        parcel.writeString(image);
        parcel.writeString(purpose);
        parcel.writeString(address);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(description);
        if (area == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(area);
        }
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(price);
        }
        parcel.writeString(unit);
        if (negotiable == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(negotiable);
        }
        parcel.writeString(status);
        if (views == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(views);
        }
        if (typeId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(typeId);
        }
        if (districtId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(districtId);
        }
        if (userId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(userId);
        }
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeParcelable(user,i);
        parcel.writeParcelable(district,i);
        parcel.writeParcelable(detail,i);
        parcel.writeParcelable(propertyType,i);
    }
}