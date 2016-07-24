package lynnard.com.androidgithub.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lynnard.com.androidgithub.R;
import lynnard.com.androidgithub.models.ProjectCard;

/**
 * Created by Lynnard on 7/22/2016.
 */


public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsAdapterViewHolder> {

    private List<ProjectCard> projectsList;
    private Context context;

    public ProjectsAdapter(List<ProjectCard> projectsList, Context context) {
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
        ProjectCard card = projectsList.get(position);
        holder.tvProjectName.setText(card.getTitle());
        holder.tvDescription.setText(card.getDescription());
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public static class ProjectsAdapterViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvProjectName;
        protected TextView tvDescription;

        public ProjectsAdapterViewHolder(View v, final Context context) {
            super(v);

            tvProjectName = (TextView) v.findViewById(R.id.tvProjectName);
            tvDescription = (TextView) v.findViewById(R.id.tvDescription);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO open project
                }
            });
        }
    }

}
