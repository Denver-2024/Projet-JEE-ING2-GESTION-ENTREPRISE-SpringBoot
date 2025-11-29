package fr.cytech.projetjeespring.controllers.tests;

import fr.cytech.projetjeespring.entities.Project;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db-test/projects")
@RequiredArgsConstructor
public class ProjectCrudTest {

    private final ProjectService projectService;
    private final DepartmentService departmentService;

    @GetMapping
    public String listProjects(@ModelAttribute Project searchProbe, Model model) {
        model.addAttribute("projects", projectService.findByExample(searchProbe));
        return "projects/list";
    }

    @GetMapping("/edit")
    public String editProject(@RequestParam(required = false) Integer id, Model model) {
        Project project = (id != null) ? projectService.findById(id) : new Project();
        model.addAttribute("project", project);
        model.addAttribute("departments", departmentService.findAll());
        return "projects/form";
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/db-test/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Integer id) {
        projectService.delete(id);
        return "redirect:/db-test/projects";
    }
}
