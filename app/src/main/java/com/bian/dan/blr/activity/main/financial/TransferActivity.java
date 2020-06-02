package com.bian.dan.blr.activity.main.financial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.application.MyApplication;
import com.bian.dan.blr.persenter.financial.TransferPersenter;
import com.bian.dan.blr.utils.SelectPhoto;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.FinancialDetails;
import com.zxdc.utils.library.bean.parameter.TransferP;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 转账页面
 */
public class TransferActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.img_transfer)
    ImageView imgTransfer;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private TransferPersenter transferPersenter;

    /**
     * 转账凭证
     */
    private String transferImg;
    //详情对象
    private FinancialDetails.DetailsBean detailsBean;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        tvHead.setText("确认转账");
        transferPersenter=new TransferPersenter(this);
        detailsBean= (FinancialDetails.DetailsBean) getIntent().getSerializableExtra("detailsBean");
    }

    @OnClick({R.id.lin_back, R.id.tv_time,R.id.img_transfer, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            //选择时间
            case R.id.tv_time:
                transferPersenter.selectTime(tvTime);
                 break;
            //选择照片
            case R.id.img_transfer:
                transferPersenter.SelectPhoto();
                break;
           //提交
            case R.id.tv_submit:
                String money=etMoney.getText().toString().trim();
                String time=tvTime.getText().toString().trim();
                if(TextUtils.isEmpty(money)){
                    ToastUtil.showLong("请输入转账金额");
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    ToastUtil.showLong("请选择转账时间");
                    return;
                }
                TransferP transferP=new TransferP();
                transferP.setId(detailsBean.getId());
                transferP.setProp1(MyApplication.getUser().getUser().getUserId());
                transferP.setProp2(money);
                transferP.setProp3(transferImg);
                transferP.setProp5(time);
                LogUtils.e("+++++++++++"+new Gson().toJson(transferP));
                transferPersenter.addTransferP(transferP);
                break;
            default:
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //返回拍照图片
            case SelectPhoto.CODE_CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {
                    File tempFile = new File(SelectPhoto.pai);
                    if (tempFile.isFile()) {
                        LocalMedia localMedia=new LocalMedia();
                        localMedia.setCompressPath(SelectPhoto.pai);
                        transferPersenter.uploadFile(localMedia);
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                if(list.size()>0){
                    transferPersenter.uploadFile(list.get(0));
                }
                break;
            default:
                break;
        }
    }


    /**
     * 图片上传成功
     */
    public void uploadSuccess(final String imgPath){
        this.transferImg=imgPath;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(activity).load(imgPath).into(imgTransfer);
            }
        });
    }
}
