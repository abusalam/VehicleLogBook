package com.github.abusalam.vehiclelogbook;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

@SuppressWarnings("unused")
public class VehicleLogBook extends Activity {

	private SQLiteLogBookDB db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("onCreate: ", "Instantiating Database ..");
		db = new SQLiteLogBookDB(this);
		db.getWritableDatabase();
		Log.d("onCreate: ", "Path: " + db.getDBPath());
		showAllTours();
	}

	private void showAllTours() {
		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.removeAllViewsInLayout();
		List<Tour> values = db.getAllTours();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Tour> adapter = new ArrayAdapter<Tour>(this,
				R.layout.log_list_item, values);
		// setListAdapter(adapter);
		for (int i = 0; i < values.size(); i++)
			Log.d("onCreate: ", "At(" + i + "): " + values.toString());
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_New_Tour:
			Toast.makeText(
					this,
					"Added New TourID "
							+ db.addTours(new Tour("VehicleNo-1234", "Quarter",
									"Office", 6)), Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_Backup:
			Toast.makeText(this, "Tours: " + db.getToursCount(),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_Reload:
			Toast.makeText(this, "Reload", Toast.LENGTH_SHORT).show();
			showAllTours();
			return true;
		case R.id.menu_Settings:
			Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_Exit:
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
