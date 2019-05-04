package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends User
{
    private String gender;
    private String address;
    private float height;
    private float startWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessmentList = new ArrayList<Assessment>();

    public Member(String name, String email, String password, String role, String gender, String address, float height, float startWeight)
    {
        super(name, email, password, role);
        this.gender = gender;
        this.address = address;
        this.height = height;
        this.startWeight = startWeight;
    }

    public Assessment getLatestAssessment() {
        if (assessmentList.size() > 0)
            return assessmentList.get(assessmentList.size()-1);
        else return null;
    }

    public void addAssessment(Assessment assessment) {
        assessmentList.add(assessment);
    }

    public void removeAssessment(Assessment assessment) {
        assessmentList.remove(assessment);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        this.startWeight = startWeight;
    }

    public List<Assessment> getAssessmentList() {
        return assessmentList;
    }

    public void setAssessmentList(List<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

}