package com.kiki.kikiwynews.rx;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by KIKI on 2018/1/6.
 * 346409606@qq.com
 */

public class RxBus {
    private static volatile RxBus mDefaultInstance;

    private RxBus(){}

    public static RxBus getDefault(){
        if(mDefaultInstance==null){
            synchronized (RxBus.class){
                if(mDefaultInstance==null){
                    mDefaultInstance=new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    /**
     * Subject
     */
    private final Subject<Object,Object> _bus=new SerializedSubject<>(PublishSubject.create());

    public void send(Object o){
        _bus.onNext(o);
    }

    public Observable<Object> toObservable(){
        return _bus;
    }

    /**
     * 根据传递的eventType类型返回特定类型 被观察者
     * @param eventType 事件类型
     * @return
     */
    public <T> Observable<T> toObservable(Class<T> eventType){
        return _bus.ofType(eventType);
    }

    /**
     * 根据传递的code和eventType类型返回特定类型（eventType）的 被观察者
     * @param code
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObservable(final int code,final Class<T> eventType){
        return _bus.ofType(RxBassMessage.class)
                .filter(new Func1<RxBassMessage, Boolean>() {
                    @Override
                    public Boolean call(RxBassMessage rxBassMessage) {
                        //过滤code和eventType都相同的事件
                        return rxBassMessage.getCode()==code && eventType.isInstance(rxBassMessage.getObject());
                    }
                }).map(new Func1<RxBassMessage, Object>() {
                    @Override
                    public Object call(RxBassMessage rxBassMessage) {
                        return rxBassMessage.getObject();
                    }
                }).cast(eventType);

    }

    public boolean hadObsevers(){
        return _bus.hasObservers();
    }


    /**
     * 提供了一个新的事件，根据code进行分发
     * @param code 事件code
     * @param o
     */
    public void post(int code,Object o){
        _bus.onNext(new RxBassMessage(code,o));
    }

    /**
     * 提供了一个新的事件，单一类型
     * @param o 事件数据
     */
    public void post(Object o){
        _bus.onNext(o);
    }
}
