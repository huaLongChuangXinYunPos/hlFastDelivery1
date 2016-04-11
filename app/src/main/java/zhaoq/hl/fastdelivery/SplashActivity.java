package zhaoq.hl.fastdelivery;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import zhaoq.hl.fastdelivery.activity.MainActivity;
import zhaoq.hl.fastdelivery.callback.TaskCallback;
import zhaoq.hl.fastdelivery.entity.VersionEntity;
import zhaoq.hl.fastdelivery.task.GetVersionTask;
import zhaoq.hl.fastdelivery.task.TaskResult;
import zhaoq.hl.fastdelivery.utils.MyToast;
import zhaoq.hl.fastdelivery.utils.ParseUtils;

public class SplashActivity extends AppCompatActivity implements TaskCallback {

    private SharedPreferences sp;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //判断  状态码：
            switch (msg.what){
                case CODE_UPDATE_DIALOG:
                    showUpdateDialog();//弹出更新对话框
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;

                default:
                    break;
            }
        }
    };

    /**
     * 弹出对话框
     */
    private void showUpdateDialog() {
        Log.i("info","--------------");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("最新版本:"+ mVersionCode);
        builder.setMessage(mDesc);

        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO 更新版本信息
                downLoad();
            }
        });
        builder.setNegativeButton("以后再说",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //用户  取消   对话框时：
                enterHome();
            }
        });
        builder.show();
    }

    /**
     * 进入到  主界面：
     */
    private void enterHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private static final int CODE_ENTER_HOME = 4;//进入到  主界面：

    private static final int CODE_UPDATE_DIALOG =0;//获取  版本信息  成功

    private TextView tvProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tvProgress = (TextView) findViewById(R.id.tv_progress);

        sp = getSharedPreferences(Configs.SP_FILE_NAME,MODE_PRIVATE);
        boolean autoUpdate = sp.getBoolean("auto_update",true);

        if(autoUpdate){
            new GetVersionTask(this).execute();//检查   版本更新
        }else{
            //延迟消息  并进入主界面
            mHandler.sendEmptyMessageDelayed(CODE_ENTER_HOME,2500);
        }
    }
    private float mVersionCode;
    private String mDesc;
    private String mDownLoadUrl;
    /**
     * 获取  版本信息
     * @param result
     */
    @Override
    public void onTaskFinished(TaskResult result) {
        try {
            switch (result.task_id){
                case Configs.GET_VERSION_INFO_AUTHORITY:

                    if(result.resultStatus==1){
                        JSONObject object = (JSONObject) result.data;
                        JSONArray array = object.getJSONArray("dDate");
                        ArrayList<VersionEntity> list = ParseUtils.parseVersionList(array);

                        mVersionCode = Float.parseFloat(list.get(0).getCVersCode());
                        mDesc = list.get(0).getCBeizhu();
                        mDownLoadUrl = list.get(0).getCUrl();

                        //将信息  保存到  sharePrefrefance
                        versionInfoToSharePreferance(mDownLoadUrl);
                        Message msg = Message.obtain();
                        //判断 版本大小
                        if(mVersionCode > getVersionCode()){
                            //说明   有新的   软件需要更新
                            msg.what = CODE_UPDATE_DIALOG;//创建 成员变量
                        }else{
                            //没有  版本更新    调到 进入主界面
                            msg.what = CODE_ENTER_HOME;//进入到  主界面
                        }
                        mHandler.sendMessage(msg);
                    }
                    if(result.resultStatus==-1){
                        //未获取到  数据  直接进入界面
                        enterHome();
                    }
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * 将版本信息  保存到本地共享参数
     */
    private void versionInfoToSharePreferance(String mDownLoadUrl) {
        SharedPreferences sp = getSharedPreferences(Configs.SP_FILE_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Configs.SERVER_DOWNLOAD_NEWVERSION,mDownLoadUrl);
        editor.commit();
    }

    //获取  软件版本
    private float getVersionCode(){
        PackageManager packagerManager = getPackageManager();
        try {
            PackageInfo packageInfo = packagerManager.getPackageInfo(
                    getPackageName(),0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }//获取  包的信息
        return -1;
    }

    //下载  apk  升级版本   文件
    protected void downLoad() {
        //判断  sdk   是否挂载
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            tvProgress.setVisibility(View.VISIBLE);//显示   进度

            String target = Environment.getExternalStorageDirectory()+"/load.apk";
            //使用   xutils下载
            HttpUtils utils = new HttpUtils();
            utils.download(mDownLoadUrl, target, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> arg0) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setDataAndType(
                            Uri.fromFile(arg0.result),
                            "application/vnd.android.package-archive");
                    startActivityForResult(intent,0);//如果用户取消  安装的话   返回结果：
                }

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    tvProgress.setText("下载进度：" + ((current * 100) / total) + "%");
                    MyToast.ToastIncenter(SplashActivity.this, "下载成功..");
                }

                @Override
                public void onFailure(HttpException arg0, String arg1) {
                    MyToast.ToastIncenter(SplashActivity.this,"下载失败,请检查网络..");
                }
            });
        }else{
            MyToast.ToastIncenter(SplashActivity.this,"当前手机未挂载SD卡");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){//进入主界面：
            enterHome();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
