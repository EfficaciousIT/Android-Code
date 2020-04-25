package com.mobi.efficacious.arohanschool.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeesDetailsPojo {

    @SerializedName("FeesDetail")
    @Expose
    private List<com.mobi.efficacious.arohanschool.entity.FeesDetail> feesDetail = null;

    public List<com.mobi.efficacious.arohanschool.entity.FeesDetail> getFeesDetail() {
        return feesDetail;
    }

    public void setFeesDetail(List<com.mobi.efficacious.arohanschool.entity.FeesDetail> feesDetail) {
        this.feesDetail = feesDetail;
    }

}
