package com.example.hiep.bds.utilts;

public class GetView < T extends GetInterface> {
    public T presenter;

    public GetView(T presenter) {
        this.presenter = presenter;
    }
}