package com.zxdc.utils.library.http.base;

import com.zxdc.utils.library.base.BaseApplication;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.parameter.LoginP;
import com.zxdc.utils.library.http.HttpApi;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * HTTP拦截器
 * Created by lyn on 2017/4/13.
 */
public class LogInterceptor implements Interceptor {

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();
        if (request.method().equals("GET")) {
            request = addGetParameter(request);
        }
        if (request.method().equals("POST")) {
            request = addPostParameter(request);
        }
        if (request.method().equals("PUT")) {
            request = addPutParameter(request);
        }
        if (request.method().equals("DELETE")) {
            request = addDeleteParameter(request);
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        String body = response.body().string();
        //如果ACCESS_TOKEN失效，自动重新获取一次
        final int code = getCode(body);
        if(code==401){
            BaseBean baseBean=reFreshToken();
            if(baseBean!=null && baseBean.isSussess()){
                //存储token数据
                SPUtil.getInstance(BaseApplication.getContext()).addString(SPUtil.TOKEN,baseBean.getToken());
                if (request.method().equals("GET")) {
                    request = addGetParameter(request);
                }
                if (request.method().equals("POST")) {
                    request = addPostParameter(request);
                }
                if (request.method().equals("PUT")) {
                    request = addPutParameter(request);
                }
                if (request.method().equals("DELETE")) {
                    request = addDeleteParameter(request);
                }
                response = chain.proceed(request);
                body = response.body().string();
            }
        }
        LogUtils.e(String.format("response %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, body));
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), body)).build();
    }


    /**
     * 传递GET请求的全局参数
     * @param request
     * @return
     */
    public Request addGetParameter(Request request){
        HttpUrl.Builder builder = request.url().newBuilder();
        Request newRequest = request.newBuilder()
                .addHeader("Authorization",SPUtil.getInstance(BaseApplication.getContext()).getString(SPUtil.TOKEN))
                .method(request.method(), request.body())
                .url(builder.build())
                .build();
        return newRequest;
    }


    /***
     * 添加POST的公共参数
     */
    public Request addPostParameter(Request request) throws IOException {
        Request.Builder requestBuilder = request.newBuilder().addHeader("Authorization",SPUtil.getInstance(BaseApplication.getContext()).getString(SPUtil.TOKEN));
        request = requestBuilder.post(request.body()).build();
        return request;
    }


    /***
     * 添加PUT的公共参数
     */
    public Request addPutParameter(Request request) throws IOException {
//        Map<String, String> requstMap = new HashMap<>();
//        if (request.body().contentLength() > 0 && request.body() instanceof FormBody) {
//            FormBody formBody = (FormBody) request.body();
//            //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
//            for (int i = 0; i < formBody.size(); i++) {
//                requstMap.put(formBody.name(i), formBody.value(i));
//                LogUtils.e(request.url() + "参数:" + formBody.name(i) + "=" + formBody.value(i));
//            }
//        }
        Request.Builder requestBuilder = request.newBuilder().addHeader("Authorization",SPUtil.getInstance(BaseApplication.getContext()).getString(SPUtil.TOKEN));
        request = requestBuilder.put(request.body()).build();
        return request;
    }


    /**
     * 传递DELETE请求的全局参数
     * @param request
     * @return
     */
    public Request addDeleteParameter(Request request) throws IOException {
        HttpUrl.Builder builder = request.url().newBuilder();
        Request newRequest = request.newBuilder()
                .addHeader("Authorization",SPUtil.getInstance(BaseApplication.getContext()).getString(SPUtil.TOKEN))
                .method(request.method(), request.body())
                .url(builder.build())
                .delete(request.body())
                .build();
        return newRequest;
    }


    /**
     * 刷新token
     * @throws IOException
     */
    private BaseBean reFreshToken(){
        BaseBean baseBean = null;
        final LoginP loginP= (LoginP) SPUtil.getInstance(BaseApplication.getContext()).getObject(SPUtil.ACCOUNT,LoginP.class);
        try {
            baseBean=Http.getRetrofitNoInterceptor().create(HttpApi.class).login(loginP).execute().body();
        }catch (Exception e){
            e.printStackTrace();
        }
        return baseBean;
    }

    public int getCode(String json) {
        int code = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

}
