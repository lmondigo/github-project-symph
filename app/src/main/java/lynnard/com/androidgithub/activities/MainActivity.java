package lynnard.com.androidgithub.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import lynnard.com.androidgithub.helper.HTTPDataHandler;
import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.models.ProjectCard;

public class MainActivity extends AppCompatActivity {

    private final String FULL_URL = "https://api.github.com/users/";

    private Button searchUser;
    private EditText etUser;
    private ProgressDialog pdLoading;
    private String username;

    public List<ProjectCard> projectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchUser = (Button) findViewById(R.id.bSearchUser);
        etUser = (EditText) findViewById(R.id.etUser);
        pdLoading = new ProgressDialog(this);
        pdLoading.setMax(100);
        pdLoading.setProgressStyle(ProgressDialog.STYLE_SPINNER);

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
            //String stream;
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
                    JSONArray jArray;
                    JSONObject jObject;
                    JSONObject response;
                    JSONObject data;

                    if (stream.charAt(0) == ('[')) {
                        jArray = new JSONArray(stream);

                        String projectName;
                        String desc;


                        if (jArray.length() > 0) {
                            for (int i = 0; i < jArray.length(); i++) {
                                data = jArray.getJSONObject(i);
                                projectName = data.getString("name");
                                desc = data.getString("description");

                                if (desc.equals("null"))
                                    desc = "";

                                projectList.add(new ProjectCard(projectName, desc));
                            }
                            Intent i = new Intent(getApplicationContext(), UserDetailsActivity.class);
                            i.putExtra("PROJECT_LIST", (Serializable) projectList);
                            i.putExtra("USERNAME", (Serializable) username.toString());
                            pdLoading.dismiss();
                            startActivity(i);

                        } else {
                            Toast.makeText(getApplicationContext(), "User has no projects", Toast.LENGTH_LONG).show();
                            projectList.clear();
                            pdLoading.dismiss();
                        }

                    } else {
                        jObject = new JSONObject(stream);
                        response = jObject.getJSONObject("message");


                        if (response.equals("Not Found")) {
                            Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_LONG).show();
                            projectList.clear();
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
}

