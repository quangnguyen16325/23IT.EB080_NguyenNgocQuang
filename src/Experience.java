public class Experience extends Employee {
    public int expInYear;
    public String proSkill;

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public void ShowInfo() {
        super.ShowInfo();
        System.out.println("Experience in year: " + expInYear);
        System.out.println("Pro skill: " + proSkill);
    }
}




