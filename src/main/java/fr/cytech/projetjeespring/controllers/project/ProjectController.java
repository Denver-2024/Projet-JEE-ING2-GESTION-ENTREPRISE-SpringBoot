package fr.cytech.projetjeespring.controllers.project;

import fr.cytech.projetjeespring.dtos.ProjectFormDTO;
import fr.cytech.projetjeespring.entities.Project;
import fr.cytech.projetjeespring.mappers.ProjectMapper;
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db-test/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final DepartmentService departmentService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public String listProjects(@ModelAttribute Project searchProbe, Model model) {
        model.addAttribute("projects", projectService.findByExample(searchProbe));
        return "projects/list";
    }

    @GetMapping("/edit")
    public String editProject(@RequestParam(required = false) Integer id, Model model) {
        ProjectFormDTO dto;

        if (id != null) {
            Project existing = projectService.findById(id);
            dto = projectMapper.toDto(existing);
        } else {
            dto = new ProjectFormDTO();
        }

        model.addAttribute("projectForm", dto);
        model.addAttribute("departments", departmentService.findAll());
        return "projects/form";
    }

    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("projectForm") ProjectFormDTO dto,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "projects/form";
        }

        Project project;
        if (dto.getId() != null) {
            project = projectService.findById(dto.getId());
            projectMapper.updateEntityFromDto(dto, project);
        } else {
            project = projectMapper.toEntity(dto);
        }

        projectService.save(project);
        return "redirect:/db-test/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Integer id) {
        projectService.delete(id);
        return "redirect:/db-test/projects";
    }
}