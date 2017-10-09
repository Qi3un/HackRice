package riceapp.hackrice.riceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 2017/9/24.
 */

public class Todo implements Parcelable {
    private String name;
    private Category category;
    private String date;
    private String startTime;
    private String endTime;
    private String description;

    public Todo(
            String name,
            Category category,
            String date,
            String startTime,
            String endTime,
            String description) {
        this.name = name;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static class TodoArrayAdapter extends ArrayAdapter<Todo> {
        private final AppCompatActivity context;
        private HashMap<Todo, Integer> mIdMap = new HashMap<Todo, Integer>();

        public TodoArrayAdapter (AppCompatActivity context, List<Todo> todos) {
            super(context, R.layout.list_view_card, todos);
            this.context = context;

            for (int i = 0; i < todos.size(); ++i) {
                mIdMap.put(todos.get(i), i);
            }
        }
        @Override
        public long getItemId(int position) {
            Todo item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Todo t = getItem(position);
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listViewCard = layoutInflater.inflate(R.layout.list_view_card, parent, false);
            TextView firstlineView = listViewCard.findViewById(R.id.list_view_card_firstLine);
            TextView secondlineView = listViewCard.findViewById(R.id.list_view_card_secondLine);
            firstlineView.setText(t.getName());
            secondlineView.setText(t.getDate());

            Log.d(Todo.TodoArrayAdapter.class.getName()+"Log", "getView in ArrayAdapter, position: "+position);
            Log.d(Todo.TodoArrayAdapter.class.getName()+"Log", "listViewCard: "+listViewCard);

            return listViewCard;
        }
    }

    public static class TodoItemOnClickListener implements ListView.OnItemClickListener {
        private AppCompatActivity context;
        private GoogleSignInAccount acct;
        public TodoItemOnClickListener(AppCompatActivity context, GoogleSignInAccount acct) {
            this.context = context;
            this.acct = acct;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final Todo item = (Todo) parent.getItemAtPosition(position);
            context.startActivityForResult(
                    new Intent(context, EditTodoActivity.class)
                            .putExtra("todo", item)
                            .putExtra("acct", acct)
                            .putExtra("requestCode", MainPage.EDIT_TODO),
                    MainPage.EDIT_TODO);
        }
    }

    public Todo(){}

    protected Todo(Parcel in) {
        name = in.readString();
        date = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }
    public String getStartTime() {return startTime; }
    public String getEndTime() { return endTime; }
}
