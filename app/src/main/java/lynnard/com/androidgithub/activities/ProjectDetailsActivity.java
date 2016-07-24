package lynnard.com.androidgithub.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.models.ProjectDetails;

public class ProjectDetailsActivity extends AppCompatActivity {

    private ProjectDetails details;

    private TextView username;
    private TextView projectTitle;
    private TextView projectDesc;
    private TextView repoId;
    private TextView language;
    private TextView forks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        // Retrieve project details
        details = (ProjectDetails) getIntent().getSerializableExtra("details");

        username = (TextView) findViewById(R.id.lblUsername);
        projectTitle = (TextView) findViewById(R.id.lblProjectTitle);
        projectDesc = (TextView) findViewById(R.id.lblProjectDesc);
        repoId = (TextView) findViewById(R.id.lblRepoId);
        language = (TextView) findViewById(R.id.lblLanguage);
        forks = (TextView) findViewById(R.id.lblForks);
    }

    @Override
    protected void onStart() {
        super.onStart();

        username.setText(details.getOwnerName());
        projectTitle.setText(details.getName());

        projectDesc.setText(details.getDescription());
        if ( details.getDescription().equals("null") ) {
            projectDesc.setText("No description available");
        }

        repoId.setText(String.valueOf(details.getId()));

        language.setText(details.getLanguage());
        if ( details.getLanguage().equals("null") ) {
            language.setText("No language set");
        }

        forks.setText(String.valueOf(details.getForksCount()));
    }
}
