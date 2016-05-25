package com.allenliu.floatview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Allen Liu on 2016/5/25.
 */
public class CallShowReceiver extends BroadcastReceiver {
    private String TAG="CALLSHOWRECEIVER";
    private boolean viewIsShow=false;
    private  FloatView floatView;
    private PhoneStateListener phoneStateListener;
    @Override
    public void onReceive(Context context, Intent intent) {
       // if(intent.getAction().equals("com.allenliu.floatview.REGISTER_CALLSHOW"))
            initPhoneStateListener(context,intent);
    }
    private void initPhoneStateListener(final Context context, final Intent intent) {
        if(phoneStateListener==null) {
            phoneStateListener = new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    switch (state) {
                        //挂机或没有电话时的状态
                        case TelephonyManager.CALL_STATE_IDLE:
                            if (floatView != null) {
                                floatView.removeFromWindow();
                                viewIsShow = false;
                            }
                            break;
                        //响铃中
                        case TelephonyManager.CALL_STATE_RINGING:
                            //  Log.v(TAG,"SHOW");
                            if (!viewIsShow) {
                                viewIsShow = true;
                                if (floatView == null)
                                    floatView = new FloatView(context, 0, 0, R.layout.callshow_layout);
                                TextView textView = (TextView) floatView.findViewById(R.id.tv_phone);
                                ImageView imageView = (ImageView) floatView.findViewById(R.id.iv);
                                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                                params.width = getScreenWidth(context);
                                // params.height=400;
                                imageView.setLayoutParams(params);
                                textView.setText(incomingNumber + "来电了");

                                floatView.addToWindow();
                            }
                            break;
                        //接听电话了
                        case TelephonyManager.CALL_STATE_OFFHOOK:
                            if (floatView != null) {
                                floatView.removeFromWindow();
                                viewIsShow = false;
                            }
                            break;
                    }
                }
            };
            //注册监听，
            TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
