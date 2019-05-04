package controllers;

import models.Assessment;
import models.Member;

public class GymUtility {

    public static Double calculateBMI(Member member, Assessment assessment) {
        if (assessment != null)
        return (double) assessment.getWeight() / Math.pow((double) member.getHeight(), 2);

        // if no assessment exists for the member then use the members start weight to calculate the BMI
        else return member.getStartWeight() / Math.pow((double) member.getHeight(), 2);
    }

    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < 16)
            return "SEVERELY UNDERWEIGHT";

        else if (bmiValue >= 16 && bmiValue < 18.5)
            return "UNDERWEIGHT";

        else if (bmiValue >= 18.5 && bmiValue < 25)
            return "NORMAL";

        else if (bmiValue >= 25 && bmiValue < 30)
            return "OVERWEIGHT";

        else if (bmiValue >= 30 && bmiValue < 35)
            return "MODERATELY OBESE";

        else if (bmiValue >= 35)
            return "SEVERELY OBESE";

        else return "INVALID";
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {         // is there stale data here????
        float bodyWeight;
        float currentWeight = assessment.getWeight();
        float heightInInches = member.getHeight() * (float) 39.37;
        float range = (float) 2.0;

        if (member.getGender().equals("M")) {
            bodyWeight = (float) 50.0;
        } else {
            bodyWeight = (float) 45.5;
        }

        float idealWeight = bodyWeight + ((heightInInches - 60) * (float) 2.3);

        // members height is above 60 inches
        if (heightInInches > 60) {
            return currentWeight >= (idealWeight - range) && currentWeight <= (idealWeight + range);
        }

        // members height is 60 inches or below
        else {
            return currentWeight >= (bodyWeight - range) && currentWeight <= (bodyWeight + range);
        }
    }


}