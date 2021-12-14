package com.example.accessiblememory.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.accessiblememory.R;
import com.example.accessiblememory.contracts.IGame;
import com.example.accessiblememory.presenters.GamePresenter;
import com.example.accessiblememory.utils.WinDialog;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements IGame.View {

    private GamePresenter gamePresenter;
    private ArrayList<Button> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.backToLevelActionBar);

        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        gamePresenter = new GamePresenter(getWindowManager(), getResources(), getApplicationContext(), actionBarHeight);
        gamePresenter.attach(this);
        gamePresenter.newGame();
    }

    @Override
    public void prepareBoard(final int sizeX, final int sizeY, int buttonWidth, final int buttonHeight) {

        TableLayout boardTableLayout = findViewById(R.id.boardTable);

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        params.setMargins((int) getResources().getDimension(R.dimen.buttonsMargin), (int) getResources().getDimension(R.dimen.buttonsMargin),
                (int) getResources().getDimension(R.dimen.buttonsMargin), (int) getResources().getDimension(R.dimen.buttonsMargin));


        for (int i = 0; i < sizeY; ++i) {
            TableRow currentRow = new TableRow(getApplicationContext());
            currentRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < sizeX; ++j) {
                Button currentButton = new Button(getApplicationContext());
                currentButton.setWidth(buttonWidth);
                currentButton.setHeight(buttonHeight);
                currentButton.setId(i * sizeX + j + 1);
                currentButton.setTextSize((float) (buttonHeight * 0.2));
                currentButton.setBackground(getResources().getDrawable(R.drawable.card_background));
                currentButton.setLayoutParams(params);
                currentButton.setContentDescription("karta " + currentButton.getId());

                cards.add(currentButton);

                currentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button button = (Button) view;
                        gamePresenter.chooseCard(button.getId(), button);
                    }
                });
                currentRow.addView(currentButton);
            }
            boardTableLayout.addView(currentRow);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(GameActivity.this, LevelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //startActivity(new Intent(OptionsActivity.this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void enableAllCards() {
        for(Button card : cards) {
            card.setEnabled(true);
        }
    }

    @Override
    public void openEndOfGameDialog() {
        WinDialog winDialog = new WinDialog(gamePresenter);
        winDialog.show(getSupportFragmentManager(), "Koniec gry");
    }

    @Override
    public void showCard(char c, Button button) {
        button.announceForAccessibility(Character.toString(c));
        button.setContentDescription(c + " " + button.getContentDescription());
        button.setText(Character.toString(c));
        button.setBackground(getResources().getDrawable(R.drawable.card_foreground));
    }

    @Override
    public void hideCards() {
        for(Button card : cards) {
            if(card.isEnabled()) {
                card.setContentDescription("karta " + card.getId());
                card.setText(" ");
                card.setBackground(getResources().getDrawable(R.drawable.card_background));
            }
        }
    }

    @Override
    public void disableFoundPair(int id1, int id2) {
        for(Button card : cards) {
            if(card.getId() == id1 || card.getId() == id2) {
                card.setEnabled(false);
            }
        }
    }

    @Override
    public void disableAllCards() {
        for(Button card : cards) {
            card.setEnabled(false);
        }
    }

}
