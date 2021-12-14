package pl.polsl.lab.mobileorganizer;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import pl.polsl.lab.mobileorganizer.mobileorganizer.pols.lab.mobileorganizer.exceptions.DatePriorTodayException;
import pl.polsl.lab.mobileorganizer.mobileorganizer.polsl.lab.mobileorganizer.model.Task;
import pl.polsl.lab.mobileorganizer.mobileorganizer.polsl.lab.mobileorganizer.model.TaskManager;
/**
 *Class representing activity and handling all actions on the screen
 *
 * @author Aleksandra Musial‚
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private EditText taskNameText;
    private Button addTaskButton;
    private TaskManager taskManager;
    private String taskDate;
    private String taskName;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView taskView;
    private Button showButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        taskManager = new TaskManager();
        displayDate = (TextView) findViewById(R.id.dataView);
        taskNameText = (EditText) findViewById(R.id.taskText);
        addTaskButton = (Button) findViewById(R.id.addButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        taskView = (TextView) findViewById(R.id.taskView);
        showButton =(Button) findViewById(R.id.showButton);

        taskView.setMovementMethod(new ScrollingMovementMethod());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePicker = new DatePickerDialog(
                        MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                datePicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePicker.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month += 1;
                String dayString;
                String monthString;
                if(dayOfMonth < 10) {
                    dayString = "0" + Integer.toString(dayOfMonth);
                }
                else {
                    dayString = Integer.toString(dayOfMonth);
                }

                if(month < 10) {
                    monthString = "0" + Integer.toString(month);
                }
                else {
                    monthString = Integer.toString(month);
                }
                taskDate = dayString + "/"+ monthString + "/" + Integer.toString(year);
                displayDate.setText(taskDate);

            }
        };

    }

    public void showButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        ArrayList<Task> taskList = new ArrayList<>();

        switch (radioButton.getText().toString()) {
            case "All":
                taskList = taskManager.getTaskList();
                break;
            case "For today":
                taskList = taskManager.getTaskListForToday();
                break;
            case "After today":
                taskList = taskManager.getTaskListAfterToday();
                break;
        }

        if(!taskList.isEmpty()) {
            String tasks = "";
            taskView.setText(tasks);
            for (Task task : taskList) {
                tasks += (task.getDateString() + " " + task.getName() + "\n");
            }
            taskView.setText(tasks);
        }
        else {
            taskView.setText(" ");
            Toast.makeText(this, "No tasks to show", Toast.LENGTH_LONG).show();
        }

    }

    public void addButtonClicked(View view) {
        taskName = taskNameText.getText().toString();

        if(!taskName.equals("") && taskDate != null) {
            try {
                taskManager.addTask(taskDate, taskName);
                Toast.makeText(this, "New task was added", Toast.LENGTH_LONG).show();
                taskNameText.setText("");
            } catch (DatePriorTodayException e) {
                Toast.makeText(this, "The date you entered has already passed", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "You need to select the date and enter the name of task", Toast.LENGTH_LONG).show();
        }
    }
}
