package Service;

import Model.Parent;
import Repository.ParentRepository;

import java.util.List;

public class ParentService extends Service.Abstract<Parent, Integer> {
    public ParentService() {
        super(new ParentRepository(Parent.class));
    }

    public List<Parent> searchByLastname(String keyword) {
        return ((ParentRepository) repository).searchByLastname(keyword);
    }

    public List<Parent> searchByName(String keyword) {
        return ((ParentRepository) repository).searchByName(keyword);
    }

    public List<Parent> searchBySurname(String keyword) {
        return ((ParentRepository) repository).searchBySurname(keyword);
    }

    public List<Parent> searchByAddress(String keyword) {
        return ((ParentRepository) repository).searchByAddress(keyword);
    }

    public List<Parent> searchByRelationDegree(String keyword) {
        return ((ParentRepository) repository).searchByRelationDegree(keyword);
    }
}
