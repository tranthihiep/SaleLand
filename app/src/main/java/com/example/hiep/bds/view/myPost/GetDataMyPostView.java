package com.example.hiep.bds.view.myPost;

import com.example.hiep.bds.model.DataMyPost;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.utilts.GetInterface;
import java.util.List;

public interface GetDataMyPostView  extends GetInterface {
    public void getDataMyPostSuccess(List<DataMyPost> movies);
    public void getDataMyPostFailure();
}
