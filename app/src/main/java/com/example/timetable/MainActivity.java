package com.example.timetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.ToolbarMain);
        listview = (ListView)findViewById(R.id.lvMain);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Timetable App");
    }
    private void setupListView(){
        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,title,description);
        listview.setAdapter(simpleAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :{
                        Intent intent = new Intent(MainActivity.this,WeekActivity.class);
                        //Intent intent = new Intent(getApplicationContext(),WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1 :{
                        Intent intent = new Intent(MainActivity.this,SubjectActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2 :{
                        Intent intent = new Intent(MainActivity.this,FacultyActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3 :{
                        Intent intent = new Intent(getApplicationContext(),WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }
    public class SimpleAdapter extends BaseAdapter{
        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title,description;
        private String[] titleArray,descriptionArray;
        private ImageView imageView;

        /*private void setupListView(){
            String[] title = getResources().getStringArray(R.array.Main);
        }*/

        public SimpleAdapter(Context context, String[] title, String[] description){
            mContext=context;
            titleArray=title;
            descriptionArray=description;
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
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item,null);
            }
            title = (TextView)convertView.findViewById(R.id.tvMain);
            description = (TextView)convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView)convertView.findViewById(R.id.ivMain);

            title.setText(titleArray[position]);
            description.setText(descriptionArray[position]);

            if(titleArray[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.timetable);
            }else if(titleArray[position].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.book);
            }else if(titleArray[position].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.contact);
            }else {
                imageView.setImageResource(R.drawable.play);
            }
            return convertView;
        }
    }
}