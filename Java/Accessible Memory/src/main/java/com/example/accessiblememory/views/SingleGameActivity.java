package com.example.accessiblememory.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.ISingleGame;
import com.example.accessiblememory.listeners.OnSwipeTouchListener;
import com.example.accessiblememory.presenters.SingleGamePresenter;
import com.example.accessiblememory.utils.WinDialog;


public class SingleGameActivity extends AppCompatActivity implements ISingleGame.View, SensorEventListener {

    private TextView cardTextView;
    private TextView cardNumberTextView;
    private SingleGamePresenter singleGamePresenter;
    private Vibrator vibrator;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float originalX;
    private float originalY;
    private float originalZ;

    private boolean first = true;

    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.backToLevelActionBar);

        singleGamePresenter = new SingleGamePresenter(getWindowManager(), getResources(), getApplicationContext());
        singleGamePresenter.attach(this);

        if(singleGamePresenter.isAlternativeNavigationEnabled()) {
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            //accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            //accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            startTime = System.currentTimeMillis();
        }

        cardNumberTextView = findViewById(R.id.cardNumberTextView);
        cardNumberTextView.setText("1");

        cardTextView = findViewById(R.id.cardTextView);
        cardTextView.setBackground(getResources().getDrawable(R.drawable.card_background));


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        cardTextView.setWidth((int) (0.8 * displayMetrics.widthPixels));
        cardTextView.setHeight((int) (0.8 * displayMetrics.heightPixels));
        cardTextView.setTextSize((float) 0.2 * displayMetrics.heightPixels);
        cardTextView.setGravity(Gravity.CENTER);

        cardTextView.setTextColor(getResources().getColor(R.color.colorButtonBackground));

    //    if(!singleGamePresenter.isAlternativeNavigationEnabled()) {
            findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(SingleGameActivity.this) {
                @Override
                public void onSwipeLeft() {
                    if(!singleGamePresenter.isAlternativeNavigationEnabled())
                        singleGamePresenter.moveRight();
                }

                @Override
                public void onSwipeRight() {
                    if(!singleGamePresenter.isAlternativeNavigationEnabled())
                        singleGamePresenter.moveLeft();
                }

                @Override
                public void onSwipeTop() {
                    if(!singleGamePresenter.isAlternativeNavigationEnabled())
                        singleGamePresenter.moveBottom();
                }

                @Override
                public void onSwipeBottom() {
                    if(!singleGamePresenter.isAlternativeNavigationEnabled())
                        singleGamePresenter.moveTop();

                }

                @Override
                public void onSingleTap() {
                    singleGamePresenter.chooseCard();
                }
            });

        vibrator = (Vibrator) SingleGameActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        singleGamePresenter.newGame();
    }

    protected void onResume() {
        super.onResume();
        if(singleGamePresenter.isAlternativeNavigationEnabled()) {
            sensorManager.registerListener(this, accelerometer, 1000000);
            startTime = System.currentTimeMillis();
        }
        this.first = true;
    }

    protected void onPause() {
        super.onPause();
        if(singleGamePresenter.isAlternativeNavigationEnabled())
            sensorManager.unregisterListener(this);
        this.first = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(SingleGameActivity.this, LevelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showCard(char c) {
        cardTextView.announceForAccessibility(Character.toString(c));
        cardTextView.setBackground(getResources().getDrawable(R.drawable.card_foreground));
        cardTextView.setText(Character.toString(c));
        cardTextView.setTextColor(getResources().getColor(R.color.colorButtonBackground));
    }

    @Override
    public void hideCard() {
        cardTextView.setText("");
        cardTextView.setBackground(getResources().getDrawable(R.drawable.card_background));
    }

    @Override
    public void disableCard() {
            cardTextView.setTextColor(getResources().getColor(R.color.colorDisabledCard));
    }

    @Override
    public void setCardId(int cardId, char currentCard) {
        cardTextView.setContentDescription(currentCard + " karta " + cardId);
        cardNumberTextView.setText(Integer.toString(cardId));
    }

    @Override
    public void openEndOfGameDialog() {
        WinDialog winDialog = new WinDialog(singleGamePresenter);
        winDialog.show(getSupportFragmentManager(), "Koniec gry");
    }

    @Override
    public void showBoardSize(int sizeX, int sizeY) {
        AlertDialog dialog = new AlertDialog.Builder(SingleGameActivity.this, R.style.DialogStyle).create();
        dialog.setMessage("Plansza " + sizeX + " na " + sizeY);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        dialog.show();
        TextView textView = dialog.findViewById(android.R.id.message);
        textView.setTextSize(32);
    }

    @Override
    public void vibrate() {
        vibrator.vibrate(50);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[1];
        float z = event.values[0];
        float y = event.values[2];


        if (first) {
            originalX = x;
            originalY = y;
            originalZ = z;
            first = false;
        }

        if (System.currentTimeMillis() - startTime > 1200) {
            startTime = System.currentTimeMillis();

            float delta = 15;

            if (x > originalX && Math.abs(x - originalX) > delta) {
                singleGamePresenter.moveTop();
                return;
            }

            if (x < originalX && Math.abs(x - originalX) > delta) {
                singleGamePresenter.moveBottom();
                return;
            }

            if (z > originalZ && Math.abs(z - originalZ) > delta) {
                singleGamePresenter.moveRight();
                return;
            }

            if (z < originalZ && Math.abs(z - originalZ) > delta) {
               singleGamePresenter.moveLeft();
               return;
            }
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
