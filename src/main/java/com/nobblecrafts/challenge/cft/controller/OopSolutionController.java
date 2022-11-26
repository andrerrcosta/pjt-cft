package com.nobblecrafts.challenge.cft.controller;

import com.nobblecrafts.challenge.cft.dto.Employee;
import com.nobblecrafts.challenge.cft.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("solution/oop")
public class OopSolutionController {
    private final TaskService service;


    @Autowired
    public OopSolutionController(@Qualifier("onlyOopService") TaskService service) {
        this.service = service;
    }

    @GetMapping(value = "/1")
    public ResponseEntity<BigDecimal> totalPayment(@RequestParam Integer month,
                                                  @RequestParam Integer year,
                                                  @RequestParam Set<Long> employees) {
        return ResponseEntity.ok(service.totalPayment(employees, month, year));
    }

    @GetMapping(value = "/2")
    public ResponseEntity<BigDecimal> totalSalaries(@RequestParam Integer month,
                                    @RequestParam Integer year,
                                    @RequestParam Set<Long> employees) {
        return ResponseEntity.ok(service.totalSalaries(employees, month, year));
    }

    @GetMapping(value = "/3")
    public ResponseEntity<BigDecimal> totalBenefits(@RequestParam Integer month,
                                    @RequestParam Integer year,
                                    @RequestParam Set<Long> employees) {
        return ResponseEntity.ok(service.totalBenefits(employees, month, year));
    }

    @GetMapping(value = "/4")
    public ResponseEntity<Employee> higherPayment(@RequestParam Integer month,
                                                  @RequestParam Integer year,
                                                  @RequestParam Set<Long> employees) {
        return ResponseEntity.ok(service.higherPayment(employees, month, year));
    }

    @GetMapping(value = "/5")
    public ResponseEntity<Employee> higherBenefits(@RequestParam Integer month,
                                                 @RequestParam Integer year,
                                                 @RequestParam Set<Long> employees) {
        return ResponseEntity.ok(service.higherBenefits(employees, month, year));
    }

    @GetMapping(value = "/6")
    public ResponseEntity<Employee> bestSeller(@RequestParam Integer month,
                                             @RequestParam Integer year,
                                             @RequestParam Set<Long> employees) {
        return ResponseEntity.ok(service.bestSeller(employees, month, year));
    }


}
