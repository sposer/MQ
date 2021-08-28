package top.heue.mq.bean;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.hjq.toast.ToastUtils;

import top.heue.mq.base.BaseActivity;
import top.heue.mq.service.AppService;
import top.heue.mq.service.MQService;
import top.heue.utils.log.L;

public class LoginBean {
    public String uid = "";
    public String pwd = "";

    public BaseActivity activity;

    private MQService mService;
    public void login() {
        final ServiceConnection con = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = activity.getService(service);
                mService.initBot(Long.parseLong(uid), pwd);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        final Intent intent = new Intent(activity, MQService.class);
        activity.bindService(intent, con, BIND_AUTO_CREATE);
    }
}