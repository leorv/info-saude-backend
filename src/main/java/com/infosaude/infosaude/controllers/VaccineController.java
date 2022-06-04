package com.infosaude.infosaude.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.infosaude.infosaude.entities.Vaccine;
import com.infosaude.infosaude.services.VaccineService;

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
@RequestMapping(value = "vaccines")
public class VaccineController {
    public VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping("/create")
    public String createVaccine(@RequestBody Vaccine vaccine) throws InterruptedException, ExecutionException {
        return vaccineService.createVaccine(vaccine);
    }

    @GetMapping
    public List<Vaccine> getAllVaccines() throws InterruptedException, ExecutionException {
        return vaccineService.getAllVaccines();
    }

    @GetMapping("/{id}")
    public Vaccine getVaccineById(@PathVariable(value = "id") String id)
            throws InterruptedException, ExecutionException {
        return vaccineService.getVaccineById(id);
    }

    @GetMapping("/getByName/{name}")
    public List<Vaccine> getVaccineByName(@PathVariable(value = "name") String name)
            throws InterruptedException, ExecutionException {
        System.out.println(name);
        return vaccineService.getVaccineByName(name);
    }

    @PutMapping("/update")
    // public String updateVaccine(@RequestParam Vaccine vaccine) throws
    // InterruptedException, ExecutionException {
    public String updateVaccine(@RequestBody Vaccine vaccine) throws InterruptedException, ExecutionException {
        return vaccineService.updateVaccine(vaccine);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVaccine(@PathVariable(value = "id") String id) throws InterruptedException, ExecutionException {
        return vaccineService.deleteVaccine(id);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndPoint() {
        return ResponseEntity.ok("Test get vaccine end-point is working.");
    }
}
