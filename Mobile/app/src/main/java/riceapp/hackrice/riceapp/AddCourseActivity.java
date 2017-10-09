package riceapp.hackrice.riceapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    private Course course;
    private GoogleSignInAccount acct;

    private final String[] subjStrs = {"ARCH", "COMP","CSCI", "ELEC", "EMBA", "ASIA", "KECK", "CHEM"};
    private Spinner spinner;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        course = (Course) getIntent().getParcelableExtra("course");
        acct = (GoogleSignInAccount) getIntent().getParcelableExtra("acct");

        listView = findViewById(R.id.list_view_all_courses);

        spinner = findViewById(R.id.spinner_subject);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjStrs);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ServerRequest serverRequest = new ServerRequest(AddCourseActivity.this);
                List<Course> courses = serverRequest.getAllCourses("201710", subjStrs[i]);
                listView.setAdapter(new Course.CourseArrayAdapter(AddCourseActivity.this, courses));
                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        final Course item = (Course) adapterView.getItemAtPosition(i);
                        ServerRequest serverRequest = new ServerRequest(AddCourseActivity.this);
                        serverRequest.addUserCourse(acct.getEmail(), item.getTermCode(), item.getCrn());
                        setResult(MainPage.COURSE_ADDED);
                        finish();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }
}
