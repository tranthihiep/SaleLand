package com.example.hiep.bds.model.modelLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CityResponse {
    @SerializedName("LtsItem")
    @Expose
    private List<LtsItem> ltsItem = null;
    @SerializedName("TotalDoanhNghiep")
    @Expose
    private Long totalDoanhNghiep;

    public List<LtsItem> getLtsItem() {
        return ltsItem;
    }

    public void setLtsItem(List<LtsItem> ltsItem) {
        this.ltsItem = ltsItem;
    }

    public Long getTotalDoanhNghiep() {
        return totalDoanhNghiep;
    }

    public void setTotalDoanhNghiep(Long totalDoanhNghiep) {
        this.totalDoanhNghiep = totalDoanhNghiep;
    }

}