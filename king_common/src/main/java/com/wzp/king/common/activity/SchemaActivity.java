package com.wzp.king.common.activity;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Schema中转页
 *
 * <p>
 * String url = "yit://cashier.mobile/app/main";
 * Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
 * startActivity(intent);
 * </p>
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/27
 */

public class SchemaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri uri = getIntent().getData();
        if (uri != null) {
            ARouter.getInstance().build(uri).navigation(this);
        }
        finish();
    }
}
