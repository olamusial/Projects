package pl.dartscounter.view;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.dartscounter.R;
import pl.dartscounter.contract.IScorecard;
import pl.dartscounter.presenter.ScorecardPresenter;

public class ScorecardView extends AppCompatActivity implements IScorecard.View {

    ScorecardPresenter scorecardPresenter;
    Button throwButton1;
    Button throwButton2;
    Button throwButton3;
    Button nextButton;
    Button previousButton;
    TextView scoreTextView;
    TextView playerTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecard_view);

        throwButton1 = findViewById(R.id.throw1);
        throwButton2 = findViewById(R.id.throw2);
        throwButton3 = findViewById(R.id.throw3);
        nextButton = findViewById(R.id.next);
        previousButton = findViewById(R.id.previous);
        scoreTextView = findViewById(R.id.score);
        playerTextView = findViewById(R.id.playerTitle);

        scorecardPresenter = new ScorecardPresenter();
        scorecardPresenter.attach(this);

        Bundle bundle = getIntent().getExtras();
        scorecardPresenter.setPlayersNumber(bundle.getInt(getResources().getString(R.string.players_number)));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorecardPresenter.nextPlayer();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorecardPresenter.previousPlayer();
            }
        });
    }


    @Override
    public void prepare(ArrayList<String> availableScores) {

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        params.setMargins((int) getResources().getDimension(R.dimen.buttonsMargin), (int) getResources().getDimension(R.dimen.buttonsMargin),
                (int) getResources().getDimension(R.dimen.buttonsMargin), (int) getResources().getDimension(R.dimen.buttonsMargin));

        for (int i = 0; i < 21; ++i) {
            TableRow currentRow = new TableRow(getApplicationContext());
            currentRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < 3; ++j) {
                Button currentButton = new Button(getApplicationContext());
                currentButton.setText(availableScores.get(i * 3 + j));
                currentButton.setId(i * 3 + j);
              //  currentButton.setBackgroundColor(getResources().getColor(R.color.pointsColor));
                currentButton.setBackground(getDrawable(R.drawable.points_button));
                currentButton.setLayoutParams(params);
                currentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button button = (Button) v;
                        String buttonText = button.getText().toString();
                        scorecardPresenter.playerThrows(Integer.valueOf(buttonText));
                    }
                });
                currentRow.addView(currentButton);
            }
            tableLayout.addView(currentRow);
        }
    }

    @Override
    public void disableThrowsButtons() {
        throwButton1.setEnabled(false);
        throwButton2.setEnabled(false);
        throwButton3.setEnabled(false);
    }

    @Override
    public void clearThrows() {
        updateThrows(getResources().getString(R.string.empty_score), getResources().getString(R.string.empty_score), getResources().getString(R.string.empty_score));
    }

    @Override
    public void disableScoreButtons() {

        Button button;
        int id;

        for (int i = 0; i < 21; ++i) {
            for (int j = 0; j < 3; ++j) {
                id = i * 3 + j;
                button = findViewById(id);
                button.setEnabled(false);
            }
        }

    }

    @Override
    public void enableScoreButtons() {

        Button button;
        int id;
        for (int i = 0; i < 21; ++i) {
            for (int j = 0; j < 3; ++j) {
                id = i * 3 + j;
                button = findViewById(id);
                button.setEnabled(true);
            }
        }

    }


    @Override
    public void updateThrows(String firstThrow, String secondThrow, String thirdThrow) {
        throwButton1.setText(firstThrow);
        throwButton2.setText(secondThrow);
        throwButton3.setText(thirdThrow);
    }

    @Override
    public void updateThrowsButtons(int buttonIndex) {
        ShapeDrawable shapedrawable = new ShapeDrawable();
        shapedrawable.setShape(new RectShape());
        shapedrawable.getPaint().setColor(Color.WHITE);
        shapedrawable.getPaint().setStrokeWidth(10f);
        shapedrawable.getPaint().setStyle(Paint.Style.STROKE);

        throwButton1.setBackgroundResource(R.drawable.navigation_button);
        throwButton2.setBackgroundResource(R.drawable.navigation_button);
        throwButton3.setBackgroundResource(R.drawable.navigation_button);

        switch (buttonIndex) {
            case 1:// throwButton1.setBackground(shapedrawable);
                throwButton1.setBackgroundResource(R.drawable.current_throw_button);
                break;
            case 2: throwButton2.setBackgroundResource(R.drawable.current_throw_button);
                break;
            case 3: throwButton3.setBackgroundResource(R.drawable.current_throw_button);
                break;
        }
    }

    @Override
    public void updatePlayerName(String name) {
        playerTextView.setText(getResources().getString(R.string.player_title) + " " + name);
    }

    @Override
    public void updateScore(String score) {
        scoreTextView.setText(score);
    }

    @Override
    public void winner(int playerId) {
        Toast.makeText(getApplicationContext(), "Player " + playerId + " wins!", Toast.LENGTH_LONG).show();
    }

}
