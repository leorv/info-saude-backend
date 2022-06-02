package com.infosaude.infosaude.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.infosaude.infosaude.entities.VaccineTaken;
import com.infosaude.infosaude.services.VaccineTakenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vaccines-taken")
public class VaccineTakenController {
    
    public VaccineTakenService vaccinetakenService;

    public VaccineTakenController(VaccineTakenService vaccinetakenService) {
        this.vaccinetakenService = vaccinetakenService;
    }

    @PostMapping("/create")
    public String createVaccineTaken(@RequestBody VaccineTaken vaccinetaken) throws InterruptedException, ExecutionException {
        return vaccinetakenService.createVaccineTaken(vaccinetaken);
    }

    @GetMapping("/")
    public List<VaccineTaken> getAllVaccineTakens() throws InterruptedException, ExecutionException {
        return vaccinetakenService.getAllVaccineTakens();
    }

    @GetMapping("/{id}")
    public VaccineTaken getVaccineTakenById(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        return vaccinetakenService.getVaccineTakenById(id);
    }

    @GetMapping("/getVaccineTakensByType/{type}")
    public List<VaccineTaken> getVaccineTakenByType(@PathVariable(value = "type") String type)
            throws InterruptedException, ExecutionException {
        return vaccinetakenService.getVaccineTakensByType(type);
    }

    @GetMapping("/getByStudentId/{studentId}")
    // public VaccineTaken[] getVaccineTakenByStudentId(@RequestParam int studentId) throws
    // InterruptedException, ExecutionException {
    public List<VaccineTaken> getVaccineTakenByStudentId(
            @PathVariable(value = "studentId") String studentId) throws InterruptedException, ExecutionException {
        return vaccinetakenService.getVaccineTakensByStudentId(studentId);
    }

    @PutMapping("/update")
    // public String updateVaccineTaken(@RequestParam VaccineTaken vaccinetaken) throws
    // InterruptedException, ExecutionException {
    public String updateVaccineTaken(@RequestBody VaccineTaken vaccinetaken) throws InterruptedException, ExecutionException {
        return vaccinetakenService.updateVaccineTaken(vaccinetaken);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVaccineTaken(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        return vaccinetakenService.deleteVaccineTaken(id);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndPoint() {
        return ResponseEntity.ok("Test get end-point is working.");
    }
}
