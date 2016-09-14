package course.labs.activitylab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ActivityOne extends Activity {

		// string for logcat documentation
		private final static String TAG = "Lab-ActivityOne";

		SharedPreferences pref;

		private int createCtr, startCtr, resumeCtr, pauseCtr, stopCtr, restartCtr, destroyCtr;

		TextView create;
		TextView start;
		TextView resume;
		TextView pause;
		TextView stop;
		TextView restart;
		TextView destroy;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_one);
			
			//Log cat print out
			Log.i(TAG, "onCreate called");

			create = (TextView) findViewById(R.id.create);
			start = (TextView) findViewById(R.id.start);
			resume = (TextView) findViewById(R.id.resume);
			pause = (TextView) findViewById(R.id.pause);
			stop = (TextView) findViewById(R.id.stop);
			restart = (TextView) findViewById(R.id.restart);
			destroy = (TextView) findViewById(R.id.destroy);

			pref = getPreferences(MODE_PRIVATE);
			createCtr = pref.getInt("Create counter", 0);
			if(createCtr != 0){
				startCtr = pref.getInt("Start counter", 0);
				resumeCtr = pref.getInt("Resume counter", 0);
				pauseCtr = pref.getInt("Pause counter", 0);
				stopCtr = pref.getInt("Stop counter", 0);
				restartCtr = pref.getInt("Restart counter", 0);
				destroyCtr = pref.getInt("Destroy counter", 0);
			}

			createCtr++;
			create.setText(getString(R.string.onCreate) + createCtr);
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_one, menu);
			return true;
		}
		
		// lifecycle callback overrides
		
		@Override
		public void onStart(){
			super.onStart();
			
			//Log cat print out
			Log.i(TAG, "onStart called");

			startCtr++;
			start.setText(getString(R.string.onStart) + startCtr);
		}

		@Override
		public void onResume(){
			super.onResume();

			//Log cat print out
			Log.i(TAG, "onResume called");

			resumeCtr++;
			resume.setText(getString(R.string.onResume) + resumeCtr);

			saveCounters();
		}

		@Override
		public void onPause(){
			super.onPause();

			//Log cat print out
			Log.i(TAG, "onPause called");

			pauseCtr++;
			pause.setText(getString(R.string.onPause) + pauseCtr);
		}

		@Override
		public void onStop(){
			super.onStop();

			//Log cat print out
			Log.i(TAG, "onStop called");

			stopCtr++;
			stop.setText(getString(R.string.onStop) + stopCtr);

			saveCounters();
		}

		@Override
		public void onRestart(){
			super.onRestart();

			//Log cat print out
			Log.i(TAG, "onRestart called");

			restartCtr++;
			restart.setText(getString(R.string.onRestart) + restartCtr);
		}

		@Override
		public void onDestroy(){
			super.onDestroy();

			//Log cat print out
			Log.i(TAG, "onDestroy called");

			destroyCtr++;
			destroy.setText(getString(R.string.onDestroy) + destroyCtr);
		}

		@Override
		public void onSaveInstanceState(Bundle savedInstanceState){
			super.onSaveInstanceState(savedInstanceState);
			//Save all counter to the save state
			savedInstanceState.putInt("Create counter", createCtr);
			savedInstanceState.putInt("Start counter", startCtr);
			savedInstanceState.putInt("Resume counter", resumeCtr);
			savedInstanceState.putInt("Pause counter", pauseCtr);
			savedInstanceState.putInt("Stop counter", stopCtr);
			savedInstanceState.putInt("Restart counter", restartCtr);
			savedInstanceState.putInt("Destroy counter", destroyCtr);
		}

		protected void onRestoreInstanceState (Bundle savedInstanceState) {
			// Restore value of counters from saved state
			if(savedInstanceState != null){
				createCtr = savedInstanceState.getInt("Create counter");
				startCtr = savedInstanceState.getInt("Start counter");
				resumeCtr = savedInstanceState.getInt("Resume counter");
				pauseCtr = savedInstanceState.getInt("Pause counter");
				stopCtr = savedInstanceState.getInt("Stop counter");
				restartCtr = savedInstanceState.getInt("Restart counter");
				destroyCtr = savedInstanceState.getInt("Destroy counter");

				pause.setText(getString(R.string.onPause) + pauseCtr);
				stop.setText(getString(R.string.onStop) + stopCtr);
				destroy.setText(getString(R.string.onDestroy) + destroyCtr);
				restart.setText(getString(R.string.onRestart) + restartCtr);
			}
		}

		public void launchActivityTwo(View view) {
				//Start activity two
				Intent act2 = new Intent(this, ActivityTwo.class);
				startActivity(act2);
		}

		private void saveCounters(){
			SharedPreferences.Editor edit = pref.edit();
			edit.putInt("Create counter", createCtr);
			edit.putInt("Start counter", startCtr);
			edit.putInt("Resume counter", resumeCtr);
			edit.putInt("Pause counter", pauseCtr);
			edit.putInt("Stop counter", stopCtr);
			edit.putInt("Restart counter", restartCtr);
			edit.putInt("Destroy counter", destroyCtr);
			edit.commit();
		}

	public void clearCounters(View view){
		create.setText(getString(R.string.onCreate) + createCtr);
		start.setText(getString(R.string.onStart) + startCtr);
		resume.setText(getString(R.string.onResume) + resumeCtr);
		pause.setText(getString(R.string.onPause) + pauseCtr);
		stop.setText(getString(R.string.onStop) + stopCtr);
		restart.setText(getString(R.string.onRestart) + restartCtr);
		destroy.setText(getString(R.string.onDestroy) + destroyCtr);

		SharedPreferences.Editor edit = pref.edit();
		edit.putInt("Create counter", 0);
		edit.putInt("Start counter", 0);
		edit.putInt("Resume counter", 0);
		edit.putInt("Pause counter", 0);
		edit.putInt("Stop counter", 0);
		edit.putInt("Restart counter", 0);
		edit.putInt("Destroy counter", 0);
		edit.commit();
	}

}
