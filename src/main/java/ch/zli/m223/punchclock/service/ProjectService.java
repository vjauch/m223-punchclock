package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.repository.ProjectRepository;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        if (!projectRepository.exists(Example.of(project))) {
            return projectRepository.saveAndFlush(project);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Crate project: Project '" + project.getName() + "' already exists!");
        }
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }
}
