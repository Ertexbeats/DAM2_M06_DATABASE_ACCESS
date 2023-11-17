package schooldomappt.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private String tutor;
    private String curriculum;
    private List< Student > students;

    public Group() {
    }

    public Group( String name, String tutor, String curriculum, List< Student > students ) {
        this.name = name;
        this.tutor = tutor;
        this.curriculum = curriculum;
        this.students = students;
    }

    public Group( String name, String tutor, String curriculum ) {
        this.name = name;
        this.tutor = tutor;
        this.curriculum = curriculum;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor( String tutor ) {
        this.tutor = tutor;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum( String curriculum ) {
        this.curriculum = curriculum;
    }

    public List< Student > getStudents() {
        return students;
    }

    public void setStudents( List< Student > students ) {
        this.students = students;
    }

    public void addStudent( Student student ) {
        this.students.add( student );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Group{" );
        sb.append( "name=" ).append( name );
        sb.append( ", tutor=" ).append( tutor );
        sb.append( ", curriculum=" ).append( curriculum );
        sb.append( ", students=" ).append( students );
        sb.append( '}' );
        return sb.toString();
    }
}