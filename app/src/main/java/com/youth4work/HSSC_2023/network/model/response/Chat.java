package com.youth4work.HSSC_2023.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Chat extends com.youth4work.HSSC_2023.network.model.response.BusinessObject {
    @SerializedName("data")
    private ArrayList<Message> mArrListMessages;

    public ArrayList<Message> getArrListMessages() {
        return mArrListMessages;
    }

}
