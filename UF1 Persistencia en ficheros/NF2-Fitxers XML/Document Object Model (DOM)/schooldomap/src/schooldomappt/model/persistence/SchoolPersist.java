package schooldomappt.model.persistence;

import schooldomappt.model.School;

public interface SchoolPersist {
    public School load();

    public void save( School school );
}