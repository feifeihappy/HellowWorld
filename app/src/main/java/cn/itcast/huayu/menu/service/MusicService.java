package cn.itcast.huayu.menu.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.itcast.huayu.menu.R;

/**
 * @author ln：zpf on 2016/8/15
 */
public class MusicService extends Service {

    private MediaPlayer mMediaPlayer;
    public static String mText;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "MusicService_____onBind:");
        return new MusicServiceBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "onUnbind:");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MusicService___onCreate:");
        //播放音乐
/*        mMediaPlayer = MediaPlayer.create(this, R.raw.music);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();*/
        mText = "确定拿到service对象";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        super.onDestroy();
        Log.e("TAG", "MusicService____onDestroy:");
    }

   public class MusicServiceBinder extends Binder {

        public MusicService getMusicService() {
            return MusicService.this;
        }
    }


}
