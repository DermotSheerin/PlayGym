package controllers;
import models.*;
import play.Logger;
import play.mvc.Controller;
import java.util.ArrayList;
import java.util.List;


public class Dashboard extends Controller
{
    public static void member(Long id) {
        Logger.info("Rendering Dashboard MEMBER()");
        Member member = null;
        User user = Accounts.getLoggedInUser();
        if (user.isMemberRole())
            member = Accounts.getLoggedInMember();
        else if (user.isTrainerRole())
            member = Accounts.getMember(id);

        double bmi = GymUtility.calculateBMI(member, member.getLatestAssessment());
        bmi = Math.round(bmi*100)/100.0d;
        String bmiCatagory = GymUtility.determineBMICategory(bmi);
        Stats stats = new Stats(bmi, bmiCatagory);
        boolean isTrainerRole = user.isTrainerRole();

        render("dashboard.html", member, stats, isTrainerRole);
    }

    public static void trainer() {
        Logger.info("Rendering Dashboard TRAINER ()");
        Trainer trainer = Accounts.getLoggedInTrainer();
        if (trainer == null)
            redirect("/login");
        List<Member> memberlist = Member.findAll();
        Logger.info("Rendering memberList" + memberlist);
        render("dashboard_trainer.html", trainer, memberlist);
    }

    public static void addComment(Long id, Long assessmentid, String comment)
    {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);

        // created an addAssessment method rather than adding an assessment directly into the arrayList from outside the class
        assessment.setComment(comment);
        member.save();
        Logger.info("Adding Comment" + comment);
        redirect("/dashboard/member/" + id);
    }

    public static void addAssessment(Float weight, Float chest, Float thigh, Float upperArm, Float waist, Float hips, String comment)
    {
        ArrayList<String> validationErrors = new ArrayList<String>();

        if(weight == null)
            validationErrors.add("Weight must be a valid Number");
        if(chest == null)
            validationErrors.add("Chest must be a valid Number");
        if(thigh == null)
            validationErrors.add("Thigh must be a valid Number");
        if(upperArm == null)
            validationErrors.add("UpperArm be a valid Number");
        if(waist == null)
            validationErrors.add("Waist must be a valid Number");
        if(hips == null)
            validationErrors.add("Hips must be a valid Number");

        if (validationErrors.size() == 0){
            Member member = Accounts.getLoggedInMember();
            Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment);
            // created an addAssessment method rather than adding an assessment directly into the arrayList from outside the class
            member.addAssessment(assessment);
            member.save();
            Logger.info("Adding Assessment");
            redirect("/dashboard/member");
        }
        else {
            String returnURL = "member";
            render("error.html", validationErrors, returnURL);
        }
    }

    public static void deleteAssessment(Long id, Long assessmentid)
    {
        Member member = Member.findById(id);
        User user = Accounts.getLoggedInUser();
        Assessment assessment = Assessment.findById(assessmentid);
        // created a removeAssessment method rather than removing an assessment directly from the arrayList from outside the class
        member.removeAssessment(assessment);
        member.save();
        assessment.delete();
        Logger.info("Deleting " + assessmentid);

        if (user.isTrainerRole())
            redirect("/dashboard/member/" + id);
        else redirect("/dashboard/member");
    }

    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        Logger.info ("Removing Member id = " + id);
        member.delete();
        redirect("/dashboard/trainer");

    }

    public void retrieveMember() {
        Member member = Accounts.getLoggedInMember();
        User user = Accounts.getLoggedInUser();
        render("updateMember.html", member, user);
}

    public void updateMember(String name, String email, String password, String role,String gender, String address, Float height, Float startWeight)
    {
        Member member = Accounts.getLoggedInMember();
        User user = Accounts.getLoggedInUser();

        ArrayList<String> validationErrors = new ArrayList<String>();

        if (height == null || height <= 0.0)
            validationErrors.add("Please enter a valid height in meters, height cannot be zero or contain letters");
        if (startWeight == null || startWeight <= 0.0)
            validationErrors.add("Please enter a valid weight in kgs, weight cannot be zero or contain letters");
        switch (gender) {
            case "M":
                break;
            case "F":
                break;
            case "m":   gender = gender.toUpperCase();
                break;
            case "f":   gender = gender.toUpperCase();
                break;
            default:    validationErrors.add("Gender must be in the format 'M' or 'F'");
        }

        if (validationErrors.size() == 0) {
            user.setName(name);
            user.setPassword(password);
            role = "member";            // hard coding the role to member
            member.setGender(gender);
            member.setAddress(address);
            member.setHeight(height);
            member.setStartWeight(startWeight);
            member.save();
            user.save();
            redirect("/dashboard/member");
        }
        else {
            String returnURL = "dashboard/memberSettings";
            render("error.html", validationErrors, returnURL);
        }
    }
}
