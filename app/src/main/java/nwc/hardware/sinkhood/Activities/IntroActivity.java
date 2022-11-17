package nwc.hardware.sinkhood.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import nwc.hardware.sinkhood.R;

public class IntroActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        TextView textView = findViewById(R.id.textView);

        int[] gradient = {getColor(R.color.end), getColor(R.color.start)};
        float[] position = {0, 1};
        Shader.TileMode tileMode = Shader.TileMode.MIRROR;

        LinearGradient linearGradient = new LinearGradient(0, 0, textView.getTextSize() * 15, textView.getTextSize(), gradient, position, tileMode);
        Shader shader = linearGradient;

        textView.getPaint().setShader(shader);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

}