package controllers;
import models.Trainer;
import models.User;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.ArrayList;

public class Accounts extends Controller
{
    public static void signup()
    {
        render("signup.html");
    }

    public static void login()
    {
        render("login.html");
    }

    public static void register(String name, String email, String password, String role, String gender, String address, Float height, Float startWeight) {
        Logger.info("Registering new user " + email);
        // hard coding the role to member for now as the trainers will be loaded via data.yml file initially

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
            role = "member";
            Member member = new Member(name, email, password, role, gender, address, height, startWeight);
            member.save();
            redirect("/");
        }

        else {
            String returnURL = "signup";
            render("error.html", validationErrors, returnURL);
        }
    }

    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);
        User user = User.findByEmail(email);
        Logger.info(">>>"+user);
        if ((user != null) && (user.checkPassword(password) == true)) {
            Logger.info("Authentication successful - User ID = " + user.id);
            session.put("logged_in_Userid", user.id);
            if (user.isMemberRole())
                redirect("/dashboard/member");
            else if (user.isTrainerRole())
                redirect("/dashboard/trainer");
            else
                redirect("/login");
        } else
            Logger.info("Authentication failed");
            redirect("/login");
    }

    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    public static User getLoggedInUser() {
        User user = null;
        if (session.contains("logged_in_Userid")) {
            String userId = session.get("logged_in_Userid");
            user = User.findById(Long.parseLong(userId));
        } return user;
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Userid")) {
            String userId = session.get("logged_in_Userid");
            member = Member.findById(Long.parseLong(userId));
        } else {
            login();
        }
        return member;
    }

    public static Member getMember(Long id)
    {
        return Member.findById(id);
    }

    public static Trainer getLoggedInTrainer()
    {
        Trainer trainer = null;
        if (session.contains("logged_in_Userid")) {
            String userId = session.get("logged_in_Userid");
            trainer = Trainer.findById(Long.parseLong(userId));
        } else {
            login();
        }
        return trainer;
    }


}
