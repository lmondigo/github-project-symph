package lynnard.com.androidgithub.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.models.ProjectDetails;

public class ProjectDetailsActivity extends AppCompatActivity {

    private ProjectDetails details;

    private TextView tvUsername;
    private TextView tvProjectTitle;
    private TextView tvProjectDesc;
    private TextView tvRepoID;
    private TextView tvLanguage;
    private TextView forks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        // Retrieve project details
        details = (ProjectDetails) getIntent().getSerializableExtra("details");

        tvUsername = (TextView) findViewById(R.id.lblUsername);
        tvProjectTitle = (TextView) findViewById(R.id.lblProjectTitle);
        tvProjectDesc = (TextView) findViewById(R.id.lblProjectDesc);
        tvRepoID = (TextView) findViewById(R.id.lblRepoId);
        tvLanguage = (TextView) findViewById(R.id.lblLanguage);
        forks = (TextView) findViewById(R.id.lblForks);
    }

    @Override
    protected void onStart() {
        super.onStart();

        tvUsername.setText(details.getOwnerName());
        tvProjectTitle.setText("Name: " + details.getName());

        tvProjectDesc.setText("Description: " + details.getDescription());
        if ( details.getDescription().equals("null") ) {
            tvProjectDesc.setText("Description: n/a");
        }


        tvRepoID.setText("ID: " + String.valueOf(details.getId()));

        tvLanguage.setText("Language: " + details.getLanguage());
        if ( details.getLanguage().equals("null") ) {
            tvLanguage.setText("Language: n/a");
        }

        forks.setText("Forks: " + String.valueOf(details.getForksCount()));
    }
}
