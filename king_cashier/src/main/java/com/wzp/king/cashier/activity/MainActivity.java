package com.wzp.king.cashier.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wzp.king.cashier.R;
import com.wzp.king.cashier.R2;
import com.wzp.king.cashier.bean.CashierPath;
import com.wzp.king.common.activity.BaseActivity;
import com.wzp.king.common.widget.MenuView;
import com.wzp.king.common.widget.SingleClickListener;

import androidx.annotation.NonNull;
import butterknife.BindView;

@Route(path = CashierPath.PATH_MAIN)
public class MainActivity extends BaseActivity {
    @BindView(R2.id.layout_main_content)
    MenuView mMenuView;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initMemberData() {

    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {
        mHeaderView.setMiddleText("我是主页");
        mHeaderView.getLeftView().setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                onBackPressed();
            }
        });

        mMenuView.addMenu(new MenuView.Menu("我是主标题1"));
        mMenuView.addMenu(new MenuView.Menu("我是主标题2", "我是副标题2"));
        mMenuView.addMenu(new MenuView.Menu("我是主标题3", new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                showToast("我是主标题3");
            }
        }));
        mMenuView.addMenu(new MenuView.Menu("我是主标题4", "我是副标题4", new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                showToast("我是主标题4");
            }
        }));
        mMenuView.addMenu(new MenuView.Menu("我是主标题5", "我是副标题5", new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                showToast("我是主标题5");
            }
        }, "R.drawable.menu_checked", getString(R.string.text_icon_setting)), true);
    }
}
