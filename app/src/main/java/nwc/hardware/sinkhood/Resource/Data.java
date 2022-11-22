package nwc.hardware.sinkhood.Resource;

public class Data {
    private static Data data;

    public final int POWER = 0;
    public final int WHITE = 1;
    public final int FAN = 2;
    public final int TIMER = 3;
    public final int UV = 4;
    public final int IR = 5;

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

    public void resetDatas(){
        boolean[] newDatas = {false, false, false, false, false, false};
        datas = newDatas;
    }
}
