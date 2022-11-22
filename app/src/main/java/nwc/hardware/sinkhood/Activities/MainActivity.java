package nwc.hardware.sinkhood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nwc.hardware.Adapters.SearchingDeviceAdapter;
import nwc.hardware.CHeaderTest;
import nwc.hardware.Interfaces.OnGattListener;
import nwc.hardware.Test;
import nwc.hardware.bletool.BluetoothCommunicationTool;
import nwc.hardware.bletool.BluetoothConnectingTool;
import nwc.hardware.bletool.BluetoothPermissionTool;
import nwc.hardware.bletool.BluetoothSearchingTool;
import nwc.hardware.sinkhood.Listeners.RemoteImageButtonClickListener;
import nwc.hardware.sinkhood.Listeners.RemoteTimerClickListener;
import nwc.hardware.sinkhood.R;

public class MainActivity extends AppCompatActivity {
    BluetoothCommunicationTool bluetoothCommunicationTool;

    private FrameLayout settingLayout;

    private ImageButton lightBTN;
    private ImageButton powerBTN;
    private ImageButton uvBTN;
    private ImageButton irBTN;
    private ImageButton fanBTN;

    private ImageButton settingBTN;
    private ImageButton settingCloseBTN;

    private ImageButton bluetoothBTN;

    private ToggleButton toggleButton;
    private ImageView mainTimer;
    private ImageView subTimer;
    private Button timerBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setButtonsListener();

    }

    private void initialize(){
        lightBTN = findViewById(R.id.lightBTN);
        powerBTN = findViewById(R.id.powerBTN);
        uvBTN = findViewById(R.id.uvBTN);
        irBTN = findViewById(R.id.irBTN);
        fanBTN = findViewById(R.id.fanBTN);
        timerBTN = findViewById(R.id.timerBTN);
        mainTimer = findViewById(R.id.timerBack);
        subTimer = findViewById(R.id.timerIMG);
        toggleButton = findViewById(R.id.fanToggleBTN);
        settingBTN = findViewById(R.id.settingBTN);
        settingCloseBTN = findViewById(R.id.settingCloseBTN);
        bluetoothBTN = findViewById(R.id.bluetoothBTN);
        settingLayout = findViewById(R.id.SettingLayout);
        settingLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void setButtonsListener(){
        powerBTN.setOnClickListener(new RemoteImageButtonClickListener(powerBTN, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                BluetoothConnectingTool.getInstance(new OnGattListener() {
                    @Override
                    public void connectionStateConnecting(BluetoothGatt bluetoothGatt) {

                    }

                    @Override
                    public void connectionStateConnected(BluetoothGatt bluetoothGatt) {

                    }

                    @Override
                    public void connectionStateDisconnected(BluetoothGatt bluetoothGatt) {

                    }

                    @Override
                    public void discoveredServices() {

                    }

                    @Override
                    public void characteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {

                    }

                    @Override
                    public void characteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {

                    }

                    @Override
                    public void characteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {

                    }

                    @Override
                    public void readRssi(BluetoothGatt bluetoothGatt, int i, int i1) {

                    }
                });
                Log.d("TESTING", "powerBTN ON Click!");
            }
        });
        lightBTN.setOnClickListener(new RemoteImageButtonClickListener(lightBTN, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "lightBTN ON Click!");
            }
        });
        uvBTN.setOnClickListener(new RemoteImageButtonClickListener(uvBTN, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "uvBTN ON Click!");
            }
        });
        irBTN.setOnClickListener(new RemoteImageButtonClickListener(irBTN, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "irBTN ON Click!");
            }
        });
        fanBTN.setOnClickListener(new RemoteImageButtonClickListener(fanBTN, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "fanBTN ON Click!");
            }
        });
        timerBTN.setOnClickListener(new RemoteTimerClickListener(mainTimer, subTimer, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "timer ON Click!");
            }
        });
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    toggleButton.setBackgroundDrawable(getBaseContext().getDrawable(R.drawable.toggle_on_button_back));
                    toggleButton.setTextColor(Color.parseColor("#000000"));
                }else{
                    toggleButton.setBackgroundDrawable(getBaseContext().getDrawable(R.drawable.toggle_off_button_back));
                    toggleButton.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }
        });

        settingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingLayout.setVisibility(View.VISIBLE);
            }
        });
        settingCloseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingLayout.setVisibility(View.GONE);
            }
        });

        bluetoothBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchingBluetoothActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(settingLayout.getVisibility() == View.VISIBLE){
                settingLayout.setVisibility(View.GONE);
            }else{
                finish();
            }
        }
        return false;
    }
}