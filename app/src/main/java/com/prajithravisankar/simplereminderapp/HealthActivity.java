package com.prajithravisankar.simplereminderapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class HealthActivity extends AppCompatActivity {

    private Task task;
    private EditText taskNameEditText;
    private Button dateButton;
    private Button timeButton;
    private Button addButton;
    private ListView taskList;
    private LocalDateTime dueDateTime;
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayAdapter<Task> taskArrayAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        initializeWidgets();
        initializeListeners();
        taskArrayAdapter = new ArrayAdapter<>(HealthActivity.this, android.R.layout.simple_list_item_1, tasks);
        taskList.setAdapter(taskArrayAdapter);
    }

    private void initializeListeners() {
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get input from the user about date
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(HealthActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    dueDateTime = LocalDateTime.of(year, month + 1, day, 0, 0);
                                }
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get input from the user about time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(HealthActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    dueDateTime = dueDateTime.withHour(hour).withMinute(minute);
                                }
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a task and add it to the list view
                String taskName = taskNameEditText.getText().toString().trim(); // Fixed to use getText()
                if (dueDateTime != null && !taskName.isEmpty()) { // Check if dueDateTime is not null
                    task = new Task(taskName, dueDateTime);
                    tasks.add(task); // Add the task to the list
                    taskArrayAdapter.notifyDataSetChanged(); // Notify adapter of data change
                } else {
                    Toast.makeText(HealthActivity.this, "Please select a date and time", Toast.LENGTH_SHORT).show();
                }
            }
        });

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task selectedTask = tasks.get(position);
                String remainingTime = selectedTask.getRemainingTime();
                Toast.makeText(HealthActivity.this, remainingTime, Toast.LENGTH_LONG).show();
            }
        });

        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task selectedTask = tasks.get(position);

                new AlertDialog.Builder(HealthActivity.this)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Remove the task from the list
                                tasks.remove(selectedTask);
                                taskArrayAdapter.notifyDataSetChanged(); // Notify adapter of data change
                                Toast.makeText(HealthActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true; // Indicates that the long click event was handled
            }
        });
    }

    private void initializeWidgets() {
        taskNameEditText = findViewById(R.id.edit_text_task_name);
        dateButton = findViewById(R.id.button_date);
        timeButton = findViewById(R.id.button_time);
        addButton = findViewById(R.id.button_add);
        taskList = findViewById(R.id.list_view_tasks);
    }
}