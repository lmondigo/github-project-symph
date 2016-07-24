package lynnard.com.androidgithub.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.helper.HTTPDataHandler;
import lynnard.com.androidgithub.models.ProjectDetails;

public class MainActivity extends AppCompatActivity {

    private final String FULL_URL = "https://api.github.com/users/";

    private Button searchUser;
    private EditText etUser;
    private ProgressDialog pdLoading;
    private String username;

    public List<ProjectDetails> projectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchUser = (Button) findViewById(R.id.bSearchUser);
        etUser = (EditText) findViewById(R.id.etUser);
        pdLoading = new ProgressDialog(this);
        pdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdLoading.setTitle("Connecting");
        pdLoading.setMessage("Please wait...");

        projectList.clear();

        searchUser.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = etUser.getText().toString();
                String link = FULL_URL + username + "/repos";
                projectList.clear();
                pdLoading.show();
                MyAsyncTask getData = new MyAsyncTask();
                getData.execute(link);
            }
        });
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings){
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

            try {
                // Get the full HTTP Data as JSON
                JSONArray jsonArray;

                if (stream.charAt(0) == ('[')) {
                    jsonArray = new JSONArray(stream);

                    List<ProjectDetails> projects = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ProjectDetails details =
                                convertToProjectDetails(jsonArray.getJSONObject(i));
                        projects.add(details);
                    }

                    if (projects.size() > 0) {
                        Intent i = new Intent(getApplicationContext(), UserDetailsActivity.class);
                        i.putExtra("username", username);
                        i.putExtra("projects", (Serializable) projects);
                        pdLoading.dismiss();
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "User has no projects", Toast.LENGTH_LONG).show();
                        projectList.clear();
                        pdLoading.dismiss();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                projectList.clear();
                pdLoading.dismiss();
            }
        }
    }

    private ProjectDetails convertToProjectDetails(JSONObject jsonObject) {
        try {
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String ownerName = jsonObject.getJSONObject("owner").getString("login");
            String description = jsonObject.getString("description");
            String language = jsonObject.getString("language");
            int forksCount = jsonObject.getInt("forks_count");

            return new ProjectDetails(id, name, ownerName,
                    description, language, forksCount);
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return null;
    }
}