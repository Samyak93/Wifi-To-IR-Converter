package com.android.wifitoirclient.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;



public class IRDataDBHelper {

	private static final String TAG = "DBHelper";
	private IRDataDBAdapter iRDataDBAdapter;

	public IRDataDBHelper(Context mContext) {
		iRDataDBAdapter= new IRDataDBAdapter(mContext);
	}

	public boolean updateIRData(IRDataBean iRDataBean)
	{
		iRDataDBAdapter.open();
		boolean result = iRDataDBAdapter.updateIRData(iRDataBean);
		iRDataDBAdapter.close();
		if(result)
			return true;
		else 
			return false;
	}

	public IRDataBean getIRData(String name, String type)
	{
		iRDataDBAdapter.open();
		Cursor configCursor = iRDataDBAdapter.fetchIRData(type);
		if( configCursor != null)
		{
			configCursor.moveToFirst();
			if(!configCursor.isAfterLast())
			{
				do
				{	
					IRDataBean iRDataBean = getIRDataBeanFromCursor(configCursor);
					if(iRDataBean.getName().equalsIgnoreCase(name))
					{
						return iRDataBean;
					}
				}while(configCursor.moveToNext());
			}
			configCursor.close();
		}
		iRDataDBAdapter.close();
		return null;
	}

	private static IRDataBean getIRDataBeanFromCursor(Cursor configCursor)
	{
		IRDataBean tempBean = new IRDataBean();

		tempBean.setName(configCursor.getString(configCursor.getColumnIndex(IRDataDBAdapter.COLUMN_NAME)));
		tempBean.setType(configCursor.getString(configCursor.getColumnIndex(IRDataDBAdapter.COLUMN_TYPE)));
		tempBean.setValue(configCursor.getString(configCursor.getColumnIndex(IRDataDBAdapter.COLUMN_VALUE)));


		Log.d(TAG, "adding config bean to list : property : " + tempBean.getName() + " value : " + tempBean.getValue() );
		return tempBean;

	}
}
