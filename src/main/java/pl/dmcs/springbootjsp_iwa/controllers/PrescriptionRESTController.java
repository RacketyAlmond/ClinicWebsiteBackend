package pl.dmcs.springbootjsp_iwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.springbootjsp_iwa.model.Prescription;
import pl.dmcs.springbootjsp_iwa.model.Visit;
import pl.dmcs.springbootjsp_iwa.repository.PrescriptionRepository;
import pl.dmcs.springbootjsp_iwa.repository.VisitRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/prescriptions")
public class PrescriptionRESTController {

    private final PrescriptionRepository prescriptionRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public PrescriptionRESTController(PrescriptionRepository prescriptionRepository, VisitRepository visitRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.visitRepository = visitRepository;
    }

    @GetMapping
    public List<Prescription> findAllPrescriptions() {
        return prescriptionRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<Prescription> addPrescription(@RequestParam Long visitId, @RequestBody Prescription prescription) {
        Visit visit = visitRepository.findById(visitId).orElseThrow(() -> new RuntimeException("Visit not found"));
        prescription.setVisit(visit);
        prescriptionRepository.save(prescription);
        return new ResponseEntity<>(prescription, HttpStatus.CREATED);
    }



    @GetMapping("/username")
    public ResponseEntity<String> getUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<String> getEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Prescription> deletePrescription(@PathVariable("id") long id) {
        Prescription prescription = prescriptionRepository.findById(id);
        if (prescription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        prescriptionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@RequestParam Long visitId, @RequestBody Prescription prescription, @PathVariable("id") long id) {
        Visit visit = visitRepository.findById(visitId).orElseThrow(() -> new RuntimeException("Visit not found"));
        prescription.setVisit(visit);
        prescription.setId(id);
        prescriptionRepository.save(prescription);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Prescription> updatePartOfPrescription(@RequestBody Map<String, Object> updates, @PathVariable("id") long id) {
        Prescription prescription = prescriptionRepository.findById(id);
        if (prescription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(prescription, updates);
        return new ResponseEntity<>(prescription, HttpStatus.NO_CONTENT);
    }

    private void partialUpdate(Prescription prescription, Map<String, Object> updates) {
        if (updates.containsKey("telephone")) {
            prescription.setTelephone((String) updates.get("telephone"));
        }
        if (updates.containsKey("note")) {
            prescription.setNote((String) updates.get("note"));
        }

        prescriptionRepository.save(prescription);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePrescriptions() {
        prescriptionRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
