package riceapp.hackrice.riceapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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


import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Admin on 2017/9/23.
 */

public class ServerRequest {

    public Context context;

    String common = "http://10.112.78.228:8080/RiceApp/";

    public ServerRequest(Context context) {
        this.context = context;
    }

    public void enroll(final String email) {
        // TODO: create new url
        String url = common + "enroll";

        finished = false;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                        } catch (final JSONException e) {
                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        while(!finished);
        return;
    }


    Course c = new Course();

    public List<Course> getAllCourses(final String termCode, final String subject) {
        String url = common + "courses/" + termCode + "/" + subject;
        RequestQueue queue = Volley.newRequestQueue(context);

        finished = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            courses = new LinkedList<Course>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            JSONArray dataArray = jsonObj.getJSONArray("data");
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
                            // TODO: display courses


                        } catch (final JSONException e) {
                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        while (!finished);

        return courses;
    }
    List<Course> courses;
    boolean finished;

    public List<Course> getAllUserCourses(final String email) {
        String url = common + "courses";
        RequestQueue queue = Volley.newRequestQueue(context);
        finished = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                             courses = new LinkedList<Course>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                            JSONArray dataArray = jsonObj.getJSONArray("data");
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
                            // TODO: display courses


                        } catch (final JSONException e) {
                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        while (!finished) ;

        return courses;
    }

    public void dropUserCourse(final String email, final String termCode, final int crn) {
        // TODO: create new url
        String url = common + "drop/" + termCode + "/" + crn;
        finished = false;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                        } catch (final JSONException e) {
                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
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
        while(!finished);
        return;
    }


