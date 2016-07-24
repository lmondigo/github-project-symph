package lynnard.com.androidgithub.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Lynnard on 7/22/2016.
 */
public class ProjectCard implements Serializable{

    private String title;
    private String description;

    public ProjectCard(String title, String description) {
        this.description = description;
        this.title = title;
    }

    protected ProjectCard(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    public static final Parcelable.Creator<ProjectCard> CREATOR = new Parcelable.Creator<ProjectCard>() {
        @Override
        public ProjectCard createFromParcel(Parcel in) {
            return new ProjectCard(in);
        }

        @Override
        public ProjectCard[] newArray(int size) {
            return new ProjectCard[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
