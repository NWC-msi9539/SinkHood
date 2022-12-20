package nwc.hardware.sinkhood.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;


import android.annotation.SuppressLint;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;


import nwc.hardware.Interfaces.OnGattListener;

import nwc.hardware.bletool.BluetoothGeneralTool;

import nwc.hardware.sinkhood.Listeners.RemoteFanClickListener;
import nwc.hardware.sinkhood.Listeners.RemoteImageButtonClickListener;
import nwc.hardware.sinkhood.Listeners.RemoteTimerClickListener;
import nwc.hardware.sinkhood.R;
import nwc.hardware.sinkhood.Resource.Data;

public class MainActivity extends AppCompatActivity {
    private TextView fanLVTXT;
    private TextView deviceNameTXT;
    private TextView ConnectionTXT;
    private View divider;

    private ImageButton lightBTN;
    private ImageButton powerBTN;
    private ImageButton uvBTN;
    private ImageButton irBTN;
    private ImageButton fanBTN;

    private ImageButton bluetoothBTN;

    private ImageView mainTimer;
    private ImageView subTimer;
    private Button timerBTN;

    private BluetoothGeneralTool bluetoothConnectingTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setButtonsListener();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        bluetoothConnectingTool = BluetoothGeneralTool.getInstance(new OnGattListener() {
            @Override
            public void connectionStateConnecting(BluetoothGatt bluetoothGatt) {

            }

            @Override
            public void connectionStateConnected(BluetoothGatt bluetoothGatt) {

            }

            @Override
            public void connectionStateDisconnected(BluetoothGatt bluetoothGatt) {
                ConnectionTXT.setText("연결 끊김");
                BluetoothGeneralTool.getInstance().close();
                Data.getInstance().resetDatas();
                repaintBTN();
            }

            @Override
            public void discoveredServices() {

            }

            @Override
            public void characteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                Log.d("TESTING", "Write Success!!");
            }

            @Override
            public void characteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                Log.d("TESTING", "Read Success!!");
            }

