package com.youth4work.HSSC_2023.PayTM;

/**
 * Created by jagbros-4 on 20-Jun-17.
 */
public class PaytmCancel {
    public String mMerchantId;
    public String mOrderId;

    public PaytmCancel(String inMerchantId, String inOrderId) {
        this.mMerchantId = inMerchantId;
        this.mOrderId = inOrderId;
    }
}
