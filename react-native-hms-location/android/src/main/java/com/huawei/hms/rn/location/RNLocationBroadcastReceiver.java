/*
    Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.rn.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.HeadlessJsTaskService;

import static com.huawei.hms.rn.location.backend.helpers.HMSBroadcastReceiver.isAppOnForeground;
import static com.huawei.hms.rn.location.backend.utils.PlatformUtils.GE_OREO;

public class RNLocationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = RNLocationBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        if (!isAppOnForeground((context))) {
            intent.setClass(context, RNTaskService.class);
            if (GE_OREO) {
                context.startForegroundService(intent);
            } else{
                context.startService(intent);
            }
            HeadlessJsTaskService.acquireWakeLockNow(context);
        }
    }
}
