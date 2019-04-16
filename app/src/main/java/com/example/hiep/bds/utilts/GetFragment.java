package com.example.hiep.bds.utilts;

import android.support.v4.app.Fragment;

public class GetFragment extends Fragment {
    protected GetChildFragment iGetChildFragment;

    public void setFragmentMain(GetChildFragment getChildFragment) {
        this.iGetChildFragment = getChildFragment;
    }

    public GetChildFragment getIGetChildFragment() {
        return iGetChildFragment;
    }
}

