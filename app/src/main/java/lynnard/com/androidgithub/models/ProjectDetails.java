package lynnard.com.androidgithub.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ProjectDetails implements Serializable {
    private int id;
    private String name;
    private String ownerName;
    private String description;
    private String language;
    private int forksCount;

    public ProjectDetails(int id, String name, String ownerName, String description, String language, int forksCount) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.description = description;
        this.language = language;
        this.forksCount = forksCount;
    }

    protected ProjectDetails(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.ownerName = in.readString();
        this.description = in.readString();
        this.language = in.readString();
        this.forksCount = in.readInt();
    }

    public static final Parcelable.Creator<ProjectDetails> CREATOR = new Parcelable.Creator<ProjectDetails>() {
        @Override
        public ProjectDetails createFromParcel(Parcel in) {
            return new ProjectDetails(in);
        }

        @Override
        public ProjectDetails[] newArray(int size) {
            return new ProjectDetails[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }
}
