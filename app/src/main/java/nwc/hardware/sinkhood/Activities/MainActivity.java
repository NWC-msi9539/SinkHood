package nwc.hardware.sinkhood.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import nwc.hardware.sinkhood.Listeners.RemoteButtonClickListener;
import nwc.hardware.sinkhood.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton lightBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightBTN = findViewById(R.id.lightBTN);
        lightBTN.setOnClickListener(new RemoteButtonClickListener(lightBTN, getApplicationContext()));

    }
}