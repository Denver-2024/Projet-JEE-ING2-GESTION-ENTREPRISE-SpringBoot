package fr.cytech.projetjeespring.controllers.project;

import fr.cytech.projetjeespring.model.dtos.ProjectFormDTO;
import fr.cytech.projetjeespring.model.dtos.ProjectSummaryDTO;
import fr.cytech.projetjeespring.model.entities.Employee;
import fr.cytech.projetjeespring.model.entities.Project;
import fr.cytech.projetjeespring.model.mappers.ProjectMapper;
import fr.cytech.projetjeespring.repositories.ProjectRepository; // Direct repo access for custom queries
import fr.cytech.projetjeespring.services.DepartmentService;
import fr.cytech.projetjeespring.services.EmployeeService;
import fr.cytech.projetjeespring.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public String listProjects(@ModelAttribute Project searchProbe, Model model) {
        List<Project> entities = projectService.findByExample(searchProbe);
        List<ProjectSummaryDTO> dtos = projectMapper.toSummaryDtoList(entities);

        model.addAttribute("projects", dtos);
        return "projects/list";
    }

    @GetMapping("/my-projects")
    public String myProjects(Authentication auth, Model model) {
        Integer myId = Integer.parseInt(auth.getName());
        Employee me = employeeService.findById(myId);

        if (me.getDepartment() == null) {
            model.addAttribute("message", "You are not assigned to any department.");
            return "projects/list";
        }

        List<Project> myProjects = projectRepository.findByDepartmentId(me.getDepartment().getId());

        List<ProjectSummaryDTO> dtos = projectMapper.toSummaryDtoList(myProjects);
        model.addAttribute("projects", dtos);
        model.addAttribute("isMyProjects", true); // Flag to change title in JSP
        return "projects/list";
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
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

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
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
        return "redirect:/projects";
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Integer id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
}