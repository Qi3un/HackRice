package riceapp.hackrice.riceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {/*
    TextView mTextView;
    TextView parsed;

    String common;
    Course c = new Course();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.TextView);
        parsed = (TextView) findViewById(R.id.parsed);
        common = "http://10.112.78.228:8080/RiceApp/";

//        getAllCourses("201810", "COMP");
//        getAllUserCourses("email");
//        enroll("email");
//        addUserCourse("email", "201810", 10020);
//        dropUserCourse("email", "201810", 10020);
//        getAllItems("mack@gmail.com");
//        getItemsByPriority("mack@gmail.com", "normal");
//        getItemsByCat("mack@gmail.com", "category");
        TodoCategory cat = new TodoCategory();
        cat.setCategory("amusement");
        cat.setColor("cvan");
        cat.setPriority("normal");
        cat.setNotifyMethod("none");
        TodoItem item = new TodoItem();
        item.setName("itemName");
        item.setTodoCategory(cat);
        item.setDate("2017-01-01");
        item.setBeginTime("1220");
        item.setEndTime("1420");
//        addUserItem("mack@gmail.com", item);
//        deleteUserItem("mack@gmail.com", "itemName");
        getItemsByTime("mack@gmail.com", "2017", "9");
//        getcoursesByTime("mack@gmail.com", "2017", "10");
    }


    private void enroll(final String email) {
        // TODO: create new url
        String url = common + "enroll";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }




    private void getAllCourses(final String termCode, final String subject) {
        String url = common + "courses/" + termCode + "/" + subject;
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            List<Course> courses = new LinkedList<Course>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                            JSONArray dataArray = jsonObj.getJSONArray("data");
                            mTextView.setText("Length of dataArray is " + dataArray.length());
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                c.setTermCode(jo.getString("termCode"));
                                c.setTermDescription(jo.getString("termDescription"));
                                c.setCourseNumber(jo.getInt("courseNumber"));
                                c.setDepartment(jo.getString("department"));
                                c.setSubject(jo.getString("subject"));
                                c.setTitle(jo.getString("title"));
                                c.setDescription(jo.getString("description"));
                                c.setCreditHours(jo.getString("creditHours"));
                                c.setMeetingDays(jo.getString("meetingDays"));
                                c.setStartTime(jo.getString("startTime"));
                                c.setEndTime(jo.getString("endTime"));
                                c.setLocation(jo.getString("location"));
                                c.setInstructor(jo.getString("instructor"));
                                courses.add(c);
                            }
                            parsed.setText("Number of courses in get:" + courses.size());
                            // TODO: display courses

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("termCode", termCode);
                params.put("subject", subject);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void getAllUserCourses(final String email) {
        String url = common + "courses";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            List<Course> courses = new LinkedList<Course>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                            JSONArray dataArray = jsonObj.getJSONArray("data");
                            mTextView.setText("Length of dataArray is " + dataArray.length());
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                c.setTermCode(jo.getString("termCode"));
                                c.setTermDescription(jo.getString("termDescription"));
                                c.setCourseNumber(jo.getInt("courseNumber"));
                                c.setDepartment(jo.getString("department"));
                                c.setSubject(jo.getString("subject"));
                                c.setTitle(jo.getString("title"));
                                c.setDescription(jo.getString("description"));
                                c.setCreditHours(jo.getString("creditHours"));
                                c.setMeetingDays(jo.getString("meetingDays"));
                                c.setStartTime(jo.getString("startTime"));
                                c.setEndTime(jo.getString("endTime"));
                                c.setLocation(jo.getString("location"));
                                c.setInstructor(jo.getString("instructor"));
                                courses.add(c);
                            }
                            parsed.setText("Number of courses in get:" + courses.size());
                            // TODO: display courses

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void dropUserCourse(final String email, final String termCode, final int crn) {
        // TODO: create new url
        String url = common + "drop/" + termCode + "/" + crn;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("termCode", termCode);
                params.put("crn", crn + "");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }


    private void addUserCourse(final String email, final String termCode, final int crn) {
        // TODO: create new url
        String url = common + "enroll/" + termCode + "/" + crn;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("termCode", termCode);
                params.put("crn", crn + "");
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }


    private void getAllItems(final String email) {
        String url = common + "todoitems";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            final List<TodoItem> items = new LinkedList<TodoItem>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);

                            JSONArray dataArray = jsonObj.getJSONArray("data");

                            mTextView.setText("Length of dataArray is " + dataArray.length());

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                TodoItem item = new TodoItem();
                                item.setName(jo.getString("name"));
                                item.setEndTime(jo.getString("endTime"));
                                item.setBeginTime(jo.getString("beginTime"));
                                item.setDate(jo.getString("date"));

                                TodoCategory cat = new TodoCategory();
                                JSONObject co = jo.getJSONObject("todoCategory");
                                cat.setCategory(co.getString("category"));
                                cat.setColor(co.getString("color"));
                                cat.setNotifyMethod(co.getString("notifyMethod"));
                                cat.setPriority(co.getString("priority"));

                                item.setTodoCategory(cat);
                                items.add(item);
                            }
                            parsed.setText("Number of courses in get:" + items.size());
                            // TODO: display items

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void getItemsByPriority(final String email, final String priority) {
        String url = common + "todoitems/priority";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            final List<TodoItem> items = new LinkedList<TodoItem>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);

                            JSONArray dataArray = jsonObj.getJSONArray("data");

                            mTextView.setText("Length of dataArray is " + dataArray.length());

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                TodoItem item = new TodoItem();
                                item.setName(jo.getString("name"));
                                item.setEndTime(jo.getString("endTime"));
                                item.setBeginTime(jo.getString("beginTime"));
                                item.setDate(jo.getString("date"));

                                TodoCategory cat = new TodoCategory();
                                JSONObject co = jo.getJSONObject("todoCategory");
                                cat.setCategory(co.getString("category"));
                                cat.setColor(co.getString("color"));
                                cat.setNotifyMethod(co.getString("notifyMethod"));
                                cat.setPriority(co.getString("priority"));

                                item.setTodoCategory(cat);
                                items.add(item);
                            }
                            parsed.setText("Number of courses in get:" + items.size());
                            // TODO: display items

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("priority", priority);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void getItemsByCat(final String email, final String category) {
        String url = common + "todoitems/category";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            final List<TodoItem> items = new LinkedList<TodoItem>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);

                            JSONArray dataArray = jsonObj.getJSONArray("data");

                            mTextView.setText("Length of dataArray is " + dataArray.length());

                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                TodoItem item = new TodoItem();
                                item.setName(jo.getString("name"));
                                item.setEndTime(jo.getString("endTime"));
                                item.setBeginTime(jo.getString("beginTime"));
                                item.setDate(jo.getString("date"));

                                TodoCategory cat = new TodoCategory();
                                JSONObject co = jo.getJSONObject("todoCategory");
                                cat.setCategory(co.getString("category"));
                                cat.setColor(co.getString("color"));
                                cat.setNotifyMethod(co.getString("notifyMethod"));
                                cat.setPriority(co.getString("priority"));

                                item.setTodoCategory(cat);
                                items.add(item);
                            }
                            parsed.setText("Number of courses in get:" + items.size());
                            // TODO: display items

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("category", category);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void editUserItem(final String email, final String itemName, final TodoItem wholeItem) {
        // TODO: create new url
        String url = common + "todoitem/update";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("itemName", itemName);
                params.put("name", wholeItem.getName());
                TodoCategory cat = wholeItem.getTodoCategory();
                params.put("category", cat.getCategory());
                params.put("color", cat.getColor());
                params.put("priority", cat.getPriority());
                params.put("notifyMethod", cat.getNotifyMethod());
                params.put("date", wholeItem.getDate());
                params.put("beginTime", wholeItem.getBeginTime());
                params.put("endTime", wholeItem.getEndTime());
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void deleteUserItem(final String email, final String itemName) {
        // TODO: create new url
        String url = common + "todoitem/" + itemName + "/delete";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("itemName", itemName);
                params.put("email", email);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private void addUserItem(final String email, final TodoItem wholeItem) {
        // TODO: create new url
        String url = common + "todoitem";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);
                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("name", wholeItem.getName());
                TodoCategory cat = wholeItem.getTodoCategory();
                params.put("category", cat.getCategory());
                params.put("color", cat.getColor());
                params.put("priority", cat.getPriority());
                params.put("notifyMethod", cat.getNotifyMethod());
                params.put("date", wholeItem.getDate());
                params.put("beginTime", wholeItem.getBeginTime());
                params.put("endTime", wholeItem.getEndTime());
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }

    private  void getEventsByTime(final String email, final String year, final String month) {
        getItemsByTime(email, year, month);
        getcoursesByTime(email, year, month);
        return;
    }

    private void getItemsByTime(final String email, final String year, final String month) {
        String url = common + "todoitems/search";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            final List<Event> events = new LinkedList<Event>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);

                            JSONArray dataArray = jsonObj.getJSONArray("data");

                            mTextView.setText("Length of dataArray is " + dataArray.length());
                            final int[] months = new int[]{Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH,
                                    Calendar.APRIL, Calendar.MAY, Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER,
                                    Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER};
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                String startTime = jo.getString("beginTime");
                                String endTime = jo.getString("endTime");
                                Calendar cldStart = Calendar.getInstance();
                                cldStart.set(Integer.parseInt(year), months[Integer.parseInt(month)], Integer.parseInt(jo.getString("date").substring(8)),
                                        Integer.parseInt(startTime.substring(0, 2)), Integer.parseInt(startTime.substring(3)));
                                Calendar cldEnd = Calendar.getInstance();
                                cldStart.set(Integer.parseInt(year), months[Integer.parseInt(month)], Integer.parseInt(jo.getString("date").substring(8)),
                                        Integer.parseInt(endTime.substring(0, 2)), Integer.parseInt(endTime.substring(3)));
                                Event event = new Event(jo.getString("name"), cldStart, cldEnd);
                                events.add(event);
                            }
                            parsed.setText("Number of courses in get:" + events.size());
                            // TODO: display events

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("year", year);
                params.put("month", month);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }


    private void getcoursesByTime(final String email, final String year, final String month) {
        String url = common + "getEventsWithinMonth";
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //mTextView.setText("Response is: "+ response);
                        try {
                            final List<Event> events = new LinkedList<Event>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            parsed.setText("Parsed: code: " + code + "; msg: " + msg);

                            JSONArray dataArray = jsonObj.getJSONArray("data");

                            mTextView.setText("Length of dataArray is " + dataArray.length());
                            final int[] months = new int[]{Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH,
                            Calendar.APRIL, Calendar.MAY, Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER,
                            Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER};
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                String startTime = jo.getString("startTime");
                                String endTime = jo.getString("endTime");
                                Calendar cldStart = Calendar.getInstance();
                                cldStart.set(jo.getInt("year"), months[jo.getInt("month") - 1], jo.getInt("day"),
                                        Integer.parseInt(startTime.substring(0, 2)), Integer.parseInt(startTime.substring(2)));
                                Calendar cldEnd = Calendar.getInstance();
                                cldEnd.set(jo.getInt("year"), months[jo.getInt("month") - 1], jo.getInt("day"),
                                        Integer.parseInt(endTime.substring(0, 2)), Integer.parseInt(endTime.substring(2)));
                                Event event = new Event(jo.getString("courseName"), cldStart, cldEnd);
                                events.add(event);
                            }
                            parsed.setText("Number of courses in get:" + events.size());
                            // TODO: display events

                        } catch (final JSONException e) {
                            mTextView.setText("Json parsing error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("year", year);
                params.put("month", month);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        return;
    }*/
}