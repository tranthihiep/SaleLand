package com.example.hiep.bds.view.sale;

import com.example.hiep.bds.utilts.GetInterface;
import com.example.hiep.bds.model.Datum;
import java.util.List;

public interface GetDataView extends GetInterface {
    public void getListMovieSuccess(List<Datum> movies);
    public void getListMovieFailure();

}