            @Override
            public void characteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                byte[] datas = bluetoothGattCharacteristic.getValue();
                Log.d("TESTING", "Notify Length --> " + datas.length);
                for(byte b : datas){
                    Log.d("TESTING", "Notify Pop : " + (char)(b));
                }
                parsingData(datas);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        repaintBTN();
                    }
                });
            }

            @Override
            public void readRssi(BluetoothGatt bluetoothGatt, int i, int i1) {

            }

            public void parsingData(byte[] data){
                Log.d("TESTING", "Start Parsing Data");
                Data tool = Data.getInstance();
                if(data[0] == 'S'){
                    Log.d("TESTING", "Find Sender Flagment!!");
                    byte power = data[Data.S_POWER];
                    byte white = data[Data.S_WHITE];
                    byte fan = data[Data.S_FAN];
                    byte timer = data[Data.S_TIMER];
                    byte uv = data[Data.S_UV];
                    byte ir = data[Data.S_IR];
                    byte fanstep = data[Data.S_FANSTEP];
                    String time = String.valueOf((char)data[Data.S_TIME_UPPER]);
                    time += (char)data[Data.S_TIME_LOWER];

                    if(power == '1'){ tool.setData(Data.POWER, true); } else { tool.resetDatas(); Log.d("TESTING", "Parsing Done!!"); return; }
                    if(white == '1'){ tool.setData(Data.WHITE, true); } else { tool.setData(Data.WHITE, false); }
                    if(fan == '1'){ tool.setData(Data.FAN, true); } else { tool.setData(Data.FAN, false); }
                    if(timer == '1'){ tool.setData(Data.TIMER, true); } else { tool.setData(Data.TIMER, false); }
                    if(uv == '1'){ tool.setData(Data.UV, true); } else { tool.setData(Data.UV, false); }
                    if(ir == '1'){ tool.setData(Data.IR, true); } else { tool.setData(Data.IR, false); }
                    tool.setFanStep(Integer.parseInt(String.valueOf((char)fanstep)));
                    tool.setTime(Integer.parseInt(time));
                    Log.d("TESTING", "Parsing Done!!");
                }
            }
        });

        BluetoothGatt gatt =  bluetoothConnectingTool.getGatt();
        if(gatt != null){
            ConnectionTXT.setText("연결 됌");
            ConnectionTXT.setGravity(Gravity.CENTER | Gravity.LEFT);
            deviceNameTXT.setText(gatt.getDevice().getName());
            deviceNameTXT.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
        }else{
            ConnectionTXT.setText("기기를 연결해주세요.");
            ConnectionTXT.setGravity(Gravity.CENTER);
            deviceNameTXT.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
        }
    }

    public void repaintBTN(){
        Data tool = Data.getInstance();
        boolean[] datas = tool.getDatas();
        if(datas[Data.POWER]){
            powerBTN.setBackground(getDrawable(R.drawable.active_button_back));
        }else{
            powerBTN.setBackground(null);
        }
        if(datas[Data.WHITE]){
            lightBTN.setBackground(getDrawable(R.drawable.active_button_back));
        }else{
            lightBTN.setBackground(null);
        }
        if(datas[Data.UV]){
            uvBTN.setBackground(getDrawable(R.drawable.active_button_back));
        }else{
            uvBTN.setBackground(null);
        }
        if(datas[Data.IR]){
            irBTN.setBackground(getDrawable(R.drawable.active_button_back));
        }else{
            irBTN.setBackground(null);
        }
        if(datas[Data.FAN]){
            fanBTN.setBackground(getDrawable(R.drawable.active_button_back));
            fanLVTXT.setBackground(getDrawable(R.drawable.active_button_back));
            fanLVTXT.setText("Lv" + tool.getFanStep());
        }else{
            fanBTN.setBackground(null);
            fanLVTXT.setBackground(null);
            fanLVTXT.setText("Lv0");
        }
        if(datas[Data.TIMER]){
            mainTimer.setBackground(getDrawable(R.drawable.active_button_back));
            subTimer.setBackground(getDrawable(R.drawable.active_button_back));
            timerBTN.setText(tool.getTime() + "분");
        }else{
            mainTimer.setBackground(null);
            subTimer.setBackground(null);
            timerBTN.setText("00분");
        }
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
        bluetoothBTN = findViewById(R.id.bluetoothBTN);
        fanLVTXT = findViewById(R.id.fanTXT);
        deviceNameTXT = findViewById(R.id.deviceNameTXT);
        ConnectionTXT = findViewById(R.id.ConnectionTXT);
        divider = findViewById(R.id.deviceDVD);
    }

    private void setButtonsListener(){
        powerBTN.setOnClickListener(new RemoteImageButtonClickListener(powerBTN, getApplicationContext(), Data.POWER){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "powerBTN ON Click!");
                Data tool = Data.getInstance();
                tool.switchData(Data.POWER);
                bluetoothConnectingTool.Write(tool.getDataStream(Data.POWER));
            }
        });
        lightBTN.setOnClickListener(new RemoteImageButtonClickListener(lightBTN, getApplicationContext(), Data.WHITE){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "lightBTN ON Click!");
                Data tool = Data.getInstance();
                tool.switchData(Data.WHITE);
                bluetoothConnectingTool.Write(tool.getDataStream(Data.WHITE));
            }
        });
        uvBTN.setOnClickListener(new RemoteImageButtonClickListener(uvBTN, getApplicationContext(), Data.UV){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "uvBTN ON Click!");
                Data tool = Data.getInstance();
                tool.switchData(Data.UV);
                bluetoothConnectingTool.Write(tool.getDataStream(Data.UV));
            }
        });
        irBTN.setOnClickListener(new RemoteImageButtonClickListener(irBTN, getApplicationContext(), Data.IR){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "irBTN ON Click!");
                Data tool = Data.getInstance();
                tool.switchData(Data.IR);
                bluetoothConnectingTool.Write(tool.getDataStream(Data.IR));
            }
        });
        fanBTN.setOnClickListener(new RemoteFanClickListener(fanBTN, fanLVTXT, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "fanBTN ON Click!");
                Data tool = Data.getInstance();
                bluetoothConnectingTool.Write(tool.getDataStream(Data.FAN));
            }
        });
        timerBTN.setOnClickListener(new RemoteTimerClickListener(mainTimer, subTimer, getApplicationContext()){
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Log.d("TESTING", "timer ON Click!");
                Data tool = Data.getInstance();
                bluetoothConnectingTool.Write(tool.getDataStream(Data.TIMER));
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
}