    public void addUserCourse(final String email, final String termCode, final int crn) {
        // TODO: create new url
        String url = common + "enroll/" + termCode + "/" + crn;

        finished = false;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                        } catch (final JSONException e) {
                        }

                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        while(!finished) ;
        return;
    }
    List<Todo> items;

    public List<Todo> getAllItems(final String email) {
        String url = common + "todoitems";
        RequestQueue queue = Volley.newRequestQueue(context);

        finished = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            items = new LinkedList<Todo>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");


                            JSONArray dataArray = jsonObj.getJSONArray("data");



                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                Todo item = new Todo();
                                item.setName(jo.getString("name"));
                                item.setEndTime(jo.getString("endTime"));
                                item.setStartTime(jo.getString("beginTime"));
                                item.setDate(jo.getString("date"));

                                Category cat = new Category();
                                JSONObject co = jo.getJSONObject("todoCategory");
                                cat.setName(co.getString("category"));
                                cat.setColor(Integer.parseInt(co.getString("color")));
                                cat.setMethod(co.getString("notifyMethod"));
                                cat.setPriority(Integer.parseInt(co.getString("priority")));

                                item.setCategory(cat);
                                items.add(item);
                            }

                            // TODO: display items


                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        while(!finished) ;
        return items;
    }

    public List<Todo> getItemsByPriority(final String email, final int priority) {
        String url = common + "todoitems/priority";
        RequestQueue queue = Volley.newRequestQueue(context);

        finished = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            items = new LinkedList<Todo>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");


                            JSONArray dataArray = jsonObj.getJSONArray("data");



                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                Todo item = new Todo();
                                item.setName(jo.getString("name"));
                                item.setEndTime(jo.getString("endTime"));
                                item.setStartTime(jo.getString("beginTime"));
                                item.setDate(jo.getString("date"));

                                Category cat = new Category();
                                JSONObject co = jo.getJSONObject("todoCategory");
                                cat.setName(co.getString("category"));
                                cat.setColor(Integer.parseInt(co.getString("color")));
                                cat.setMethod(co.getString("notifyMethod"));
                                cat.setPriority(Integer.parseInt(co.getString("priority")));

                                item.setCategory(cat);
                                items.add(item);
                            }

                            // TODO: display items

                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("priority", ""+priority);
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        while(!finished) ;
        return items;
    }

    public List<Todo> getItemsByCat(final String email, final String category) {
        String url = common + "todoitems/category";
        RequestQueue queue = Volley.newRequestQueue(context);

        finished = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            items = new LinkedList<Todo>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");


                            JSONArray dataArray = jsonObj.getJSONArray("data");



                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject jo = dataArray.getJSONObject(i);
                                Todo item = new Todo();
                                item.setName(jo.getString("name"));
                                item.setEndTime(jo.getString("endTime"));
                                item.setStartTime(jo.getString("beginTime"));
                                item.setDate(jo.getString("date"));

                                Category cat = new Category();
                                JSONObject co = jo.getJSONObject("todoCategory");
                                cat.setName(co.getString("category"));
                                cat.setColor(Integer.parseInt(co.getString("color")));
                                cat.setMethod(co.getString("notifyMethod"));
                                cat.setPriority(Integer.parseInt(co.getString("priority")));

                                item.setCategory(cat);
                                items.add(item);
                            }

                            // TODO: display items


                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        while (!finished) ;
        return items;
    }

    public void editUserItem(final String email, final String itemName, final Todo wholeItem) {
        // TODO: create new url
        String url = common + "todoitem/update";
        finished = false;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");
                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("itemName", itemName);
                params.put("name", wholeItem.getName());
                Category cat = wholeItem.getCategory();
                params.put("category", cat.getName());
                params.put("color", ""+cat.getColor());
                params.put("priority", ""+cat.getPriority());
                params.put("notifyMethod", cat.getMethod());
                params.put("date", wholeItem.getDate());
                params.put("beginTime", wholeItem.getStartTime());
                params.put("endTime", wholeItem.getEndTime());
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        while(!finished);
        return;
    }

    public void deleteUserItem(final String email, final String itemName) {
        // TODO: create new url
        String url = common + "todoitem/" + itemName + "/delete";

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");

                        } catch (final JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

    public void addUserItem(final String email, final Todo wholeItem) {
        // TODO: create new url
        String url = common + "todoitem";

        finished = false;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");

                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("name", wholeItem.getName());
                Category cat = wholeItem.getCategory();
                params.put("category", cat.getName());
                params.put("color", ""+cat.getColor());
                params.put("priority", ""+cat.getPriority());
                params.put("notifyMethod", cat.getMethod());
                params.put("date", wholeItem.getDate());
                params.put("beginTime", wholeItem.getStartTime());
                params.put("endTime", wholeItem.getEndTime());
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        while (!finished) ;
        return;
    }

    public  List<WeekViewEvent> getEventsByTime(final String email, final String year, final String month) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
        events.addAll(getItemsByTime(email, year, month));
        events.addAll(getcoursesByTime(email, year, month));
        return events;
    }

    List<WeekViewEvent> weekViewEvents;
    public static int countWeekEvent = 1;

    public List<WeekViewEvent> getItemsByTime(final String email, final String year, final String month) {
        String url = common + "todoitems/search";
        RequestQueue queue = Volley.newRequestQueue(context);

        finished = false;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            weekViewEvents = new LinkedList<WeekViewEvent>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");


                            JSONArray dataArray = jsonObj.getJSONArray("data");


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
                                WeekViewEvent event = new WeekViewEvent(countWeekEvent++, jo.getString("name"), cldStart, cldEnd);
                                weekViewEvents.add(event);
                            }

                            // TODO: display events


                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        while(!finished) ;
        return weekViewEvents;
    }


    public List<WeekViewEvent> getcoursesByTime(final String email, final String year, final String month) {
        String url = common + "getEventsWithinMonth";
        RequestQueue queue = Volley.newRequestQueue(context);

        finished = false;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            final List<WeekViewEvent> events = new LinkedList<WeekViewEvent>();
                            JSONObject jsonObj = new JSONObject(response);
                            String code = jsonObj.getString("code");
                            String msg = jsonObj.getString("msg");


                            JSONArray dataArray = jsonObj.getJSONArray("data");


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
                                WeekViewEvent event = new WeekViewEvent(countWeekEvent++, jo.getString("courseName"), cldStart, cldEnd);
                                weekViewEvents.add(event);
                            }

                            // TODO: display events


                        } catch (final JSONException e) {

                        }
                        finished = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        while(!finished) ;
        return weekViewEvents;
    }
