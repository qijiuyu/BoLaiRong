package com.bian.dan.blr.activity.main.sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.bian.dan.blr.R;
import com.bian.dan.blr.adapter.sales.GridViewImgAdapter;
import com.bian.dan.blr.persenter.sales.AddContractPersenter;
import com.bian.dan.blr.utils.SelectPhoto;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zxdc.utils.library.base.BaseActivity;
import com.zxdc.utils.library.bean.ConstractDetails;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.SelectCustomer;
import com.zxdc.utils.library.bean.parameter.AddContractP;
import com.zxdc.utils.library.bean.parameter.FileList;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.MyGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增合同
 */
public class AddContractActivity extends BaseActivity {

    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sign_time)
    TextView tvSignTime;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_invoice)
    TextView tvInvoice;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_office)
    TextView tvOffice;
    @BindView(R.id.gridView)
    MyGridView gridView;
    private AddContractPersenter addContractPersenter;
    //选择的照片
    private List<FileBean> imgList=new ArrayList<>();
    private GridViewImgAdapter gridViewImgAdapter;
    //合同详情对象
    private ConstractDetails constractDetails;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        ButterKnife.bind(this);
        initView();
        //编辑前显示对应的数据
        showData();
    }


    /**
     * 初始化
     */
    private void initView(){
        addContractPersenter=new AddContractPersenter(this);
        tvHead.setText("新增合同");
        constractDetails= (ConstractDetails) getIntent().getSerializableExtra("constractDetails");
        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==imgList.size()){
                    addContractPersenter.SelectPhoto();
                }
            }
        });
    }



    /**
     * 按钮点击事件
     * @param view
     */
    @OnClick({R.id.lin_back,R.id.tv_name,R.id.tv_sign_time,R.id.tv_pay_type,R.id.tv_invoice,R.id.tv_company,R.id.tv_office,R.id.tv_submit})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            //选择客户名称
            case R.id.tv_name:
                  intent.setClass(this,SelectCustomerActivity.class);
                  startActivityForResult(intent,100);
                  break;
            //签订时间
            case R.id.tv_sign_time:
                addContractPersenter.selectTime(tvSignTime);
                break;
            //支付方式
            case R.id.tv_pay_type:
                 addContractPersenter.selectText(tvPayType,1);
                  break;
            //是否开票
            case R.id.tv_invoice:
                addContractPersenter.selectText(tvInvoice,2);
                 break;
            //售卖公司
            case R.id.tv_company:
                addContractPersenter.selectText(tvCompany,3);
                break;
            //指派内勤
            case R.id.tv_office:
                addContractPersenter.selectOffice(tvOffice);
                break;
            //提交
            case R.id.tv_submit:
                  String code=etCode.getText().toString().trim();
                  String name=tvName.getText().toString().trim();
                  String signTime=tvSignTime.getText().toString().trim();
                  String money=etMoney.getText().toString().trim();
                  String payType=tvPayType.getText().toString().trim();
                  String invoice=tvInvoice.getText().toString().trim();
                  String company=tvCompany.getText().toString().trim();
                  String office=tvOffice.getText().toString().trim();
                  if(TextUtils.isEmpty(code)){
                      ToastUtil.showLong("请输入合同编号");
                      return;
                  }
                 if(TextUtils.isEmpty(name)){
                     ToastUtil.showLong("请选择客户名称");
                     return;
                 }
                 if(TextUtils.isEmpty(signTime)){
                     ToastUtil.showLong("请选择签订时间");
                     return;
                 }
                 if(TextUtils.isEmpty(money)){
                     ToastUtil.showLong("请输入订单金额");
                     return;
                 }
                if(TextUtils.isEmpty(payType)){
                    ToastUtil.showLong("请选择支付方式");
                    return;
                }
                if(TextUtils.isEmpty(invoice)){
                    ToastUtil.showLong("请选择是否开票");
                    return;
                }
                if(TextUtils.isEmpty(company)){
                    ToastUtil.showLong("请选择售卖公司");
                    return;
                }
                if(imgList.size()==0){
                    ToastUtil.showLong("请选择设备图片");
                    return;
                }
                AddContractP addContractP=new AddContractP();
                addContractP.setProp2(code);
                addContractP.setCustomerId((int)tvName.getTag());
                addContractP.setSignedTime(signTime+" 00:00:00");
                addContractP.setAmount(Double.parseDouble(money));
                if(!TextUtils.isEmpty(office)){
                    addContractP.setAssignerId((int)tvOffice.getTag());
                }
                if(payType.equals("全款")){
                    addContractP.setPayType(1);
                }else{
                    addContractP.setPayType(2);
                }
                if(invoice.equals("是")){
                    addContractP.setInvoice(1);
                }else{
                    addContractP.setInvoice(0);
                }
                if(company.equals("博徕荣")){
                    addContractP.setSellers(1);
                }else{
                    addContractP.setSellers(2);
                }
                //解析要提交的图片链接
                if(constractDetails==null) {
                    List<FileList> list=new ArrayList<>();
                    for (int i = 0; i < imgList.size(); i++) {
                         FileList fileList=new FileList();
                         fileList.setUrl(imgList.get(i).getUrl());
                         list.add(fileList);
                    }
                    addContractP.setFileList(list);
                }
                if(constractDetails==null){
                    //校验合同编码唯一性
                    addContractPersenter.checkCode(addContractP);
                }else{
                    addContractP.setId(constractDetails.getContract().getId());
                    addContractPersenter.editContract(addContractP);
                }
                  break;
            default:
                break;
        }
    }


    /**
     * 编辑前显示对应的数据
     */
    private void showData(){
        if(constractDetails==null){
            return;
        }
        ConstractDetails.DetailsBean detailsBean=constractDetails.getContract();
        if(detailsBean==null){
            return;
        }
        //禁止修改合同编码
        etCode.setEnabled(false);
        etCode.setTextColor(getResources().getColor(R.color.color_999999));
        etCode.setText(detailsBean.getProp2());
        tvName.setText(detailsBean.getCustomerName());
        tvName.setTag(detailsBean.getCustomerId());
        tvSignTime.setText(detailsBean.getSignedTime());
        etMoney.setText(String.valueOf(detailsBean.getAmount()));
        if(detailsBean.getPayType()==1){
            tvPayType.setText("全款");
        }else{
            tvPayType.setText("分期");
        }
        if(detailsBean.getInvoice()==0){
            tvInvoice.setText("否");
        }else{
            tvInvoice.setText("是");
        }
        if(detailsBean.getSellers()==1){
            tvCompany.setText("博徕荣");
        }else{
            tvCompany.setText("立钻");
        }
        tvOffice.setText(detailsBean.getAssignerName());
        tvOffice.setTag(detailsBean.getAssignerId());
        /**
         * 显示图片
         */
        imgList.addAll(constractDetails.getFileList());
        gridViewImgAdapter=new GridViewImgAdapter(this,imgList);
        gridView.setAdapter(gridViewImgAdapter);
    }


    /**
     * 编辑的时候删除图片
     */
    public void deleteImg(FileBean fileBean){
        addContractPersenter.deleteFile(String.valueOf(fileBean.getId()));
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
                        List<LocalMedia> list=new ArrayList<>();
                        LocalMedia localMedia=new LocalMedia();
                        localMedia.setCompressPath(SelectPhoto.pai);
                        list.add(localMedia);
                        if(constractDetails==null){
                            //增加合同-上传图片
                            addContractPersenter.uploadFile(list);
                        }else{
                            //编辑合同-上传图片
                            addContractPersenter.uploadByFileAndTypeAndFid(constractDetails.getContract().getId(),list);
                        }
                    }
                }
                break;
            //返回相册选择图片
            case PictureConfig.CHOOSE_REQUEST:
                 List<LocalMedia> list= PictureSelector.obtainMultipleResult(data);
                 if(constractDetails==null){
                    //增加合同-上传图片
                    addContractPersenter.uploadFile(list);
                }else{
                    //编辑合同-上传图片
                    addContractPersenter.uploadByFileAndTypeAndFid(constractDetails.getContract().getId(),list);
                }
                break;
           //回执客户信息
            case 100:
                 if(data==null){
                     return;
                 }
                 SelectCustomer.ListBean listBean = (SelectCustomer.ListBean) data.getSerializableExtra("listBean");
                 tvName.setText(listBean.getCustomerName());
                 tvName.setTag(listBean.getId());
                 break;
            default:
                break;
        }
    }

    /**
     * 图片上传成功
     * @param path
     */
    public void uploadSuccess(final String path, final int pid){
        runOnUiThread(new Runnable() {
            public void run() {
                FileBean fileBean;
                fileBean = new FileBean(pid,path);
                imgList.add(fileBean);
                gridViewImgAdapter=new GridViewImgAdapter(activity,imgList);
                gridView.setAdapter(gridViewImgAdapter);
            }
        });
    }
}
