package schooldomappt.model;

public class Model {
    private School school;

    public Model() {
        this.school = new School( "Institut Provençana" );
    }

    public Model( School school ) {
        this.school = school;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool( School school ) {
        this.school = school;
    }
}