/*
    public List<Course> getAllUserCourses(String email) {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course(
                "201710",
                "Fall 16",
                "COMP",
                556,
                "001",
                "School of Engineering",
                "Computer Science",
                13949,
                "INTRO TO COMPUTER NETWORKS",
                "Network architectures, algorithms, and protocols. Local- and Wide-area networking. Intra- and inter-domain routing. Transmission reliability. Flow and congestion control. TCP/IP. Multicast. Quality of Service. Network Security - Networked applications. Additional coursework required beyond the undergraduate course requirements.",
                "4",
                1,
                "HRZ 212",
                "Ng, Tze Sing E.",
                70,
                23,
                0,
                0,
                "",
                "http://courses.rice.edu/admweb/swkscat.main?p_action=COURSE&p_crn=13949&p_term=201710"
        ));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));
        courses.add(new Course("201710", "COMP", 556, "Intro. to Computer Network"));
        courses.add(new Course("201710", "COMP", 532, "Intro. to Distributed Systems"));

        return courses;
    }*/
/*
    public List<WeekViewEvent> getEventsByMonth(String email, int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        Log.d(ServerRequest.class.getName()+"Log", "newYear:"+newYear + " newMonth:"+newMonth);

        Calendar startTime = Calendar.getInstance();
        Calendar endTime;

        startTime.set(2017,Calendar.SEPTEMBER,23,8,0);
        endTime = (Calendar)startTime.clone();
        endTime.set(2017,Calendar.SEPTEMBER,23,10,0);
        WeekViewEvent event = new WeekViewEvent(1, "event1", startTime, endTime);
        event.setColor(ContextCompat.getColor(context, R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(2017,Calendar.SEPTEMBER,23,8,10);
        endTime = (Calendar)startTime.clone();
        endTime.set(2017,Calendar.SEPTEMBER,23,10,20);
        event = new WeekViewEvent(2, "event2", (Calendar)startTime, (Calendar)endTime);
        event.setColor(ContextCompat.getColor(context, R.color.event_color_02));
        events.add(event);

        return events;
    }

    public List<Course> getAllCourses(String email, int term, String dept) {
        return new ArrayList<Course>();
    }

    public void deleteUserCourse(String email, Course course) {

    }

    public List<Todo> getAllUserTodos(String email) {
        List<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo(
                "Meeting Prof",
                Category.CATEGORY_OFFICIAL,
                "2017/9/23",
                "13:00",
                "15:00",
                "Progress Report of recent survey")
        );
        todos.add(new Todo(
                "Meeting friend",
                Category.CATEGORY_CASUAL,
                "2017/9/24",
                "11:00",
                "12:30",
                "Eating lunch!!")
        );
        todos.add(new Todo(
                "Deadline on Project 1 of COMP556",
                Category.CATEGORY_DEADLINE,
                "2017/9/24",
                "16:00",
                "23:59",
                "Need to be submitted before midnight")
        );

        return todos;
    }

    public List<Todo> getUserTodosByPriority(String email, int currentPriority) {
        List<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo(
                "Meeting Prof",
                Category.CATEGORY_OFFICIAL,
                "2017/9/23",
                "13:00",
                "15:00",
                "Progress Report of recent survey")
        );
        todos.add(new Todo(
                "Meeting friend",
                Category.CATEGORY_CASUAL,
                "2017/9/24",
                "11:00",
                "12:30",
                "Eating lunch!!")
        );
        todos.add(new Todo(
                "Deadline on Project 1 of COMP556",
                Category.CATEGORY_DEADLINE,
                "2017/9/24",
                "16:00",
                "23:59",
                "Need to be submitted before midnight")
        );

        List<Todo> out = new ArrayList<Todo>();
        for (Todo t:todos)
            if (t.getCategory().getPriority() <= currentPriority)
                out.add(t);

        return out;
    }

    public List<Todo> getUserTodosByCategory(String email, Category category) {
        List<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo(
                "Meeting Prof",
                Category.CATEGORY_OFFICIAL,
                "2017/9/23",
                "13:00",
                "15:00",
                "Progress Report of recent survey")
        );
        todos.add(new Todo(
                "Meeting friend",
                Category.CATEGORY_CASUAL,
                "2017/9/24",
                "11:00",
                "12:30",
                "Eating lunch!!")
        );
        todos.add(new Todo(
                "Deadline on Project 1 of COMP556",
                Category.CATEGORY_DEADLINE,
                "2017/9/24",
                "16:00",
                "23:59",
                "Need to be submitted before midnight")
        );

        List<Todo> out = new ArrayList<Todo>();
        for (Todo t:todos)
            if (t.getCategory().equals(category))
                out.add(t);

        return out;
    }

    public void editUserTodo(String email, Todo todo) {
    }

    public void deleteUserTodo(String email, Todo todo) {

    }*/
}
