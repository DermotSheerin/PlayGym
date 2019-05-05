package models;
import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Assessment extends Model
{
    private float weight;
    private float chest;
    private float thigh;
    private float upperArm;
    private float waist;
    private float hips;
    private String comment;

    public Assessment(float weight, float chest, float thigh, float upperArm, float waist, float hips, String comment)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

//    public float getChest() {
//        return chest;
//    }
//
//    public void setChest(float chest) {
//        this.chest = chest;
//    }
//
//    public float getThigh() {
//        return thigh;
//    }
//
//    public void setThigh(float thigh) {
//        this.thigh = thigh;
//    }
//
//    public float getUpperArm() {
//        return upperArm;
//    }
//
//    public void setUpperArm(float upperArm) {
//        this.upperArm = upperArm;
//    }
//
//    public float getWaist() {
//        return waist;
//    }
//
//    public void setWaist(float waist) {
//        this.waist = waist;
//    }
//
//    public float getHips() {
//        return hips;
//    }
//
//    public void setHips(float hips) {
//        this.hips = hips;
//    }
//
//    public String getComment() {
//        return comment;
//    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}