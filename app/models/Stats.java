package models;
import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity
public class Stats extends Model {
    private double bmi;
    private String bmiCatagory;

    public Stats(double bmi, String bmiCatagory) {
        this.bmi = bmi;
        this.bmiCatagory = bmiCatagory;
    }

    public double getBmi() {
        return bmi;
    }

    public String getBmiCatagory() {
        return bmiCatagory;
    }

    public String getWeightIndicatorCSS() {
        if (bmi < 16 || bmi >= 35)
            return  "red";
        else if (bmi >= 16 && bmi < 18.5 || bmi >= 25 && bmi < 30)
            return "yellow";
        else if (bmi >= 18.5 && bmi < 25)
            return "green";
        else return "orange";
    }




}

