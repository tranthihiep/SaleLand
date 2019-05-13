package com.example.hiep.bds.view.postAD;

import com.example.hiep.bds.model.modelLocation.Tinh;
import com.example.hiep.bds.utilts.GetInterface;
import java.util.List;

public interface GetLocationView extends GetInterface {
    public void getListLocationSucces(List<Tinh> locationResponses);
    public void getListLocationFailure();

}
