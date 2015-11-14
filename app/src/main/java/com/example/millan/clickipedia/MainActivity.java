package com.example.millan.clickipedia;

import android.content.DialogInterface;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    private GestureDetectorCompat mDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetector = new GestureDetectorCompat(this, new UpDetector());
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class UpDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            switch (swipeDirection(e1, e2)) {
                case UP:
                    Toast.makeText(MainActivity.this,"Up", Toast.LENGTH_SHORT).show();
                    break;
                case DOWN:
                    Toast.makeText(MainActivity.this,"Down", Toast.LENGTH_SHORT).show();
                    break;
                case RIGHT:
                    Toast.makeText(MainActivity.this,"Right", Toast.LENGTH_SHORT).show();
                    break;
                case LEFT:
                    Toast.makeText(MainActivity.this,"Left", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
        private Swipe swipeDirection(MotionEvent e1, MotionEvent e2) {
            float horizSwipe = e2.getX() - e1.getX();
            float vertSwipe = e2.getY() - e1.getY();
            if (Math.abs(horizSwipe) > Math.abs(vertSwipe)) {
                if (horizSwipe < 0) {
                    return Swipe.RIGHT;
                } else /*(horizSwipe > 0)*/ {
                    return Swipe.LEFT;
                }
            } else {
                if (vertSwipe < 0) {
                    return Swipe.UP;
                } else {
                    return Swipe.DOWN;
                }
            }

        }
    }
    enum Swipe {
        UP, RIGHT, DOWN, LEFT
    }
}
