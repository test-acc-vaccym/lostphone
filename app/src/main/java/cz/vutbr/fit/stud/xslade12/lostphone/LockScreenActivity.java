package cz.vutbr.fit.stud.xslade12.lostphone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LockScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        startService(new Intent(this,LockScreenService.class));
        //Set up our Lockscreen
        makeFullScreen();

        setContentView(R.layout.activity_lock_screen);
    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.lock_screen, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }



    /**
     * A simple method that sets the screen to fullscreen.  It removes the Notifications bar,
     *   the Actionbar and the virtual keys (if they are on the phone)
     */
    public void makeFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        return; //Do nothing!
    }

    public void unlockScreen(View view) {
        //Instead of using finish(), this totally destroys the process
        android.os.Process.killProcess(android.os.Process.myPid());
    }



    public void onClickBtnCallOwner(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:123456789"));
        startActivity(callIntent);
    }



    public void onClickBtnUnlock(View view) {
        EditText editUnlock = (EditText) findViewById(R.id.editUnlock);
        String pin = editUnlock.getText().toString();

        if(pin.equals("1234")) {
            this.finish();
        } else {
            Toast.makeText(this, R.string.wrongPin, Toast.LENGTH_SHORT).show();
        }
    }



//
//    //here's where most of the magic happens
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        // Do this on key down
//        boolean up = event.getAction() == KeyEvent.ACTION_UP;
//        //flags to true if the event we are getting is the up (release)
//        switch (event.getKeyCode()) {
//            //case KeyEvent.KEYCODE_VOLUME_UP:
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//            case KeyEvent.KEYCODE_FOCUS:
//                if (up) {
//                    break;//break without return means pass on to other processes
//                    //doesn't consume the press
//                }
//
//                Log.v("key event", "locked key");
//
//                return true;
//            //returning true means we handled the event so don't pass it to other processes
//
//            case KeyEvent.KEYCODE_CAMERA:
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                Log.v("key event","wake key");
////                awake = true;
////                setBright((float) 0.1);//tell screen to go on with 10% brightness
//                return true;
//
//            case KeyEvent.KEYCODE_POWER:
//                Log.v("key event","unlock key");
//                finish();
//                return true;
//
//            case KeyEvent.KEYCODE_HOME:
//                Toast.makeText(this, "Muhehe", Toast.LENGTH_LONG).show();
//                return true;
//
//            default:
//
//                break;
//        }
//        return super.dispatchKeyEvent(event);
//    }

}
