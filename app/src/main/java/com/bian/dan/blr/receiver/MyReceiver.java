package com.bian.dan.blr.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.bian.dan.blr.activity.audit.financial.AuditFinancialDetailsActivity;
import com.bian.dan.blr.activity.audit.outbound.AuditOutBoundDetailsActivity;
import com.bian.dan.blr.activity.audit.production.AuditProductPlanDetailsActivity;
import com.zxdc.utils.library.util.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
//			LogUtils.e("[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				LogUtils.e ("[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Map<String,Object> valueMap=printBundle(bundle);
				if(valueMap==null){
					return;
				}
				final String type= (String) valueMap.get("type");
				final String fid=(String) valueMap.get("fid");
				Intent gotoIntent=new Intent();
				gotoIntent.putExtra("detailsId",fid);
				switch (type){
					case "1":
						gotoIntent.setClass(context, AuditOutBoundDetailsActivity.class);
						 break;
					case "2":
						gotoIntent.setClass(context, AuditFinancialDetailsActivity.class);
						break;
					case "3":
						gotoIntent.setClass(context, AuditOutBoundDetailsActivity.class);
						break;
					case "4":
						gotoIntent.setClass(context, AuditProductPlanDetailsActivity.class);
						break;
					default:
						break;
				}
				gotoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(gotoIntent);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				LogUtils.e( "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private  Map<String,Object> printBundle(Bundle bundle) {
		Map<String,Object> valueMap=new HashMap<>();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				valueMap.put("notificationId",bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){

			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					LogUtils.e( "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						if(myKey.equals("fid")){
							valueMap.put("fid",json.optString(myKey));
						}else if(myKey.equals("type")){
							valueMap.put("type",json.optString(myKey));
						}
					}
				} catch (JSONException e) {
					LogUtils.e("Get message extra JSON error!");
				}

			}
		}
		return valueMap;
	}
	
}
