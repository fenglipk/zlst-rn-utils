
package com.zlst.rn;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import java.io.File;

public class RNZlstRnUtilsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNZlstRnUtilsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  /**
   * 要导出一个方法给JavaScript使用，Java方法需要使用注解@ReactMethod。
   * 方法的返回类型必须为void。React Native的跨语言访问是异步进行的，
   * 所以想要给JavaScript返回一个值的唯一办法是使用回调函数或者发送事件
   * @param message
   * @param duration
   */
  @ReactMethod
  public void setBadge(int num) {
    BadgeUtils.addNumShortCut(this.reactContext,num);
  }

  @ReactMethod
  public void exitAPP() {
    reactContext.getCurrentActivity().finish();
    System.exit(0);
  }

  @ReactMethod
  public void installApk(String apkPath) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //版本在7.0以上是不能直接通过uri访问的
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
      File file = (new File(apkPath));
      // 由于没有在Activity环境下启动Activity,设置下面的标签
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
      Uri apkUri = FileProvider.getUriForFile(reactContext, "com.rnproject", file);
      //添加这一句表示对目标应用临时授权该Uri所代表的文件
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
    } else {
      intent.setDataAndType(Uri.fromFile(new File(apkPath)),
        "application/vnd.android.package-archive");
    }
    reactContext.startActivity(intent);
  }
  
  @Override
  public String getName() {
    return "RNZlstUtils";
  }
}