package com.youth4work.HSSC_2023.PayTM;

/**
 * Created by jagbros-4 on 20-Jun-17.
 */

public class PaytmStatusQuery {
    public String mOrderId;
    public String mMerchantId;

    public PaytmStatusQuery(String inOrderId, String inMerchantId) {
        this.mOrderId = inOrderId;
        this.mMerchantId = inMerchantId;
    }
}
