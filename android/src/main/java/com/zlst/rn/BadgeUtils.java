package com.zlst.rn;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.zlst.rn.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BadgeUtils {

  public static void addNumShortCut(Context context, int num) {

    if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
      setBadgeOfXiaoMi(context,num);

    } else if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
      setBadgeOfHuaWei(context,num);

    } else if (Build.MANUFACTURER.equalsIgnoreCase("vivo")) {
      setBadgeOfVIVO(context,num);

    } else {//其他原生系统手机

    }
  }

  public static void setBadgeOfHuaWei(Context context,int num){
    try{
      Bundle bunlde =new Bundle();
      bunlde.putString("package", context.getPackageName()); // 包名
      bunlde.putString("class", context.getClass().getName()); //类名
      bunlde.putInt("badgenumber",num);
      Bundle res= context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public static void setBadgeOfXiaoMi(Context context, int count) {
    NotificationManager mNotificationManager = (NotificationManager) context
      .getSystemService(Context.NOTIFICATION_SERVICE);
    Notification.Builder builder = new Notification.Builder(context)
      .setContentTitle("消息通知").setContentText("你有"+count+"条未处理消息").setSmallIcon(R.drawable.ic_launcher);
    Notification notification = builder.build();

    try {
      Field field = notification.getClass().getDeclaredField("extraNotification");
      Object extraNotification = field.get(notification);
      Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
      method.invoke(extraNotification, count);


    } catch (Exception e) {
      e.printStackTrace();
    }
    mNotificationManager.notify(1, notification);
  }

  private static void setBadgeOfVIVO(Context context,int num){
    try {
      NotificationManager mNotificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
      Notification.Builder builder = new Notification.Builder(context)
        .setContentTitle("消息通知").setContentText("你有"+num+"条未处理消息").setSmallIcon(R.drawable.ic_launcher);
      Notification notification = builder.build();

      Intent localIntent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
      localIntent.putExtra("packageName", context.getPackageName());
      localIntent.putExtra("className", context.getClass().getName());
      localIntent.putExtra("notificationNum", num);
      context.sendBroadcast(localIntent);

      NotificationManager notifyMgr = (NotificationManager)(context.getSystemService(Context.NOTIFICATION_SERVICE));
      if(num!=0)notifyMgr.notify(1, notification);else notifyMgr.cancel(1);

    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
