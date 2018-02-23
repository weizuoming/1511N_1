package com.dash.a1511n.util;

/**
 * Created by Dash
 */
import com.dash.a1511n.application.DashApplication;


/**
 * Created by Dash
 */
public class CommonUtils {

    /**
     * 自己写的运行在主线程的方法
     * 如果是主线程,执行任务,否则使用handler发送到主线程中去执行
     *
     *
     * @param runable
     */
    public static void runOnUIThread(Runnable runable) {
        //先判断当前属于子线程还是主线程
        if (android.os.Process.myTid() == DashApplication.getMainThreadId()) {
            runable.run();
        } else {
            //子线程
            DashApplication.getAppHanler().post(runable);
        }
    }
}
