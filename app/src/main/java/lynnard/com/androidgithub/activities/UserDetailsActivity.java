package lynnard.com.androidgithub.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.adapters.ProjectsAdapter;
import lynnard.com.androidgithub.models.ProjectCard;

/**
 * Created by Lynnard on 7/22/2016.
 */
public class UserDetailsActivity extends AppCompatActivity {
    public Context context;

    //private ImageView ivProfPic;    // for user picture
    private TextView tvUsername;
    private String username;

    private RecyclerView userDetailsRecycler;
    public GridLayoutManager layoutManagerGrid;
    public List<ProjectCard> projectList = new ArrayList<>();
    public ProjectsAdapter adapter;



    public UserDetailsActivity() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_activity);

        // Mapping the elements
        userDetailsRecycler = (RecyclerView) findViewById(R.id.project_list);
        tvUsername = (TextView) findViewById(R.id.tvUsername);


        // Setting the username
        username =  getIntent().getExtras().getString("USERNAME");
        tvUsername.setText(username);

        // Retrieving items for the list
        projectList = (List<ProjectCard>) getIntent().getSerializableExtra("PROJECT_LIST");

        // Showing the list
        showList();
    }

    private void showList() {
        adapter = new ProjectsAdapter(projectList, context);
        adapter.notifyDataSetChanged();
        userDetailsRecycler.setHasFixedSize(true);
        layoutManagerGrid = new GridLayoutManager(context, 1);
        userDetailsRecycler.setLayoutManager(layoutManagerGrid);
        userDetailsRecycler.setAdapter(adapter);
    }




}
