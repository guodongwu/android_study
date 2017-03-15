package com.wii.study.lession_6;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wii.study.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PopupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNormal1;
    private Button btnNormal2;
    private  Button btnList1;
    private  Button btnList2;
    private  Button btnList3;
    private  Button btnWait1;
    private Button btnWait2;
    private  Button btnEdit;
    private  Button btnDIY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        initView();
    }
    private  void initView(){
        btnNormal1=(Button)findViewById(R.id.btnNormal1);
        btnNormal2=(Button)findViewById(R.id.btnNormal2);

        btnList1=(Button)findViewById(R.id.btnList1);
        btnList2=(Button)findViewById(R.id.btnList2);
        btnList3=(Button)findViewById(R.id.btnList3);

        btnWait1=(Button)findViewById(R.id.btnWait1);
        btnWait2=(Button)findViewById(R.id.btnWait2);

        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDIY=(Button)findViewById(R.id.btndiy);


        btnNormal1.setOnClickListener(this);
        btnNormal2.setOnClickListener(this);
        btnList1.setOnClickListener(this);
        btnList2.setOnClickListener(this);
        btnList3.setOnClickListener(this);
        btnWait1.setOnClickListener(this);
        btnWait2.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDIY.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNormal1:
                showNormalDialog();
                break;
            case R.id.btnNormal2:
                showMultiBtnDialog();
                break;
            case R.id.btnList1:
                showListDialog();
                break;
            case R.id.btnList2:
                showSingleChoiceDialog();
                break;
            case R.id.btnList3:
                showMultiChoiceDialog();
                break;
            case R.id.btnWait1:
                showWaitingDialog();
                break;
            case R.id.btnWait2:
                showProgressDialog();
                break;
            case R.id.btnEdit:
                showInputDialog();
                break;
            case R.id.btndiy:
                showCustomizeDialog();
                break;
        }
    }
    //单选框
    private  void showNormalDialog(){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("我是一个普通Dialog");
        normalDialog.setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Toast.makeText(PopupActivity.this,"您点击了确定按钮",Toast.LENGTH_SHORT).show();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Toast.makeText(PopupActivity.this,"您点击了关闭按钮",Toast.LENGTH_SHORT).show();
                    }
                });
        // 显示
        normalDialog.show();
    }
    //多选框
    private void showMultiBtnDialog(){
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("我是一个普通Dialog").setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNeutralButton("忽略",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                    }
                });
        normalDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ...To-do
            }
        });
        // 创建实例并显示
        normalDialog.show();
    }

    //列表
    private void showListDialog() {
        final String [] items={"北京","上海","广东","深圳"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(this);
        listDialog.setTitle("我是一个列表Dialog");
        listDialog.setItems( items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始
                // ...To-do
                Toast.makeText(PopupActivity.this,
                        "你点击了" + items[which],
                        Toast.LENGTH_SHORT).show();
            }
        });
        listDialog.show();
    }

    int yourChoice;
    private void showSingleChoiceDialog(){
        final String[] items = { "我是1","我是2","我是3","我是4" };
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(this);
        singleChoiceDialog.setTitle("我是一个单选Dialog");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            Toast.makeText(PopupActivity.this,
                                    "你选择了" + items[yourChoice],
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    ArrayList<Integer> yourChoices = new ArrayList<>();
    private void showMultiChoiceDialog() {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        // 设置默认选中的选项，全为false默认均未选中
        final boolean initChoiceSets[]={false,false,false,false};
        yourChoices.clear();
        AlertDialog.Builder multiChoiceDialog =
                new AlertDialog.Builder(this);
        multiChoiceDialog.setTitle("我是一个多选Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            yourChoices.add(which);
                        } else {
                            yourChoices.remove(which);
                        }
                    }
                });
        multiChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = yourChoices.size();
                        String str = "";
                        for (int i = 0; i < size; i++) {
                            str += items[yourChoices.get(i)] + " ";
                        }
                        Toast.makeText(PopupActivity.this,
                                "你选中了" + str,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        multiChoiceDialog.show();
    }

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        ProgressDialog waitingDialog=
                new ProgressDialog(this);
        waitingDialog.setTitle("我是一个等待Dialog");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(true);
        waitingDialog.show();
    }

    private void showProgressDialog() {
    /* @setProgress 设置初始进度
     * @setProgressStyle 设置样式（水平进度条）
     * @setMax 设置进度最大值
     */
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog =
                new ProgressDialog(this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
    /* 模拟进度增加的过程
     * 新开一个线程，每个100ms，进度增加1
     */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }

    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(this);
        inputDialog.setTitle("我是一个输入Dialog").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PopupActivity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void showCustomizeDialog() {

    /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        final View dialogView = LayoutInflater.from(PopupActivity.this)
                .inflate(R.layout.login_dialog,null);
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(PopupActivity.this)
                .setTitle("登录提示").setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText username =
                                (EditText) dialogView.findViewById(R.id.username);
                        EditText password =
                                (EditText) dialogView.findViewById(R.id.password);
                        Toast.makeText(PopupActivity.this,
                                username.getText().toString()+"|"+password.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        customizeDialog.show();

    }

    // 显示对话框
    public void showWaiterAuthorizationDialog() {

        // LayoutInflater是用来找layout文件夹下的xml布局文件，并且实例化
        LayoutInflater factory = LayoutInflater.from(PopupActivity.this);
        // 把activity_login中的控件定义在View中
        final View textEntryView = factory.inflate(R.layout.login_dialog,
                null);

        // 将LoginActivity中的控件显示在对话框中
        new AlertDialog.Builder(PopupActivity.this)
                // 对话框的标题
                .setTitle("登陆")
                // 设定显示的View
                .setView(textEntryView)
                // 对话框中的“登陆”按钮的点击事件
                .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        // 获取用户输入的“用户名”，“密码”
                        // 注意：textEntryView.findViewById很重要，因为上面factory.inflate(R.layout.activity_login,
                        // null)将页面布局赋值给了textEntryView了
                        final EditText etUserName = (EditText) textEntryView
                                .findViewById(R.id.username);
                        final EditText etPassword = (EditText) textEntryView
                                .findViewById(R.id.password);

                        // 将页面输入框中获得的“用户名”，“密码”转为字符串
                        String userName = etUserName.getText().toString()
                                .trim();
                        String password = etPassword.getText().toString()
                                .trim();

                        // 现在为止已经获得了字符型的用户名和密码了，接下来就是根据自己的需求来编写代码了
                        // 这里做一个简单的测试，假定输入的用户名和密码都是1则进入其他操作页面（OperationActivity）
                        if (userName.equals("1") && password.equals("1")) {
                            Toast.makeText(PopupActivity.this,"登录",Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(PopupActivity.this, "密码或用户名错误",
                                    Toast.LENGTH_SHORT).show();

                            try {
                                // 注意此处是通过反射，修改源代码类中的字段mShowing为true，系统会认为对话框打开
                                // 从而调用dismiss()
                                Field field = dialog.getClass().getSuperclass()
                                        .getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, false);
                                dialog.dismiss();

                            } catch (Exception e) {

                            }
                        }
                    }
                })
                // 对话框的“退出”单击事件
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        PopupActivity.this.finish();
                    }
                })
                // 设置dialog是否为模态，false表示模态，true表示非模态
                .setCancelable(false)
                // 对话框的创建、显示
                .create().show();
    }
}
