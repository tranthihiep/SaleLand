package com.example.hiep.bds.view;

import com.example.hiep.bds.model.modelLocation.LtsItem;
import com.example.hiep.bds.utilts.GetInterface;
import java.util.List;

public interface GetLocationView extends GetInterface {
    public void getListLocationSucces(List<LtsItem> locationResponses);
    public void getListLocationFailure();

}
