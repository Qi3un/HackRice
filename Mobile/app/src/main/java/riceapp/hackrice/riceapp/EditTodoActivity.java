package riceapp.hackrice.riceapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Calendar;

public class EditTodoActivity extends AppCompatActivity {

    private Todo todo;
    private GoogleSignInAccount acct;

    private String categoryStrs[] = new String[]{"Casual", "Official", "Deadline"};

    private EditText todoNameText;
    private EditText todoDescriptionText;
    private Spinner categorySpinner;
    private EditText todoDateText;
    private EditText todoTimeText;
    private FloatingActionButton calendarButton;
    private FloatingActionButton timeButton;
    private int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        todo = (Todo) getIntent().getParcelableExtra("todo");
        acct = (GoogleSignInAccount) getIntent().getParcelableExtra("acct");
        requestCode = getIntent().getIntExtra("requestCode", -1);

        todoNameText = findViewById(R.id.todo_name_text);
        todoDescriptionText = findViewById(R.id.todo_descriptioin_text);
        todoDateText = findViewById(R.id.todo_date_text);
        todoTimeText = findViewById(R.id.todo_time_text);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        todoDateText.setText(""+mYear+"/"+mMonth+"/"+mDay);
        todoTimeText.setText(""+hour+":"+min);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_check);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTodoFromUI();
                ServerRequest serverRequest = new ServerRequest(EditTodoActivity.this);
                if (requestCode == MainPage.ADD_TODO) {
                    serverRequest.addUserItem(acct.getEmail(), todo);
                    setResult(MainPage.TODO_ADDED);
                } else if (requestCode == MainPage.EDIT_TODO) {
                    serverRequest.editUserItem(acct.getEmail(), todo.getName(), todo);
                    setResult(MainPage.TODO_EDITED);
                }
                finish();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab_no_change);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(MainPage.TODO_NO_CHANGE);
                finish();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab_delete);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerRequest serverRequest = new ServerRequest(EditTodoActivity.this);
                serverRequest.deleteUserItem(acct.getEmail(), todo.getName());
                setResult(MainPage.TODO_DELETED);
                finish();
            }
        });

        calendarButton = (FloatingActionButton)findViewById(R.id.fab_calendar);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTodoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                todoDateText.setText(""+year+"/"+monthOfYear+"/"+dayOfMonth);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timeButton = (FloatingActionButton)findViewById(R.id.fab_time);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int min = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditTodoActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                todoTimeText.setText(""+hourOfDay +":"+ minute);
                            }
                        }, 10, 0, true);
            }
        });


        categorySpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoryStrs);
        categorySpinner.setAdapter(arrayAdapter);

        if (todo != null) {
            updateUIFromTodo();
        } else {
            todo = new Todo();
        }
        fab = (FloatingActionButton) findViewById(R.id.fab_delete);
        if (requestCode == MainPage.ADD_TODO) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }
    }

    private void updateUIFromTodo() {
        todoNameText.setText(todo.getName());
        if (todo.getCategory().getName().equals(categoryStrs[0]))
            categorySpinner.setSelection(0);
        else if (todo.getCategory().getName().equals(categoryStrs[1]))
            categorySpinner.setSelection(1);
        else if (todo.getCategory().getName().equals(categoryStrs[2]))
            categorySpinner.setSelection(2);
    }

    private void updateTodoFromUI() {
        todo.setName(todoNameText.getText().toString());
        switch (categorySpinner.getSelectedItemPosition()) {
            case 0:
                todo.setCategory(Category.CATEGORY_CASUAL);
                break;
            case 1:
                todo.setCategory(Category.CATEGORY_OFFICIAL);
                break;
            case 2:
                todo.setCategory(Category.CATEGORY_DEADLINE);
                break;
            default:
                break;
        }
        todo.setDate(todoDateText.getText().toString());
        todo.setStartTime(todoTimeText.getText().toString());
        todo.setDescription(todoDescriptionText.getText().toString());
    }

}
