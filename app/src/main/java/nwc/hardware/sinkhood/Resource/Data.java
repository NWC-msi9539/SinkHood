package nwc.hardware.sinkhood.Resource;

public class Data {
    private static Data data;

    public static final int POWER = 0;
    public static final int WHITE = 1;
    public static final int FAN = 2;
    public static final int TIMER = 3;
    public static final int UV = 4;
    public static final int IR = 5;

    public static final int S_POWER = 1;
    public static final int S_WHITE = 2;
    public static final int S_FAN = 3;
    public static final int S_TIMER = 4;
    public static final int S_UV = 5;
    public static final int S_IR = 6;
    public static final int S_FANSTEP = 7;
    public static final int S_TIME_UPPER = 8;
    public static final int S_TIME_LOWER = 9;

    private int fanStep = 0;
    private int time = 0;

    private byte startByte = 'K';
    private byte[] endByte = { 'E', 0x0D };
    private boolean[] datas = {false, false, false, false, false, false};

    private Data(){}

    public static Data getInstance(){
        if(data == null){
            data = new Data();
        }
        return data;
    }

    public boolean getData(int type){
        return datas[type];
    }

    public boolean[] getDatas(){
        return datas;
    }

    public void setData(int type, boolean value){
        datas[type] = value;
    }

    public void switchData(int type){
        datas[type] = !datas[type];
    }

    public void setDatas(boolean[] datas){
        this.datas = datas;
    }

    public int getFanStep() {
        return fanStep;
    }

    public void setFanStep(int fanStep) {
        this.fanStep = fanStep;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void resetDatas(){
        boolean[] newDatas = {false, false, false, false, false, false};
        fanStep = 0;
        time = 0;
        datas = newDatas;
    }

    public byte[] getDataStream(int type){
        byte[] dataStream = new byte[9];
        dataStream[0] = startByte;
        if(type == POWER){ dataStream[1] = '1'; }else{ dataStream[1] = '0';}
        if(type == WHITE){ dataStream[2] = '1'; }else{ dataStream[2] = '0';}
        if(type == FAN){ dataStream[3] = '1'; }else{ dataStream[3] = '0';}
        if(type == TIMER){ dataStream[4] = '1'; }else{ dataStream[4] = '0';}
        if(type == UV){ dataStream[5] = '1'; }else{ dataStream[5] = '0';}
        if(type == IR){ dataStream[6] = '1'; }else{ dataStream[6] = '0';}
        dataStream[7] = endByte[0];
        dataStream[8] = endByte[1];

        return dataStream;
    }
}
