package com.example.timetable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SubjectDetails extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setupUIViews();
        initToolbar();
        setupListView();
    }
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubjectDetails);
        listView = (ListView)findViewById(R.id.lvSubjectDetails);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupListView(){
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF,null);
        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.Titles);

        if(subject_selected.equalsIgnoreCase("DistributedDatabases")){
            syllabus = getResources().getStringArray(R.array.DistributedDatabases);
        }else if(subject_selected.equalsIgnoreCase("SystemAdministration")){
            syllabus = getResources().getStringArray(R.array.SystemAdministration);
        }else{
            syllabus = getResources().getStringArray(R.array.AndroidLab);
        }
        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this,titles,syllabus);
        listView.setAdapter(subjectDetailsAdapter);
    }
    public class SubjectDetailsAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title,syllabus;
        private String[] titleArray,syllabusArray;

        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus){
            mContext=context;
            titleArray=title;
            syllabusArray=syllabus;
            layoutInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item,null);
            }
            title = (TextView)convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = (TextView)convertView.findViewById(R.id.tvSyllabus);
            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);
            return convertView;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}