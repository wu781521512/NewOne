package com.example.mrwuchao.newone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.utils.SharedPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegistActivity extends NotitleActivity {

    @BindView(R.id.regist_phone_edit)
    EditText registPp;
    @BindView(R.id.regist_yanzheng_edit)
    EditText yanzhengEdit;
    @BindView(R.id.regist_name_edit)
    EditText nameEdit;
    @BindView(R.id.regist_pass_edit)
    EditText passEdit;
    @BindView(R.id.regist_queren)
    Button queren;
    @BindView(R.id.regist_get_code_btn)
    Button codeBtn;
    int num = 60;
    boolean isAlive = true;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            codeBtn.setText(msg.arg1 + "s");
            if (msg.what == 0x1) {
                if (msg.arg1 < 0) {
                    isAlive = false;
                    codeBtn.setClickable(true);
                    codeBtn.setText("获取验证码");
                    codeBtn.setBackgroundColor(getResources().getColor(R.color.juhuang));
                    num = 60;
                }else{
                    codeBtn.setClickable(false);
                    codeBtn.setText(msg.arg1+"s");
                    codeBtn.setBackgroundColor(getResources().getColor(R.color.find_hui));
                }
            }
           if (msg.what == 0x2) {
               Log.i("submit","消息收到了");
               isAlive = false;
               finish();
               Intent intent = new Intent(RegistActivity.this,MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
           }

            return true;
        }
    });

    EventHandler eh = new EventHandler() {

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.i("submit","提交验证码成功");
                    //提交验证码成功  保存用户信息
                    SharedPUtil sharedPUtil = new SharedPUtil(getBaseContext());
                    sharedPUtil.setUserInfo(registPp.getText().toString().trim(),
                            passEdit.getText().toString().trim(),nameEdit.getText().toString().trim());
                    handler.sendEmptyMessage(0x2);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        SMSSDK.initSDK(this, "196b43f99180c", "fa9af3fbedb8c06666b8bd2ee99847f2");
        SMSSDK.registerEventHandler(eh);

    }

    @OnClick({R.id.regist_get_code_btn, R.id.regist_queren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regist_get_code_btn:
                getCode();
                break;
            case R.id.regist_queren:
                queren();
                break;
        }
    }

    private void queren() {
        if (TextUtils.isEmpty(registPp.getText().toString().trim()) || registPp.getText().toString().length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(nameEdit.getText().toString().trim())) {
            Toast.makeText(this,"昵称不合法",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(yanzhengEdit.getText().toString().trim())||yanzhengEdit.getText().length()<4) {
            Toast.makeText(this,"请输入正确验证码",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(passEdit.getText().toString().trim()) ||passEdit.getText().length()<6){
            Toast.makeText(this,"密码不能小于6位",Toast.LENGTH_SHORT).show();
        }else{
            SMSSDK.submitVerificationCode("86",registPp.getText().toString().trim(),yanzhengEdit.getText().toString().trim());
        }
    }

    private void getCode() {
        isAlive = true;
        if (TextUtils.isEmpty(registPp.getText().toString().trim()) || registPp.getText().toString().length() != 11) {
            //为空或者不是11位
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else {
            //启动验证码获取
            SMSSDK.getVerificationCode("86", registPp.getText().toString().trim());
            //启动倒计时
            codeBtn.setBackgroundColor(getResources().getColor(R.color.find_hui));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (num >= -1 && isAlive) {
                        Message message = Message.obtain();
                        message.what = 0x1;
                        message.arg1 = num;
                        handler.sendMessage(message);
                        num--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
