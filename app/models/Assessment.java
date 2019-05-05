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

    public void setComment(String comment) {
        this.comment = comment;
    }
}