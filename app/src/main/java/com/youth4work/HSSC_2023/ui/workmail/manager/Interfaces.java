package com.youth4work.HSSC_2023.ui.workmail.manager;


import com.youth4work.HSSC_2023.network.model.response.BusinessObject;

public class Interfaces {

    public interface IDataRetrievalListener{
        public void onDataRetrieved(BusinessObject businessObject);
    }

    public interface IDataRetrievalListenerString{
        public void onDataRetrieved(String string);
    }
}
