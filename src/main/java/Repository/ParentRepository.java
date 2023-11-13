package Repository;

import Model.Parent;

import java.util.List;

public class ParentRepository extends Repository.Abstract<Parent, Integer> {
    public List<Parent> searchByLastname(String keyword) {
        return searchByField(Parent.class, "lastname", keyword);
    }

    public List<Parent> searchByName(String keyword) {
        return searchByField(Parent.class, "name", keyword);
    }

    public List<Parent> searchBySurname(String keyword) {
        return searchByField(Parent.class, "surname", keyword);
    }

    public List<Parent> searchByAddress(String keyword) {
        return searchByField(Parent.class, "address", keyword);
    }

    public List<Parent> searchByRelationDegree(String keyword) {
        return searchByField(Parent.class, "relationDegree", keyword);
    }
}
