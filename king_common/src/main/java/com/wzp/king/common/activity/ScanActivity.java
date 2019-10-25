package com.wzp.king.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.joanzapata.iconify.widget.IconTextView;
import com.wzp.king.common.R;
import com.wzp.king.common.R2;
import com.wzp.king.common.router.RouterPath;
import com.wzp.king.common.util.DisplayUtil;
import com.wzp.king.common.util.EmptyUtil;
import com.wzp.king.common.widget.SingleClickListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.core.ScanBoxView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * 扫描页
 *
 * @author wengzhipeng
 * @version v1.0, 2019/6/25
 */

@Route(path = RouterPath.PATH_SCAN, name = "扫描页")
public class ScanActivity extends BaseActivity {
    public static final String ARG_HINT = "hint";
    public static final String ARG_RESULT = "result";
    @BindView(R2.id.tv_scan_back)
    IconTextView mTvBack;
    @BindView(R2.id.layout_scan_scanner)
    ZXingView mLayoutScan;
    @Autowired(name = ARG_HINT)
    String mHint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ARouter.getInstance().inject(this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_scan;
    }

    @Override
    public void initMemberData() {
        if (getIntent() != null) {
            mHint = getIntent().getStringExtra(ARG_HINT);
        }
    }

    @Override
    public void bindContentView(Bundle savedInstanceState) {
        mHeaderView.setVisibility(View.GONE);
        mTvBack.setOnClickListener(new SingleClickListener() {
            @Override
            public void onSingleClick(@NonNull View v) {
                finish();
            }
        });

        ScanBoxView scanBoxView = mLayoutScan.getScanBoxView();

        scanBoxView.setTipTextSize(DisplayUtil.dip2Px(this, 16));
        scanBoxView.setTipTextColor(Color.WHITE);
        if (EmptyUtil.isEmptyText(mHint)) {
            scanBoxView.setTipText("请扫描二维码");
        } else {
            scanBoxView.setTipText(mHint);
        }
        scanBoxView.setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码

        mLayoutScan.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
        mLayoutScan.setType(BarcodeType.HIGH_FREQUENCY, null); // 只识别高频率格式，包括 QR_CODE、UPC_A、EAN_13、CODE_128
        mLayoutScan.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
                Intent intent = new Intent();
                intent.putExtra(ARG_RESULT, result);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

            @Override
            public void onCameraAmbientBrightnessChanged(boolean isDark) {

            }

            @Override
            public void onScanQRCodeOpenCameraError() {
                showToast("初始化相机失败");
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mLayoutScan != null) {
            mLayoutScan.startCamera();
            mLayoutScan.startSpotAndShowRect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mLayoutScan != null) {
            mLayoutScan.stopCamera();
            mLayoutScan.stopSpotAndHiddenRect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLayoutScan != null) {
            mLayoutScan.onDestroy();
        }
    }
}
