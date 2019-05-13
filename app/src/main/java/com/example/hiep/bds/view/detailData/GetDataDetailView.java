package com.example.hiep.bds.view.detailData;

import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.utilts.GetInterface;

public interface GetDataDetailView extends GetInterface {
    public void getDataDetailSuccess(DataDetail movieDetails);

    public void getDataDetailFailure();
}