package fr.cytech.projetjeespring.services;

import fr.cytech.projetjeespring.entities.Project;
import fr.cytech.projetjeespring.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    // jpp du crud ;w;
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }
}