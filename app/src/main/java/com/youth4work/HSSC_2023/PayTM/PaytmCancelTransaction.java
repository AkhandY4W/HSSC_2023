package com.youth4work.HSSC_2023.PayTM;

/**
 * Created by jagbros-4 on 20-Jun-17.
 */

public interface PaytmCancelTransaction {
    void onCancellationSuccess();

    void onCancellationFailure();
}
