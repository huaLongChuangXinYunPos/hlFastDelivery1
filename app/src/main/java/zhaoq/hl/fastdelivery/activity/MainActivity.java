package zhaoq.hl.fastdelivery.activity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.barcode.Scanner;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zbar.lib.CaptureActivity;

import java.util.ArrayList;

import zhaoq.hl.fastdelivery.ConsumDialog;
import zhaoq.hl.fastdelivery.utils.ApplicationUtils;
import zhaoq.hl.fastdelivery.utils.MyToast;
import zhaoq.hl.fastdelivery.view.ViewPagerTranfAnim;
import zhaoq.hl.fastdelivery.callback.DialogCallback;
import zhaoq.hl.fastdelivery.R;
import zhaoq.hl.fastdelivery.adapter.MyFragmentPagerAdapter;
import zhaoq.hl.fastdelivery.entity.FormDao;
import zhaoq.hl.fastdelivery.entity.SaleFormInfoDao;
import zhaoq.hl.fastdelivery.fragments.QueryFragment;
import zhaoq.hl.fastdelivery.fragments.ResultFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener,DialogCallback,QueryFragment.QFCallback{

    private ViewPager viewPager;

    private Fragment[] fragments;

    private RadioGroup radioGroup;

    private RadioButton radt1,radt2;

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radt1 = (RadioButton) findViewById(R.id.main_tab_query);
        radt2 = (RadioButton) findViewById(R.id.main_tab_result);

        imageView = (ImageView) findViewById(R.id.anim_pregress);

        initList();

        MyFragmentPagerAdapter adapter =
                new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(adapter);

        radioGroup = (RadioGroup) findViewById(R.id.main_tab);
        radioGroup.setOnCheckedChangeListener(this);

        viewPager.setPageTransformer(true, new ViewPagerTranfAnim());

        viewPager.addOnPageChangeListener(this);
    }

    private void initList() {
        fragments = new Fragment[2];
        fragments[0] = new QueryFragment();
        fragments[1] = new ResultFragment();
    }

    /**
     * 点击  事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = 0;
        switch(checkedId){
            case R.id.main_tab_query:
                index = 0;
                break;
            case R.id.main_tab_result:
                index = 1;
                break;
            default:
                break;
        }
//        显示  或隐藏  当前 fragment
        for (int i= 0;i<fragments.length;i++){
            if(i==index){
               viewPager.setCurrentItem(i);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        viewPager.setCurrentItem(position);
        if(position==0){
            radt1.setChecked(true);
            radt2.setChecked(false);
        }else{
            radt1.setChecked(false);
            radt2.setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private static final int TO_SCAN_Activity = 4;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch(id){
            case R.id.menu_input:
                //弹出  dialog  输入商品单号
                ConsumDialog dialog = new ConsumDialog(this,R.style.DialogStyle);
                dialog.show();
                break;

            case R.id.main_menu_scan:
                Intent intent = new Intent();
                intent.setClass(this, CaptureActivity.class);
                startActivityForResult(intent,TO_SCAN_Activity);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 实现  回调方法
     * @param result
     */
    @Override
    public void callResult(String result) {
        if(result!=null&&!result.equals("")){
            queryFormNo(result);
        }
    }

    /**
     * 传入数据单  开始查询
     * @param result
     */
    public void queryFormNo(String result){
        viewPager.setCurrentItem(0);
        //弹出   进度对话框  查询表单数据信息
        ArrayList<String> list = new ArrayList<String>();
        FormDao dao = new FormDao();
        dao.setcSaleSheetNo(result);
        dao.setDate1("");
        dao.setDate2("");
        String json = new Gson().toJson(dao);
        list.add(json);
        //调用  Fragment的方法   更新ui
        QueryFragment fragment = (QueryFragment) getSupportFragmentManager().getFragments().get(0);
        fragment.adapterNotify(list,imageView);
    }

    /**
     * 点击条目的回调方法
     * @param dao
     */
    @Override
    public void qfCallback(SaleFormInfoDao dao) {
        viewPager.setCurrentItem(1);
        //打开  fragment
        ResultFragment fragment = (ResultFragment) fragments[1];
        fragment.update(dao, imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(data!=null){
                switch(requestCode){
                    case TO_SCAN_Activity:
                        Bundle bundle = data.getBundleExtra("bundle");
                        String formNo = bundle.getString("result");
                        queryFormNo(formNo);
                        break;
                    default :
                        break;
                }
            }
        }
    }

    //间隔时间
    private static long exectTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exectTime)>2000){//第一次点击
                MyToast.ToastIncenter(this,"再次点击退出程序").show();
                exectTime = System.currentTimeMillis();
            }else{
                finish();
                ApplicationUtils.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
