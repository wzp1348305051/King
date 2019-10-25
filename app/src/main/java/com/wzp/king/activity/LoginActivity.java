package com.wzp.king.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wzp.king.R;
import com.wzp.king.bean.AppPath;
import com.wzp.king.cashier.bean.CashierPath;
import com.wzp.king.common.activity.BaseActivity;
import com.wzp.king.common.widget.SingleClickListener;

import androidx.annotation.NonNull;
import butterknife.BindView;

@Route(path = AppPath.PATH_LOGIN)
public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_login_submit)
    TextView mTvSubmit;

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initMemberData() {

    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {
        mHeaderView.setVisibility(View.GONE);

        mTvSubmit.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                ARouter.getInstance().build(CashierPath.PATH_MAIN).navigation(mActivity);
            }
        });
    }
}
