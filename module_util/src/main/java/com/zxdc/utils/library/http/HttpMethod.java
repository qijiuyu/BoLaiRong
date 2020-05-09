package com.zxdc.utils.library.http;

import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.CheckCode;
import com.zxdc.utils.library.bean.ConstractDetails;
import com.zxdc.utils.library.bean.Contract;
import com.zxdc.utils.library.bean.ContractCode;
import com.zxdc.utils.library.bean.Department;
import com.zxdc.utils.library.bean.Device;
import com.zxdc.utils.library.bean.DeviceType;
import com.zxdc.utils.library.bean.Inventory;
import com.zxdc.utils.library.bean.Log;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.NetWorkCallBack;
import com.zxdc.utils.library.bean.Office;
import com.zxdc.utils.library.bean.OutBound;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.bean.PlanDetails;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.bean.SelectCustomer;
import com.zxdc.utils.library.bean.Upload;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.AddContractP;
import com.zxdc.utils.library.bean.parameter.AddDeviceP;
import com.zxdc.utils.library.bean.parameter.AddLogP;
import com.zxdc.utils.library.bean.parameter.AddProductPlanP;
import com.zxdc.utils.library.bean.parameter.OutBoundP;
import com.zxdc.utils.library.http.base.BaseRequst;
import com.zxdc.utils.library.http.base.Http;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpMethod extends BaseRequst {

    public static int limit=20;


    /**
     * 登录
     */
    public static void login(String username, String pwd, final NetWorkCallBack netWorkCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("username",username);
        map.put("pwd",pwd);
        Http.getRetrofit().create(HttpApi.class).login(map).enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                netWorkCallBack.onFail(t);
            }
        });
    }


    /**
     * 获取部门列表
     */
    public static void getDepartment(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDepartment().enqueue(new Callback<Department>() {
            public void onResponse(Call<Department> call, Response<Department> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Department> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取设备类型
     */
    public static void getDeviceType(final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeviceType(8).enqueue(new Callback<DeviceType>() {
            public void onResponse(Call<DeviceType> call, Response<DeviceType> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DeviceType> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加设备
     */
    public static void addDevice(AddDeviceP addDeviceP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addDevice(addDeviceP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 上传文件
     */
    public static void uploadFile(String fileName,File file, final NetWorkCallBack netWorkCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("source","blr");
        map.put("key",fileName);
        Http.upLoadFile(HttpConstant.UPLOAD_FILE,"file", file, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                try {
                    String str = response.body().string();
                    netWorkCallBack.onSuccess(SPUtil.gson.fromJson(str, Upload.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }


    /**
     * 上传文件
     */
    public static void uploadByFileAndTypeAndFid(String fileName,File file,String type,String fid, final NetWorkCallBack netWorkCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("source","blr");
        map.put("key",fileName);
        map.put("type",type);
        map.put("fid",fid);
        Http.upLoadFile(HttpConstant.UPLOAD_FILE_BY_OBJECT,"file", file, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                try {
                    String str = response.body().string();
                    netWorkCallBack.onSuccess(SPUtil.gson.fromJson(str, Upload.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }



    /**
     * 获取设备列表
     */
    public static void getDeviceList(String initialism,String id,String page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeviceList(initialism,id,page,limit).enqueue(new Callback<Device>() {
            public void onResponse(Call<Device> call, Response<Device> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Device> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取客户列表
     */
    public static void getCustomerList(String prop1, int userId,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCustomerList(prop1,userId).enqueue(new Callback<SelectCustomer>() {
            public void onResponse(Call<SelectCustomer> call, Response<SelectCustomer> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SelectCustomer> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取指派内勤
     */
    public static void getOffice(int deptId, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOffice(deptId).enqueue(new Callback<Office>() {
            public void onResponse(Call<Office> call, Response<Office> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Office> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 新增合同
     */
    public static void addContract(AddContractP addContractP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addContract(addContractP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取合同列表
     */
    public static void getContractList(String prop2,String customerId,String page, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getContractList(prop2,customerId,page,limit).enqueue(new Callback<Contract>() {
            public void onResponse(Call<Contract> call, Response<Contract> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Contract> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取合同详情
     */
    public static void getConstractDetails(String id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getConstractDetails(id).enqueue(new Callback<ConstractDetails>() {
            public void onResponse(Call<ConstractDetails> call, Response<ConstractDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ConstractDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 修改合同
     */
    public static void editContract(AddContractP addContractP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).editContract(addContractP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 校验合同编码唯一性
     */
    public static void checkCode(String prop2,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).checkCode(prop2).enqueue(new Callback<CheckCode>() {
            public void onResponse(Call<CheckCode> call, Response<CheckCode> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CheckCode> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取库存明细列表
     */
    public static void getInventoryList(String goodsId,String warnFlag,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getInventoryList(goodsId,warnFlag,page,limit).enqueue(new Callback<Inventory>() {
            public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Inventory> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 删除文件
     */
    public static void deleteFile(String id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).deleteFile(id).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 物料列表-名称模糊查询
     */
    public static void getMeterialByName(String prop3,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getMeterialByName(prop3,page,limit).enqueue(new Callback<Material>() {
            public void onResponse(Call<Material> call, Response<Material> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Material> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }



    /**
     * 根据合同编码模糊匹配合同列表
     */
    public static void getContractCode(String prop2,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getContractCode(prop2,page,limit).enqueue(new Callback<ContractCode>() {
            public void onResponse(Call<ContractCode> call, Response<ContractCode> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ContractCode> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 增加出库单
     */
    public static void addOutBound(OutBoundP outBoundP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addOutBound(outBoundP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取出库单列表
     */
    public static void getOutBoundList(String customerId,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOutBoundList(customerId,page,limit).enqueue(new Callback<OutBound>() {
            public void onResponse(Call<OutBound> call, Response<OutBound> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutBound> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 查询出库单详情
     */
    public static void getOutBoundDetails(int id,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOutBoundDetails(id).enqueue(new Callback<OutBoundDetails>() {
            public void onResponse(Call<OutBoundDetails> call, Response<OutBoundDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<OutBoundDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加生产计划
     */
    public static void addPlan(AddProductPlanP addProductPlanP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addPlan(addProductPlanP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取生产计划列表
     */
    public static void getPlanList(String planCode,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getPlanList(planCode,page,limit).enqueue(new Callback<ProductPlan>() {
            public void onResponse(Call<ProductPlan> call, Response<ProductPlan> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProductPlan> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取生产计划详情
     */
    public static void getPlanDetails(int planId, int deptId,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getPlanDetails(planId,deptId).enqueue(new Callback<PlanDetails>() {
            public void onResponse(Call<PlanDetails> call, Response<PlanDetails> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<PlanDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 添加日志
     */
    public static void addLog(AddLogP addLogP, final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).addLog(addLogP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }


    /**
     * 获取日志列表
     */
    public static void getLogList(String customerId,int page,final NetWorkCallBack netWorkCallBack) {
        Http.getRetrofit().create(HttpApi.class).getLogList(customerId,page,limit).enqueue(new Callback<Log>() {
            public void onResponse(Call<Log> call, Response<Log> response) {
                DialogUtil.closeProgress();
                netWorkCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<Log> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(t.getMessage());
            }
        });
    }

}
