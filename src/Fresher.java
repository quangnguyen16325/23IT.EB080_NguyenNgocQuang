
public class Fresher extends Employee {
    private String graduation_date;
    private String graduation_rank;
    private String education;

    public String getGraduation_date() {
        return graduation_date;
    }

    public void setGraduation_date(String graduation_date) {
        this.graduation_date = graduation_date;
    }

    public String getGraduation_rank() {
        return graduation_rank;
    }

    public void setGraduation_rank(String graduation_rank) {
        this.graduation_rank = graduation_rank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public void ShowInfo() {
        super.ShowInfo();
        System.out.println("Graduation date: " + graduation_date);
        System.out.println("Graduation rank: " + graduation_rank);
        System.out.println("Education: " + education);
    }
}
