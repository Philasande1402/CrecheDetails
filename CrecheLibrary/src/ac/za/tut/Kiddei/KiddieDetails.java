package ac.za.tut.Kiddei;

public class KiddieDetails {
    
    private String name;
    private String selectedGender;

    public KiddieDetails(String name, String selectedGender) {
        this.name = name;
        this.selectedGender = selectedGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    @Override
    public String toString() {
        return "Name : "+getName()+"\nGender : "+getSelectedGender();
    }
    
}
