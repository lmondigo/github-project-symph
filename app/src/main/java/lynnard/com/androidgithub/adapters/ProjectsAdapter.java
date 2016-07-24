package lynnard.com.androidgithub.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import lynnard.com.androidgithub.BuildConfig;
import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.models.ProjectDetails;

/**
 * Created by Lynnard on 7/22/2016.
 */


public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsAdapterViewHolder> {

    private List<ProjectDetails> projectsList;
    private Context context;

    public ProjectsAdapter(List<ProjectDetails> projectsList, Context context) {
        this.projectsList = projectsList;
        this.context = context;
    }

    @Override
    public ProjectsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_card, parent, false);
        return new ProjectsAdapterViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(ProjectsAdapterViewHolder holder, int position) {
        final ProjectDetails project = projectsList.get(position);
        holder.tvProjectName.setText(project.getName());
        holder.tvDescription.setText(project.getDescription());
        if ( project.getDescription().equals("null") ) {
            holder.tvDescription.setText("No description available");
        }

        holder.layoutDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(BuildConfig.APPLICATION_ID,
                        BuildConfig.APPLICATION_ID + ".activities.ProjectDetailsActivity");
                intent.putExtra("details", project);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public static class ProjectsAdapterViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvProjectName;
        protected TextView tvDescription;
        protected LinearLayout layoutDetails;

        public ProjectsAdapterViewHolder(View v, final Context context) {
            super(v);
            tvProjectName = (TextView) v.findViewById(R.id.tvProjectName);
            tvDescription = (TextView) v.findViewById(R.id.tvDescription);
            layoutDetails = (LinearLayout) v.findViewById(R.id.layoutDetails);
        }
    }

}
