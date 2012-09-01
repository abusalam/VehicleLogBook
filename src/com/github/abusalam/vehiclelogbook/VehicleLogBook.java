package com.github.abusalam.vehiclelogbook;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
			LayoutInflater li = LayoutInflater.from(this);
			View promptsView = li.inflate(R.layout.add_tour_dialog, null);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
			alertDialogBuilder.setView(promptsView);
			 final EditText userInputVehicleNo = (EditText) promptsView
			 .findViewById(R.id.editTextVehicleNo);
			 final EditText userInputSource = (EditText) promptsView
					 .findViewById(R.id.editTextSource);
			 final EditText userInputDestination = (EditText) promptsView
					 .findViewById(R.id.editTextDestination);
			 final EditText userInputDistance = (EditText) promptsView
					 .findViewById(R.id.editTextDistance);
			alertDialogBuilder
			.setTitle(R.string.addTourDialogTitle)
					.setCancelable(false)
					.setPositiveButton(R.string.addTourButtonOk,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// result.setText(userInput.getText());
									Toast.makeText(
											getBaseContext(),
											"Added New TourID "
													+ db.addTours(new Tour(userInputVehicleNo.getText().toString(), userInputSource.getText().toString(),
															userInputDestination.getText().toString(), Integer.parseInt(userInputDistance.getText().toString()))), Toast.LENGTH_SHORT).show();
									showAllTours();
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
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
