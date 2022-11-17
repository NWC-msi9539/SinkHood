package nwc.hardware.sinkhood.Listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;

import nwc.hardware.sinkhood.R;

public class RemoteButtonClickListener implements View.OnClickListener {
    private boolean active = false;
    private ImageButton parent;
    private Context mContext;

    public RemoteButtonClickListener(ImageButton button, Context mContext){
        parent = button;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View v) {
        repaint();
    }

    @SuppressLint("ResourceType")
    public void repaint(){
        if(active){
            parent.setBackground(mContext.getDrawable(R.drawable. nonactive_button_back));
        }else{
            parent.setBackground(mContext.getDrawable(R.drawable.active_button_back));
        }
        active = !active;
    }
}
