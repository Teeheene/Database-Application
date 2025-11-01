package model;
import java.util.ArrayList;

public class CoachManagement {
    private ArrayList<Coach> coachList;

    public CoachManagement() {
        coachList = new ArrayList<Coach>();
    }

    public void addCoach(Coach coach) {
        coachList.add(coach);
    }

    public void deleteCoach(Coach coach) {
        coachList.remove(coach);
    }

    public Coach updateCoach(Coach oldCoach, Coach newCoach) {
        Coach reference = searchCoach("id", String.valueOf(oldCoach.getCoachID()));
        reference.update(newCoach);
        return reference;
    }

    public Coach searchCoach(String category, String key) {
        for(Coach coach : coachList) {
            if(coach == null) {
                continue;
            }

            switch(category) {
                case "id":
                    if(String.valueOf(coach.getCoachID()).equalsIgnoreCase(key))
                        return coach;
                    break;
                case "name":
                    if(coach.getFirstName().equalsIgnoreCase(key) ||
                       coach.getMiddleName().equalsIgnoreCase(key) ||
                       coach.getLastName().equalsIgnoreCase(key))
                        return coach;
                    break;
            }
        }

        return null;
    }
}
