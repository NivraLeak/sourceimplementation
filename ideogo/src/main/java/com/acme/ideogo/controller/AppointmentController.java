package com.acme.ideogo.controller;

import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Skill;
import com.acme.ideogo.resource.AppointmentResource;
import com.acme.ideogo.resource.SaveAppointmentResource;
import com.acme.ideogo.resource.SaveSkillResource;
import com.acme.ideogo.resource.SkillResource;
import com.acme.ideogo.service.AppointmentService;
import com.acme.ideogo.service.SkillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "appointments", description = "the Appointments API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class AppointmentController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/projectSchedules/{projectScheduleId}/appointments")
    public Page<AppointmentResource> getAllAppointmentsByProjectScheduleId(
            @PathVariable(name = "projectScheduleId") Long projectScheduleId,
            Pageable pageable) {
        Page<Appointment> appointmentPage = appointmentService.getAllAppointmentsByProjectScheduleId (projectScheduleId, pageable);
        List<AppointmentResource> resources = appointmentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/projectSchedules/{projectScheduleId}/appointments/{appointmentId}")
    public AppointmentResource getSkillByIdAndTagId(@PathVariable(name = "projectScheduleId") Long projectScheduleId,
                                              @PathVariable(name = "appointmentId") Long skillId) {
        return convertToResource(appointmentService.getAppointmentsByIdAndTagId(skillId, projectScheduleId));
    }

    @PostMapping("/projectSchedules/{projectScheduleId}/skills")
    public AppointmentResource createAppointment(@PathVariable(name = "projectScheduleId") Long TagId,
                                     @Valid @RequestBody SaveAppointmentResource resource) {
        return convertToResource(appointmentService.createAppointment(TagId, convertToEntity(resource)));

    }

    @PutMapping("/projectSchedules/{projectScheduleId}/appointments/{appointmentId}")
    public AppointmentResource updateSkill(@PathVariable(name = "projectScheduleId") Long projectScheduleId,
                                     @PathVariable(name = "appointmentId") Long appointmentId,
                                     @Valid @RequestBody SaveAppointmentResource resource) {
        return convertToResource(appointmentService.updateAppointment(projectScheduleId, appointmentId, convertToEntity(resource)));
    }

    @DeleteMapping("/projectSchedules/{projectScheduleId}/appointments/{appointmentId}")
    public ResponseEntity<?> deleteSkill(@PathVariable(name = "projectScheduleId") Long projectScheduleId,
                                         @PathVariable(name = "appointmentId") Long appointmentId) {
        return appointmentService.deleteAppointment(projectScheduleId, appointmentId);
    }

    private Appointment convertToEntity(SaveAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }

    private AppointmentResource convertToResource(Appointment entity) {
        return mapper.map(entity, AppointmentResource.class);
    }
}
