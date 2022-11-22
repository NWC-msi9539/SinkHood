package nwc.hardware.sinkhood.Listeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import nwc.hardware.sinkhood.R;

public class RemoteImageButtonClickListener implements View.OnClickListener {
    private boolean active = false;
    private ImageButton parent;
    private Context mContext;

    public RemoteImageButtonClickListener(ImageButton button, Context mContext){
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
            parent.setBackground(null);
        }else{
            parent.setBackground(mContext.getDrawable(R.drawable. active_button_back));
        }
        active = !active;
    }
}
