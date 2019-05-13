package com.example.hiep.bds.model.postModel;

import com.example.hiep.bds.model.Dis;
import com.example.hiep.bds.model.Type;
import com.example.hiep.bds.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.List;

public class DataPost {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("area")
    @Expose
    private Float area;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("longitude")
    @Expose
    private Float longitude;
    @SerializedName("property_type_id")
    @Expose
    private Integer propertyTypeId;
    @SerializedName("floor")
    @Expose
    private Integer floor;
    @SerializedName("bed_room")
    @Expose
    private Integer bedRoom ;
    @SerializedName("bath")
    @Expose
    private Integer bath;
    @SerializedName("balcony")
    @Expose
    private Integer balcony;
    @SerializedName("toilet")
    @Expose
    private Integer toilet;
    @SerializedName("conveniences")
    @Expose
    private List<Integer> conveniences;
    @SerializedName("distances")
    @Expose
    private HashMap<Integer, Integer> distances;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("negotiable")
    @Expose
    private int negotiable;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("type")
    @Expose
    private Type type;

    public DataPost(String title, String purpose, Float price, Float area, String description,
            Integer districtId, Integer cityId, Integer typeId, String address, Float latitude,
            Float longitude, Integer propertyTypeId, Integer floor, Integer bedRoom, Integer bath,
            Integer balcony, Integer toilet, List<Integer> conveniences, HashMap<Integer, Integer> distances,
            String startDate, String endDate, String unit, int negotiable) {
        this.title = title;
        this.purpose = purpose;
        this.price = price;
        this.area = area;
        this.description = description;
        this.districtId = districtId;
        this.cityId = cityId;
        this.typeId = typeId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.propertyTypeId = propertyTypeId;
        this.floor = floor;
        this.bedRoom = bedRoom;
        this.bath = bath;
        this.balcony = balcony;
        this.toilet = toilet;
        this.conveniences = conveniences;
        this.distances = distances;
        this.startDate = startDate;
        this.endDate = endDate;
        this.unit = unit;
        this.negotiable = negotiable;
    }


    public void setNegotiable(int negotiable) {
        this.negotiable = negotiable;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(Integer propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(Integer bedRoom) {
        this.bedRoom = bedRoom;
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

    public List<Integer> getConveniences() {
        return conveniences;
    }

    public void setConveniences(List<Integer> conveniences) {
        this.conveniences = conveniences;
    }

    public HashMap<Integer, Integer> getDistances() {
        return distances;
    }

    public void setDistances(HashMap<Integer, Integer> distances) {
        this.distances = distances;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataPost{"
                + "title='"
                + title
                + '\''
                + ", slug='"
                + slug
                + '\''
                + ", purpose='"
                + purpose
                + '\''
                + ", price="
                + price
                + ", area="
                + area
                + ", description='"
                + description
                + '\''
                + ", districtId="
                + districtId
                + ", cityId="
                + cityId
                + ", typeId="
                + typeId
                + ", address='"
                + address
                + '\''
                + ", latitude="
                + latitude
                + ", longitude="
                + longitude
                + ", propertyTypeId="
                + propertyTypeId
                + ", floor="
                + floor
                + ", bedRoom="
                + bedRoom
                + ", bath="
                + bath
                + ", balcony="
                + balcony
                + ", toilet="
                + toilet
                + ", conveniences="
                + conveniences
                + ", distances="
                + distances
                + ", startDate='"
                + startDate
                + '\''
                + ", endDate='"
                + endDate
                + '\''
                + ", unit='"
                + unit
                + '\''
                + ", negotiable="
                + negotiable
                + ", userId="
                + userId
                + ", updatedAt='"
                + updatedAt
                + '\''
                + ", createdAt='"
                + createdAt
                + '\''
                + ", id="
                + id
                + ", user="
                + user
                + ", type="
                + type
                + '}';
    }
}