package zhaoq.hl.fastdelivery;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.hardware.barcode.Scanner;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import zhaoq.hl.fastdelivery.callback.DialogCallback;
import zhaoq.hl.fastdelivery.utils.KeyboardUtil;

/**
 * PACKAGE_NAME:zhaoq.hl.fastdelivery
 * CREATE_BY:zhaoqiang
 * AUTHOR_EMAIL:zhaoq_hero@163.com
 * DATE: 2016/03/19  17:02
 */
public class ConsumDialog extends Dialog implements View.OnClickListener, TextWatcher, View.OnTouchListener {
    private Context context;
    private DialogCallback callback;

    public ConsumDialog(Context context) {
        super(context);
        this.context = context;
        callback = (DialogCallback) context;
    }
    public ConsumDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        callback = (DialogCallback) context;
    }

    private EditText inputNo;
    private Button sure,exit;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.input_queryno_dialog,null);
        this.setContentView(view);

        inputNo = (EditText) view.findViewById(R.id.dialog_input_queryno);
        sure = (Button) view.findViewById(R.id.dialog_input_querysure);
        exit = (Button) view.findViewById(R.id.dialog_input_queryexit);

        sure.setOnClickListener(this);
        exit.setOnClickListener(this);

        inputNo.setOnTouchListener(this);
        inputNo.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        String saleSheetNo = inputNo.getText().toString().trim();
        switch(v.getId()){
            case R.id.dialog_input_querysure://确认按钮
                if(saleSheetNo!=null&&!saleSheetNo.equals("")){
                    callback.callResult(saleSheetNo);
                    dismiss();
                }else{
                    inputNo.setError("数据为空");
                }
                break;
            case R.id.dialog_input_queryexit://取消按钮
                dismiss();
                break;
            default:
                break;
        }
    }


    /**
     * 监听文本变化   文本改变后  修改文本内容
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /**
     * 设置   edittext的   触摸事件
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_UP){
            inputNo.setInputType(InputType.TYPE_NULL);
            new KeyboardUtil(view, context, inputNo).showKeyboard();
            return true;
        }
        return false;
    }
}
