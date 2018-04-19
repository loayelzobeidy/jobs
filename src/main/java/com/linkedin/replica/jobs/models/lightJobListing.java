package com.linkedin.replica.jobs.models;

import com.arangodb.entity.DocumentField;

public class lightJobListing {
    @DocumentField(DocumentField.Type.KEY)
    private int jobID;
    private String positionName;
    private String companyName ;
    private String companyID;
    private String companyProfilePictureURL;

    public lightJobListing(){};

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setCompanyProfilePictureURL(String companyProfilePictureURL) {
        this.companyProfilePictureURL = companyProfilePictureURL;
    }

    public int getJobID() {
        return jobID;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getCompanyProfilePictureURL() {
        return companyProfilePictureURL;
    }
}
