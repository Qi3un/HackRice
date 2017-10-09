package riceapp.hackrice.riceapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener{

    public static final int RC_SIGN_IN = 9001;
    public static final int COURSE_INFO = 1001;
    public static final int EDIT_TODO = 2001;
    public static final int ADD_COURSE = 3001;
    public static final int ADD_TODO = 4001;

    public static final int COURSE_DELETED = 1002;
    public static final int TODO_EDITED = 2002;
    public static final int TODO_NO_CHANGE = 2003;
    public static final int TODO_DELETED = 2004;
    public static final int TODO_ADDED = 2005;
    public static final int COURSE_ADDED = 3002;

    public static final int LOW_PRIORITY = 3;
    public static final int MED_PRIORITY = 2;
    public static final int HIGH_PRIORITY = 1;


    public static int EVENT_COLOR_1;
    public static int EVENT_COLOR_2;
    public static int EVENT_COLOR_3;




    private GoogleSignInAccount acct;

    private WeekView mWeekView;
    CoordinatorLayout appBarMainPage;
    View currentMainView;
    int currentPageId;
    int currentPriority;
    Category currentCategory;
    ConstraintLayout contentCalendarPage;
    ListView listPage;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MainPage.class.getName()+"Log", "onCreate");

        setContentView(R.layout.activity_main_page);

        EVENT_COLOR_1 = ContextCompat.getColor(this, R.color.event_color_01);
        EVENT_COLOR_2 = ContextCompat.getColor(this, R.color.event_color_02);
        EVENT_COLOR_3 = ContextCompat.getColor(this, R.color.event_color_03);

        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        appBarMainPage = findViewById(R.id.app_bar_main_page_layout);
        contentCalendarPage = (ConstraintLayout)layoutInflater.inflate(R.layout.content_calendar_page, appBarMainPage, false);
        listPage = (ListView)layoutInflater.inflate(R.layout.content_list_page, appBarMainPage, false);
        navigationView = findViewById(R.id.nav_view);
        mWeekView = contentCalendarPage.findViewById(R.id.weekView);

        currentPageId = R.id.content_calendar_page;
        appBarMainPage.addView(contentCalendarPage);
        currentMainView = contentCalendarPage;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);
        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);
        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);
        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);
        mWeekView.setNumberOfVisibleDays(3);
        // Lets change some dimensions to best fit the view.
        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));

        startActivityForResult(new Intent(this, SignInActivity.class), RC_SIGN_IN);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshPage() {
        appBarMainPage.removeView(currentMainView);
        if (currentPageId == R.id.nav_calendar) {
            appBarMainPage.addView(contentCalendarPage);
            currentMainView = contentCalendarPage;
        } else if (currentPageId == R.id.nav_user_course_list) {
            ServerRequest serverRequest = new ServerRequest(this);
            Log.d(MainPage.class.getName()+"Log", "nav_user_course_list updated");
            List<Course> courses = serverRequest.getAllUserCourses(acct.getEmail());
            listPage.setAdapter(new Course.CourseArrayAdapter(this, courses));
            listPage.setOnItemClickListener(new Course.CourseItemOnClickListener(this, acct));
            appBarMainPage.addView(listPage, 1);
            currentMainView = listPage;
        } else if (currentPageId == R.id.nav_all_todo_list) {
            ServerRequest serverRequest = new ServerRequest(this);
            Log.d(MainPage.class.getName()+"Log", "nav_all_todo_list updated");
            List<Todo> todo = serverRequest.getAllItems(acct.getEmail());
            listPage.setAdapter(new Todo.TodoArrayAdapter(this, todo));
            listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(this, acct));
            appBarMainPage.addView(listPage, 1);
            currentMainView = listPage;
        } else if (currentPageId == R.id.nav_todo_by_priority) {
            Log.d(MainPage.class.getName()+"Log", "nav_todo_by_priority updated");
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Low", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ServerRequest serverRequest = new ServerRequest(MainPage.this);
                    currentPriority = LOW_PRIORITY;
                    List<Todo> todo = serverRequest.getItemsByPriority(acct.getEmail(), currentPriority);
                    listPage.setAdapter(new Todo.TodoArrayAdapter(MainPage.this, todo));
                    listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(MainPage.this, acct));
                    appBarMainPage.addView(listPage, 1);
                    currentMainView = listPage;
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Medium", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ServerRequest serverRequest = new ServerRequest(MainPage.this);
                    currentPriority = MED_PRIORITY;
                    List<Todo> todo = serverRequest.getItemsByPriority(acct.getEmail(), currentPriority);
                    listPage.setAdapter(new Todo.TodoArrayAdapter(MainPage.this, todo));
                    listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(MainPage.this, acct));
                    appBarMainPage.addView(listPage, 1);
                    currentMainView = listPage;
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "High", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ServerRequest serverRequest = new ServerRequest(MainPage.this);
                    currentPriority = HIGH_PRIORITY;
                    List<Todo> todo = serverRequest.getItemsByPriority(acct.getEmail(), currentPriority);
                    listPage.setAdapter(new Todo.TodoArrayAdapter(MainPage.this, todo));
                    listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(MainPage.this, acct));
                    appBarMainPage.addView(listPage, 1);
                    currentMainView = listPage;
                }
            });
            alertDialog.show();
        } else if (currentPageId == R.id.nav_todo_by_category) {
            Log.d(MainPage.class.getName()+"Log", "nav_todo_by_category updated");
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Casual", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ServerRequest serverRequest = new ServerRequest(MainPage.this);
                    currentPriority = LOW_PRIORITY;
                    List<Todo> todo = serverRequest.getItemsByCat(acct.getEmail(), Category.CATEGORY_CASUAL.getName());
                    listPage.setAdapter(new Todo.TodoArrayAdapter(MainPage.this, todo));
                    listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(MainPage.this, acct));
                    appBarMainPage.addView(listPage, 1);
                    currentMainView = listPage;
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Official", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ServerRequest serverRequest = new ServerRequest(MainPage.this);
                    currentPriority = MED_PRIORITY;
                    List<Todo> todo = serverRequest.getItemsByCat(acct.getEmail(), Category.CATEGORY_OFFICIAL.getName());
                    listPage.setAdapter(new Todo.TodoArrayAdapter(MainPage.this, todo));
                    listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(MainPage.this, acct));
                    appBarMainPage.addView(listPage, 1);
                    currentMainView = listPage;
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Deadline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ServerRequest serverRequest = new ServerRequest(MainPage.this);
                    currentPriority = HIGH_PRIORITY;
                    List<Todo> todo = serverRequest.getItemsByCat(acct.getEmail(), Category.CATEGORY_DEADLINE.getName());
                    listPage.setAdapter(new Todo.TodoArrayAdapter(MainPage.this, todo));
                    listPage.setOnItemClickListener(new Todo.TodoItemOnClickListener(MainPage.this, acct));
                    appBarMainPage.addView(listPage, 1);
                    currentMainView = listPage;
                }
            });
            alertDialog.show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id != R.id.nav_add_course && id != R.id.nav_add_todo) {
            currentPageId = id;
            refreshPage();
        } else if (id == R.id.nav_add_course) {
            startActivityForResult(new Intent(this, AddCourseActivity.class).putExtra("acct", acct), ADD_COURSE);
        } else if (id == R.id.nav_add_todo) {
            startActivityForResult(new Intent(this, EditTodoActivity.class).putExtra("acct", acct).putExtra("requestCode", ADD_TODO), ADD_TODO);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        ServerRequest serverRequest = new ServerRequest(this);
        return serverRequest.getEventsByTime(acct.getEmail(), ""+newYear, ""+newMonth);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK)
                handleSignInResult(data);
            else
                finish();
        } else if (requestCode == COURSE_INFO) {
            if (resultCode == COURSE_DELETED) {
                refreshPage();
            }
        } else if (requestCode == EDIT_TODO || requestCode == ADD_TODO) {
            if (resultCode == TODO_EDITED || resultCode == TODO_DELETED || resultCode == TODO_ADDED) {
                refreshPage();
            }
        } else if (requestCode == ADD_COURSE) {


        }
    }

    // [START handleSignInResult]
    private void handleSignInResult(Intent data) {
        acct = (GoogleSignInAccount) data.getParcelableExtra("account");
        // Signed in successfully, show authenticated UI.
        Log.d(MainPage.class.getName()+"Log", "handleSignInResult");
    }
    // [END handleSignInResult]
}
