package com.github.abusalam.vehiclelogbook;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteLogBookDB extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "LogBookDB.db";
	private static final int DATABASE_VERSION = 2;

	// Table names
	private static final String TABLE_LogBook = "LogBook";
	private static final String TABLE_Tours = "Tours";

	// Table Columns names
	private static final String _LogID = "LogID";
	private static final String _TourID = "TourID";
	private static final String _StartDateTime = "StartDateTime";
	private static final String _EndDateTime = "EndDateTime";
	private static final String _Remarks = "Remarks";
	private static final String _VehicleNo = "VehicleNo";
	private static final String _Source = "Source";
	private static final String _Destination = "Destination";
	private static final String _Distance = "Distance";

	// Database creation sql statement
	private static final String DATABASE_CREATE_LogBook = "create table "
			+ TABLE_LogBook + "(" + _LogID
			+ " integer primary key autoincrement, " + _TourID + " integer,"
			+ _StartDateTime + " timestamp not null," + _EndDateTime
			+ " timestamp not null," + _Remarks + " text);";
	private static final String DATABASE_CREATE_Tours = "create table "
			+ TABLE_Tours + "(" + _TourID
			+ " integer primary key autoincrement, " + _VehicleNo + " text,"
			+ _Source + " text," + _Destination + " text," + _Distance
			+ " integer," + _Remarks + " text);";
	private String[] allColumns = { _TourID, _VehicleNo, _Source, _Destination,
			_Distance, _Remarks };

	public SQLiteLogBookDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.d("Constructor: ", "Creating Database ..");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d("SQLiteDB:onCreate: ", DATABASE_CREATE_LogBook);
		database.execSQL(DATABASE_CREATE_LogBook);
		Log.d("SQLiteDB:onCreate: ", DATABASE_CREATE_Tours);
		database.execSQL(DATABASE_CREATE_Tours);

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		// TODO Auto-generated method stub

	}

	// Adding new Tour
	public long addTours(Tour tour) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(_VehicleNo, tour.getVehicleNo());
		values.put(_Source, tour.getSource());
		values.put(_Destination, tour.getDestination());
		values.put(_Distance, tour.getDistance());
		values.put(_Remarks, tour.getRemarks());
		tour.setTourID((int) db.insert(TABLE_Tours, null, values));
		db.close();
		// Inserting Row
		Log.d("addTours: ", "Inserting .." + tour.getTourID());
		return tour.getTourID();
	}

	// Getting All Tours
	public List<Tour> getAllTours() {
		List<Tour> TourList = new ArrayList<Tour>();
		// Select All Query

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_Tours, allColumns, null, null, null,
				null, null);

		// looping through all rows and adding to list
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Tour Tour = new Tour();
			Tour.setTourID(Integer.parseInt(cursor.getString(0)));
			Tour.setVehicleNo(cursor.getString(1));
			Tour.setSource(cursor.getString(2));
			Tour.setDestination(cursor.getString(3));
			Tour.setDistance(Integer.parseInt(cursor.getString(4)));
			Tour.setRemarks(cursor.getString(5));
			// Adding Tour to list
			TourList.add(Tour);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		// return contact list
		return TourList;
	}

	// Getting Tours Count
	public int getToursCount() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_Tours, allColumns, null, null, null,
				null, null);
		// return count
		int c = cursor.getCount();
		cursor.close();
		db.close();
		return c;
	}

	public String getDBPath() {
		SQLiteDatabase db = this.getReadableDatabase();
		String DBPath = db.getPath().toString();
		db.close();
		return DBPath;
	}
}
