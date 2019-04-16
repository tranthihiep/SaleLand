package com.example.hiep.bds.view.detailData;

import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.utilts.GetInterface;

public interface GetDataDetailView extends GetInterface {
    public void getDetailMovieSuccess(DataDetail movieDetails);

    public void getDetailMovieFailure();
}