package com.example.accessiblememory.models;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.example.accessiblememory.R;

import java.util.HashMap;

public class SoundPoolPlayer {

    private SoundPool mPlayer;
    private HashMap mSounds = new HashMap();
    private Context context;
    private float volume = 1;

    public SoundPoolPlayer(Context context) {

        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mPlayer = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            this.mPlayer = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        mSounds.put(R.raw.correct_sound, this.mPlayer.load(context, R.raw.correct_sound, 1));
        mSounds.put(R.raw.incorrect_sound, this.mPlayer.load(context, R.raw.incorrect_sound, 1));
        mSounds.put(R.raw.win_sound, this.mPlayer.load(context, R.raw.win_sound, 1));
        mSounds.put(R.raw.card_sound, this.mPlayer.load(context, R.raw.card_sound, 1));
        mSounds.put(R.raw.end_of_board_sound, this.mPlayer.load(context, R.raw.end_of_board_sound, 1));
        mSounds.put(R.raw.swipe_sound, this.mPlayer.load(context, R.raw.swipe_sound, 1));
    }

    public void playSound(int id) {
        int soundId = (Integer) mSounds.get(id);
        this.mPlayer.play(soundId, this.volume, this.volume, 1, 0, 1);
    }

    public void playCorrectAnswerSound() {
        playSound(R.raw.correct_sound);
    }

    public void playIncorrectAnswerSound() {
        playSound(R.raw.incorrect_sound);
    }

    public void playWinSound() {
        playSound(R.raw.win_sound);
    }

    public void playCardSound() {
        playSound(R.raw.card_sound);
    }

    public void playEndOfBoardSound() {
        playSound(R.raw.end_of_board_sound);
    }

    public void playSwipeSound() {
        playSound(R.raw.swipe_sound);
    }

    public void release() {
        this.mPlayer.release();
        this.mPlayer = null;
    }

    public void unmute() {
        this.volume = 1;
    }

    public void mute() {
        this.volume = 0;
    }
}
