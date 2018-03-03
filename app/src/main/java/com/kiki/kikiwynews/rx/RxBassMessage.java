package com.kiki.kikiwynews.rx;

/**
 * Created by KIKI on 2018/1/6.
 * 346409606@qq.com
 */

public class RxBassMessage {
    private int code;
    private Object object;
    private RxBassMessage(){}
    public RxBassMessage(int code,Object object){
        this.code=code;
        this.object=object;
    }

    public int getCode(){
        return code;
    }

    public Object getObject(){
        return object;
    }
}
