package nwc.hardware.sinkhood.Listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import nwc.hardware.sinkhood.R;
import nwc.hardware.sinkhood.Resource.Data;

public class RemoteImageButtonClickListener implements View.OnClickListener {
    private boolean active = false;
    private int type = 0;
    private ImageButton parent;
    private Context mContext;

    public RemoteImageButtonClickListener(ImageButton button, Context mContext, int type){
        parent = button;
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    public void onClick(View v) {

    }

}
