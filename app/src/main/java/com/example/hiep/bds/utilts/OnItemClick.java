package com.example.hiep.bds.utilts;

import android.view.View;

public interface OnItemClick {
    public void onClick(View v, int adapterPosition, boolean b);
    public void onClickItem(int pos);
    public void onClickDelete(int pos);
}