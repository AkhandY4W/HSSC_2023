package com.youth4work.HSSC_2023.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Conversation extends com.youth4work.HSSC_2023.network.model.response.BusinessObject {

    @SerializedName("Conv")
    private ArrayList<com.youth4work.HSSC_2023.network.model.response.Message> mArrListMessages;

    public ArrayList<com.youth4work.HSSC_2023.network.model.response.Message> getArrListMessages() {
        return mArrListMessages;
    }

}
