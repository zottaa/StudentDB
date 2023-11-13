package Repository;

import Model.Parent;

import java.util.List;

public class ParentRepository extends Repository.Abstract<Parent, Integer> {
    public ParentRepository(Class<Parent> entityClass) {
        super(entityClass);
    }

    public List<Parent> searchByLastname(String keyword) {
        return searchByField("lastname", keyword);
    }

    public List<Parent> searchByName(String keyword) {
        return searchByField("name", keyword);
    }

    public List<Parent> searchBySurname(String keyword) {
        return searchByField("surname", keyword);
    }

    public List<Parent> searchByAddress(String keyword) {
        return searchByField("address", keyword);
    }

    public List<Parent> searchByRelationDegree(String keyword) {
        return searchByField("relationDegree", keyword);
    }
}
