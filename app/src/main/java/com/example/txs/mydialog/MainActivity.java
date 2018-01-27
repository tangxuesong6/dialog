package com.example.txs.mydialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**Button的 onclick方法 相当于findviewbyid.setOnClickListener 即Button的点击监听*/
    public void show(View view) {
        View view1 = this.getLayoutInflater().inflate(R.layout.background_dialog,null);
        final Dialog dialog = new Dialog(this,R.style.MyCommonDialog);
        dialog.setContentView(view1);
        dialog.show();
        //放在show()之后，不然有些属性是没有效果的，比如height和width
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.dialog_animation);
        WindowManager m = getWindowManager();
        // 获取屏幕宽、高
        Display d = m.getDefaultDisplay();
        // 获取对话框当前的参数值
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        //设置高度和宽度  高度设置为屏幕的0.3
        p.height = (int) (d.getHeight() * 0.3);
        // 宽度设置为屏幕的0.8
        p.width = (int) (d.getWidth() * 0.8);
        //设置位置
        p.gravity = Gravity.CENTER;
        //下面注掉的代码可以用来设置Dialog透明度
//        p.alpha = 0.5f;
        dialogWindow.setAttributes(p);
        // 设置显示位置
        dialog.onWindowAttributesChanged(p);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        //自定义布局里面的两个按钮
        TextView mTvSure;
        TextView mTvCancel;
        mTvSure = (TextView) dialog.findViewById(R.id.tv_sure);
        mTvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        //按钮点击监听
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
