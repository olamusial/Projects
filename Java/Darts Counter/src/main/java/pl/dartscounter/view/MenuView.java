package pl.dartscounter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import pl.dartscounter.R;

public class MenuView extends AppCompatActivity {

    NumberPicker playersNumberPicker;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view);

        playersNumberPicker = findViewById(R.id.playersNumberPicker);
        playersNumberPicker.setMaxValue(8);
        playersNumberPicker.setMinValue(1);
        playersNumberPicker.setWrapSelectorWheel(false);

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScorecardView.class);
                intent.putExtra(getResources().getString(R.string.players_number), playersNumberPicker.getValue());
                startActivity(intent);
            }
        });
    }
}
