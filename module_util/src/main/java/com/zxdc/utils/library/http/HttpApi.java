package com.zxdc.utils.library.http;


import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.CheckCode;
import com.zxdc.utils.library.bean.ConstractDetails;
import com.zxdc.utils.library.bean.Contract;
import com.zxdc.utils.library.bean.ContractCode;
import com.zxdc.utils.library.bean.CustomerDetails;
import com.zxdc.utils.library.bean.CustomerList;
import com.zxdc.utils.library.bean.Department;
import com.zxdc.utils.library.bean.Device;
import com.zxdc.utils.library.bean.DeviceDetails;
import com.zxdc.utils.library.bean.DeviceType;
import com.zxdc.utils.library.bean.Dict;
import com.zxdc.utils.library.bean.Financial;
import com.zxdc.utils.library.bean.FinancialDetails;
import com.zxdc.utils.library.bean.Inventory;
import com.zxdc.utils.library.bean.InventoryDetails;
import com.zxdc.utils.library.bean.Log;
import com.zxdc.utils.library.bean.LogDetails;
import com.zxdc.utils.library.bean.Material;
import com.zxdc.utils.library.bean.Office;
import com.zxdc.utils.library.bean.OutBound;
import com.zxdc.utils.library.bean.OutBoundDetails;
import com.zxdc.utils.library.bean.PlanDetails;
import com.zxdc.utils.library.bean.ProductPlan;
import com.zxdc.utils.library.bean.SelectCustomer;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.UserList;
import com.zxdc.utils.library.bean.parameter.AddContractP;
import com.zxdc.utils.library.bean.parameter.AddCustomerP;
import com.zxdc.utils.library.bean.parameter.AddDeviceP;
import com.zxdc.utils.library.bean.parameter.AddFinancialP;
import com.zxdc.utils.library.bean.parameter.AddLogP;
import com.zxdc.utils.library.bean.parameter.AddProductPlanP;
import com.zxdc.utils.library.bean.parameter.LoginP;
import com.zxdc.utils.library.bean.parameter.OutBoundP;
import com.zxdc.utils.library.bean.parameter.UpdateCustomerStateP;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface HttpApi {

    @POST(HttpConstant.LOGIN)
    Call<UserInfo> login(@Body LoginP loginP);

    @GET(HttpConstant.GET_DEPARTMENT)
    Call<Department> getDepartment();

    @PUT(HttpConstant.ADD_DEVICE)
    Call<BaseBean> addDevice(@Body AddDeviceP addDeviceP);

    @GET(HttpConstant.DEVICE_TYPE)
    Call<DeviceType> getDeviceType(@Query("pid") int pid);

    @GET(HttpConstant.GET_DEVICE_LIST)
    Call<Device> getDeviceList(@Query("initialism") String initialism, @Query("id") String id, @Query("page") String page,@Query("limit") int limit);

    @GET(HttpConstant.GET_DEVICE_DETAILS)
    Call<DeviceDetails> getDeviceDetails(@Query("id") int id);

    @GET(HttpConstant.GET_CUSTOMER_LIST)
    Call<SelectCustomer> getCustomerList(@Query("prop1") String prop1, @Query("userId") String userId);

    @GET(HttpConstant.GET_OFFICE)
    Call<Office> getOffice(@Query("deptId") int deptId);

    @PUT(HttpConstant.ADD_CONTRACT)
    Call<BaseBean> addContract(@Body AddContractP addContractP);

    @GET(HttpConstant.GET_CONTRACT_LIST)
    Call<Contract> getContractList(@Query("prop2") String prop2,@Query("customerId") String customerId, @Query("page") String page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CONTRACT_DETAILS)
    Call<ConstractDetails> getConstractDetails(@Query("id") String id);

    @POST(HttpConstant.EDIT_CONTRACT)
    Call<BaseBean> editContract(@Body AddContractP addContractP);

    @GET(HttpConstant.CHECK_CODE)
    Call<CheckCode> checkCode(@Query("prop2") String prop2);

    @GET(HttpConstant.GET_INVENTROY_LIST)
    Call<Inventory> getInventoryList(@Query("goodsId") String goodsId,@Query("warnFlag") String warnFlag, @Query("page") int page, @Query("limit") int limit);

    @DELETE(HttpConstant.DELETE_FILE)
    Call<BaseBean> deleteFile(@Query("id") String id);

    @GET(HttpConstant.GET_MATERIAL_BY_NAME)
    Call<Material> getMeterialByName(@Query("prop3") String prop3,@Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CONTRACT_CODE)
    Call<ContractCode> getContractCode(@Query("prop2") String prop2,@Query("page") int page, @Query("limit") int limit);

    @PUT(HttpConstant.ADD_OUTBOUND)
    Call<BaseBean> addOutBound(@Body OutBoundP outBoundP);

    @GET(HttpConstant.GET_OUTBOUND_LIST)
    Call<OutBound> getOutBoundList(@Query("customerId") String customerId, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_OUTBOUND_DETAILS)
    Call<OutBoundDetails> getOutBoundDetails(@Query("id") int id);

    @PUT(HttpConstant.ADD_PLAN)
    Call<BaseBean> addPlan(@Body AddProductPlanP addProductPlanP);

    @GET(HttpConstant.GET_PLAN_LIST)
    Call<ProductPlan> getPlanList(@Query("planCode") String planCode, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_PLAN_DETAILS)
    Call<PlanDetails> getPlanDetails(@Query("planId") int planId, @Query("deptId") int deptId);

    @PUT(HttpConstant.ADD_LOG)
    Call<BaseBean> addLog(@Body AddLogP addLogP);

    @GET(HttpConstant.GET_LOG_LIST)
    Call<Log> getLogList(@Query("customerId") String customerId, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_LOG_DETAILS)
    Call<LogDetails> getLogDetails(@Query("id") int id);

    @GET(HttpConstant.GET_DICT)
    Call<Dict> getDict(@Query("pid") int pid);

    @PUT(HttpConstant.ADD_CUSTOMER)
    Call<BaseBean> addCustomer(@Body AddCustomerP addCustomerP);

    @GET(HttpConstant.GET_CUSTOMER)
    Call<CustomerList> getCustomer(@Query("privateState") int privateState,@Query("privateId")String privateId,@Query("contacts") String contacts, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_CUSTOMER_DETAILS)
    Call<CustomerDetails> getCustomerDetails(@Query("id") int id);

    @POST(HttpConstant.UPDATE_CUSTOMER_STATE)
    Call<BaseBean> updateCustomerState(@Body UpdateCustomerStateP updateCustomerStateP);

    @POST(HttpConstant.UPDATE_CUSTOMER)
    Call<BaseBean> updateCustomer(@Body AddCustomerP addCustomerP);

    @GET(HttpConstant.CHECK_CUSTOMER_NAME)
    Call<CheckCode> checkCustomerName(@Query("customerName") String customerName);

    @GET(HttpConstant.GET_USER_LIST)
    Call<UserList> getUserList(@Query("deptId") int deptId, @Query("name") String name, @Query("page") int page, @Query("limit") int limit);

    @PUT(HttpConstant.ADD_FINANCIAL)
    Call<BaseBean> addFinancial(@Body AddFinancialP addFinancialP);

    @GET(HttpConstant.GET_FINANCIAL_LIST)
    Call<Financial> getFinancialList(@Query("state") String state, @Query("page") int page, @Query("limit") int limit);

    @GET(HttpConstant.GET_FINANCIAL_DETAILS)
    Call<FinancialDetails> getFinancialDetails(@Query("id") int id);

    @GET(HttpConstant.GET_INVENTORY_DETAILS)
    Call<InventoryDetails> getInventoryDetails(@Query("goodsId") String goodsId,@Query("type") String type,@Query("stockType") String stockType,@Query("page") int page, @Query("limit") int limit);


}
