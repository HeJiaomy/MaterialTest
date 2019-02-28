package com.example.materialtest.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by hejiao on 2017/12/19.
 */

public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //具体的逻辑操作
            }
        }).start();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int aHour = 60 * 60 * 1000;
        long tiggerAtTime = SystemClock.elapsedRealtime() + aHour; //系统开机至今经历的毫秒数 // System.currentTimeMillis();1970年1月1日0点至今经历的毫秒数
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, tiggerAtTime, pi);     //setExact()保证定时更准确
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //要求Alarm任务在Doze模式下也能正常执行
            manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, tiggerAtTime, pi);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
