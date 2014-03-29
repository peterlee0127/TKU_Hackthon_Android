package com.example.tkumeeting;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private final static int DBVERSION = 1;
	private final static String DB_NAME = "TKUMeeting.db";
	private final static String TABLE_NAME = "student";
	private final static String FIELD_NAME1 = "stu_id";
	private final static String FIELD_NAME2 = "password";
	private final static String FIELD_NAME3 = "auto_login";
	private final static String FIELD_NAME4 = "login_level";
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DBVERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String SQL = "CREATE TABLE IF NOT EXISTS " + getTableName() + "( " +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				FIELD_NAME1+" VARCHAR(100), " + FIELD_NAME2+" VARCHAR(100), " + FIELD_NAME3+" VARCHAR(100), " +FIELD_NAME4+" VARCHAR(100) " +
				");";
		db.execSQL(SQL);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		final String SQL = "DROP TABLE " + getTableName();
		db.execSQL(SQL); 
		
	}

	public String getTableName() {
		return TABLE_NAME;
	}

	public String getFieldName1() {
		return FIELD_NAME1;
	}
	public String getFieldName2() {
		return FIELD_NAME2;
	}
	public String getFieldName3() {
		return FIELD_NAME3;
	}
	public String getFieldName4() {
		return FIELD_NAME4;
	}

}

