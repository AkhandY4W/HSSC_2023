package com.youth4work.HSSC_2023.PayTM;

/**
 * Created by jagbros-4 on 20-Jun-17.
 */
import android.os.Bundle;

public interface PaytmStatusQueryCallback {
    void onStatusQueryCompleted(Bundle var1);

    void onStatusQueryFailed(String var1);
}
