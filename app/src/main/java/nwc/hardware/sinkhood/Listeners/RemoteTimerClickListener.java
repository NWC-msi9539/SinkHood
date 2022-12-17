package nwc.hardware.sinkhood.Listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import nwc.hardware.sinkhood.R;
import nwc.hardware.sinkhood.Resource.Data;

public class RemoteTimerClickListener implements View.OnClickListener {
    private boolean active = false;
    private ImageView parent;
    private ImageView sub_Timer;
    private Context mContext;

    public RemoteTimerClickListener(ImageView button,ImageView subView, Context mContext){
        parent = button;
        sub_Timer = subView;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
    }

}
