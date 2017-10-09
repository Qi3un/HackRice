package riceapp.hackrice.riceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class CourseInfoActivity extends AppCompatActivity {

    private Course course;
    private GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        course = (Course) getIntent().getParcelableExtra("course");
        acct = (GoogleSignInAccount) getIntent().getParcelableExtra("acct");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(course.getSubject()+course.getCourseNumber()+" "+course.getTitle());

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerRequest serverRequest = new ServerRequest(CourseInfoActivity.this);
                serverRequest.dropUserCourse(acct.getEmail(), course.getTermCode(), course.getCrn());
                Snackbar.make(view, "Course deleted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                setResult(MainPage.COURSE_DELETED);
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.course_info_text);
        textView.setText(course.getInfoString());
    }
}
