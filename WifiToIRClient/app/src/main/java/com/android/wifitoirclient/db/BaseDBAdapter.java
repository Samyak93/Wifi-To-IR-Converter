package com.android.wifitoirclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDBAdapter  extends SQLiteOpenHelper
{

	private static String DATABASE_NAME = "wifitoir";
	
	public static String CONFIG_TABLE_NAME="wifi_code_tbl";
	public static String COLUMN_NAME = "name";
	public static String COLUMN_VALUE = "value";
	public static String COLUMN_TYPE = "type";
	
	private static final String CREATE_CONFIG_TABLE_QUERY ="create table "+CONFIG_TABLE_NAME+" ("+COLUMN_NAME+" text primary key not null, "+COLUMN_VALUE+" text not null, "+COLUMN_TYPE+" text not null);";
	
	private static int DATABASE_VERSION = 2;
	
	protected SQLiteDatabase mySqliteDB;

	public BaseDBAdapter(Context context)
	{
		this(context,DATABASE_NAME,null,DATABASE_VERSION);	
	}
	
	public BaseDBAdapter(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CONFIG_TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(CREATE_CONFIG_TABLE_QUERY);
	}
	
	public void open()
	{
		mySqliteDB = getWritableDatabase();	
	}	

	public void close()
	{	
		mySqliteDB.close();
	}
}
