package nwc.hardware.sinkhood.Listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nwc.hardware.sinkhood.R;
import nwc.hardware.sinkhood.Resource.Data;

public class RemoteFanClickListener implements View.OnClickListener {
    private boolean active = false;
    private ImageView fanIMG;
    private TextView fanLV;
    private Context mContext;

    public RemoteFanClickListener(ImageView fanIMG, TextView fanLV, Context mContext){
        this.fanIMG = fanIMG;
        this.fanLV = fanLV;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
    }

}
