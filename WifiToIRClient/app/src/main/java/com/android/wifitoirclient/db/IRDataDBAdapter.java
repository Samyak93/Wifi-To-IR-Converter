package com.android.wifitoirclient.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class IRDataDBAdapter extends BaseDBAdapter{

	private static final String TAG = "DBAdapter"; 

	public IRDataDBAdapter(Context context) {
		super(context);
	}

	public IRDataDBAdapter(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public int createConfig(IRDataBean config) {
		ContentValues initialValues = new ContentValues();

		initialValues.put(COLUMN_NAME, config.getName());
		initialValues.put(COLUMN_VALUE, config.getValue());
		initialValues.put(COLUMN_TYPE, config.getType());

		Log.d(TAG, "property : " + config.getName());
		Log.d(TAG, "value : "+ config.getValue());
		Log.d(TAG, "propertyType : " + config.getType());

		return (int) mySqliteDB.insert(CONFIG_TABLE_NAME, null, initialValues);
	}


	public boolean deleteConfigByName(String name) {
		return mySqliteDB.delete(CONFIG_TABLE_NAME, COLUMN_NAME+ "='" + name  + "'", null) > 0;
	}


	/**
	 * Return a Cursor positioned at the property  that matches the given propertyType
	 * 
	 * @param propertyType to retrieve
	 * @return Cursor positioned to matching property, if found
	 * @throws SQLException if property could not be found/retrieved
	 */
	public Cursor fetchIRData(String Type) throws SQLException {

		Cursor mCursor = mySqliteDB.query(true, CONFIG_TABLE_NAME, new String[] {COLUMN_NAME, COLUMN_VALUE , COLUMN_TYPE}, COLUMN_TYPE + "='" + Type +"'", null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/**
	 * Update the property using the value provided. 
	 * If the property is not present in the DB, then it is created and the value is stored.
	 * @param configBean to update
	 */
	public boolean updateIRData(IRDataBean config) {
		ContentValues args = new ContentValues();

		args.put(COLUMN_NAME, config.getName());
		args.put(COLUMN_VALUE, config.getValue());
		args.put(COLUMN_TYPE, config.getType());
		int numberofRowsaffected = mySqliteDB.update(CONFIG_TABLE_NAME, args, COLUMN_NAME + "='" + config.getName() +"'", null);
		if(  numberofRowsaffected == 0) // no row was found so we need to create one 
		{
			mySqliteDB.insert(CONFIG_TABLE_NAME, null, args);
			return true;
		}
		else if(  numberofRowsaffected  != 1)  // more than/less than 1 row was found so we are in trouble :P   ( this already takes care of 0 by the else if)
		{
			return false;
		}

		return true;  // all is well  .. only 1 row was updated
	}

}
