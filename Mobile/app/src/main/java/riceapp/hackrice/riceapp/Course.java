package riceapp.hackrice.riceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 2017/9/23.
 */

public class Course implements Parcelable {
    private String endTime;
    private String startTime;
    private String meetingDays;
    private String termCode;
    private String termDescription;
    private String subject;
    private int courseNumber;
    private String section;
    private String school;
    private String department;
    private int crn;
    private String title;
    private String description;
    private String creditHours;
    private int session;
    private String location;
    private String instructor;
    private int maxEnrollment;
    private int actualEnrollment;
    private int xlstWaitCapacity;
    private int xlstWaitCount;
    private String catalogInstPermission;
    private String xlinkCourse;

    public Course() {}

    public Course(
             String termCode,
             String termDescription,
             String subject,
             int courseNumber,
             String section,
             String school,
             String department,
             int crn,
             String title,
             String description,
             String creditHours,
             int session,
             String location,
             String instructor,
             int maxEnrollment,
             int actualEnrollment,
             int xlstWaitCapacity,
             int xlstWaitCount,
             String catalogInstPermission,
             String xlinkCourse) {
        this.termCode = termCode;
        this.termDescription = termDescription;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.section = section;
        this.school = school;
        this.department = department;
        this.crn = crn;
        this.title = title;
        this.description = description;
        this.creditHours = creditHours;
        this.session = session;
        this.location = location;
        this.instructor = instructor;
        this.maxEnrollment = maxEnrollment;
        this.actualEnrollment = actualEnrollment;
        this.xlstWaitCapacity = xlstWaitCapacity;
        this.xlstWaitCount = xlstWaitCount;
        this.catalogInstPermission = catalogInstPermission;
        this.xlinkCourse = xlinkCourse;
    }

    public Course(String termCode, String subject, int courseNumber, String title) {
        this.termCode = termCode;
        this.subject = subject;
        this.courseNumber = courseNumber;
        this.title = title;
    }

    public String getInfoString() {
        String out =
                "Term: " + termDescription + "\n"
                + "Section: " + section + "\n"
                + "School: " + school + "\n"
                + "Department: " + department + "\n"
                + "CRN: " + crn + "\n"
                + "Description: " + description + "\n"
                + "Credit Hours: " + creditHours + "\n"
                + "Session: " + session + "\n"
                + "Location: " + location + "\n"
                + "Instructor: " + instructor + "\n"
                + "Max Enrollment: " + maxEnrollment + "\n"
                + "Actual Enrollment: " + actualEnrollment + "\n"
                + "Cross-listed Wait Capacity: " + xlstWaitCapacity + "\n"
                + "Cross-listed Wait Count: " + xlstWaitCount + "\n"
                + "Catelog Inst Permission: " + catalogInstPermission + "\n"
                + "Course Website Link: " + xlinkCourse;
        return out;
    }

    public String getMeetingDays() {
        return meetingDays;
    }

    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static class CourseItemOnClickListener implements ListView.OnItemClickListener {
        private AppCompatActivity context;
        private GoogleSignInAccount acct;
        public CourseItemOnClickListener(AppCompatActivity context, GoogleSignInAccount acct) {
            this.context = context;
            this.acct = acct;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Course item = (Course) parent.getItemAtPosition(position);
            context.startActivityForResult(new Intent(context, CourseInfoActivity.class).putExtra("course", item).putExtra("acct", acct), MainPage.COURSE_INFO);
        }
    }

    public static class CourseArrayAdapter extends ArrayAdapter<Course> {
        private final AppCompatActivity context;
        private HashMap<Course, Integer> mIdMap = new HashMap<Course, Integer>();

        public CourseArrayAdapter (AppCompatActivity context, List<Course> courses) {
            super(context, R.layout.list_view_card, courses);
            this.context = context;

            for (int i = 0; i < courses.size(); ++i) {
                mIdMap.put(courses.get(i), i);
            }
        }
        @Override
        public long getItemId(int position) {
            Course item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Course c = getItem(position);
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewCard = layoutInflater.inflate(R.layout.list_view_card, parent, false);
            TextView firstlineView = listViewCard.findViewById(R.id.list_view_card_firstLine);
            TextView secondlineView = listViewCard.findViewById(R.id.list_view_card_secondLine);
            firstlineView.setText(c.getSubject()+c.getCourseNumber()+" "+c.getTitle());
            secondlineView.setText(c.getTermCode().substring(0, 4) + (c.getTermCode().substring(4).equals("10") ? "Fall" : "Spring"));

            Log.d(Course.CourseArrayAdapter.class.getName()+"Log", "getView in ArrayAdapter, position: "+position);
            Log.d(Course.CourseArrayAdapter.class.getName()+"Log", "listViewCard: "+listViewCard);

            return listViewCard;
        }
    }

    protected Course(Parcel in) {
        termCode = in.readString();
        termDescription = in.readString();
        subject = in.readString();
        courseNumber = in.readInt();
        section = in.readString();
        school = in.readString();
        department = in.readString();
        crn = in.readInt();
        title = in.readString();
        description = in.readString();
        creditHours = in.readString();
        session = in.readInt();
        location = in.readString();
        instructor = in.readString();
        maxEnrollment = in.readInt();
        actualEnrollment = in.readInt();
        xlstWaitCapacity = in.readInt();
        xlstWaitCount = in.readInt();
        catalogInstPermission = in.readString();
        xlinkCourse = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(termCode);
        dest.writeString(termDescription);
        dest.writeString(subject);
        dest.writeInt(courseNumber);
        dest.writeString(section);
        dest.writeString(school);
        dest.writeString(department);
        dest.writeInt(crn);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(creditHours);
        dest.writeInt(session);
        dest.writeString(location);
        dest.writeString(instructor);
        dest.writeInt(maxEnrollment);
        dest.writeInt(actualEnrollment);
        dest.writeInt(xlstWaitCapacity);
        dest.writeInt(xlstWaitCount);
        dest.writeString(catalogInstPermission);
        dest.writeString(xlinkCourse);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public String getTermCode() {
        return termCode;
    }
    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }
    public String getTermDescription() {
        return termDescription;
    }

    public void setTermDescription(String termDescription) {
        this.termDescription = termDescription;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public int getCrn() {
        return crn;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }
    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }
    public int getActualEnrollment() {
        return actualEnrollment;
    }

    public void setActualEnrollment(int actualEnrollment) {
        this.actualEnrollment = actualEnrollment;
    }
    public int getXlstWaitCapacity() {
        return xlstWaitCapacity;
    }

    public void setXlstWaitCapacity(int xlstWaitCapacity) {
        this.xlstWaitCapacity = xlstWaitCapacity;
    }
    public int getXlstWaitCount() {
        return xlstWaitCount;
    }

    public void setXlstWaitCount(int xlstWaitCount) {
        this.xlstWaitCount = xlstWaitCount;
    }
    public String getCatalogInstPermission() {
        return catalogInstPermission;
    }

    public void setCatalogInstPermission(String catalogInstPermission) {
        this.catalogInstPermission = catalogInstPermission;
    }
    public String getXlinkCourse() {
        return xlinkCourse;
    }

    public void setXlinkCourse(String xlinkCourse) {
        this.xlinkCourse = xlinkCourse;
    }
}
