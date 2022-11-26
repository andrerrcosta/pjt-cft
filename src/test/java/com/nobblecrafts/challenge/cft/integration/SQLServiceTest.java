package com.nobblecrafts.challenge.cft.integration;

import com.nobblecrafts.challenge.cft.dto.DefaultResponse;
import com.nobblecrafts.challenge.cft.dto.Employee;
import com.nobblecrafts.challenge.cft.entity.EmployeeEntity;
import com.nobblecrafts.challenge.cft.entity.PositionEntity;
import com.nobblecrafts.challenge.cft.entity.SellingEntity;
import com.nobblecrafts.challenge.cft.repository.EmployeeJpaRepository;
import com.nobblecrafts.challenge.cft.repository.PositionJpaRepository;
import com.nobblecrafts.challenge.cft.repository.SellingJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.MethodName.class)
@Slf4j
public class SQLServiceTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;
    @Autowired
    private SellingJpaRepository sellingJpaRepository;
    @Autowired
    private PositionJpaRepository positionJpaRepository;
    private static final String URL = "/solution/sql/";

    @Test
    @Transactional
    public void B_testTotalPayment() {
        var uri = UriComponentsBuilder.fromUriString(URL + 1)
                .queryParam("employees", "1,2,3,4,5,6")
                .queryParam("month", 12)
                .queryParam("year", 2021)
                .toUriString();
        log.info("\n\nURI: {}\n\n", uri);
        log.info("\n\nSellings: {}\n\n", sellingJpaRepository.findAll());
        var res = testRestTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                BigDecimal.class);
        log.info("\n\nB_testTotalPayment, Response: {}, {}\n\n", res.getStatusCode(), res.getBody());
        Assertions.assertTrue(res.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(res.getBody(), new BigDecimal("83380.00"));
    }

    @Test
    public void C_totalSalaries() {
        var uri =  UriComponentsBuilder.fromUriString(URL + 2)
                .queryParam("employees", "1,2,3,4,5,6")
                .queryParam("month", 12)
                .queryParam("year", 2021)
                .toUriString();
        log.info("\n\nURI: {}\n\n", uri);
        var res = testRestTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                BigDecimal.class);
        log.info("\n\nC_totalSalaries, Response: {}, {}\n\n", res.getStatusCode(), res.getBody());
        Assertions.assertTrue(res.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(res.getBody(), new BigDecimal("78000.00"));
    }

    @Test
    public void D_totalBenefits() {
        var uri =  UriComponentsBuilder.fromUriString(URL + 3)
                .queryParam("employees", "1,2,3,4,5,6")
                .queryParam("month", 12)
                .queryParam("year", 2021)
                .toUriString();
        log.info("\n\nURI: {}\n\n", uri);
        var res = testRestTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                BigDecimal.class);
        log.info("\n\nD_totalBenefits, Response: {}, {}\n\n", res.getStatusCode(), res.getBody());
        Assertions.assertTrue(res.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(res.getBody(), new BigDecimal("5380.00"));
    }

    @Test
    public void E_higherPayment() {
        var uri =  UriComponentsBuilder.fromUriString(URL + 4)
                .queryParam("employees", "1,2,3,4,5,6")
                .queryParam("month", 12)
                .queryParam("year", 2021)
                .toUriString();
        log.info("\n\nURI: {}\n\n", uri);
        var res = testRestTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                Employee.class);
        log.info("\n\nE_higherPayment, Response: {}, {}\n\n", res.getStatusCode(), res.getBody());
        Assertions.assertTrue(res.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(res.getBody().name().equalsIgnoreCase("Juliana Alves"));
    }

    @Test
    public void F_higherBenefits() {
        var uri =  UriComponentsBuilder.fromUriString(URL + 5)
                .queryParam("employees", "1,2,3,4,5,6")
                .queryParam("month", 12)
                .queryParam("year", 2021)
                .toUriString();
        log.info("\n\nURI: {}\n\n", uri);
        var res = testRestTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                Employee.class);
        log.info("\n\nF_higherBenefits, Response: {}, {}\n\n", res.getStatusCode(), res.getBody());
        Assertions.assertTrue(res.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(res.getBody().name().equalsIgnoreCase("Ana Silva"));
    }

    @Test
    public void G_bestSeller() {
        var uri =  UriComponentsBuilder.fromUriString(URL + 6)
                .queryParam("employees", "1,2,3,4,5,6")
                .queryParam("month", 12)
                .queryParam("year", 2021)
                .toUriString();
        log.info("\n\nURI: {}\n\n", uri);
        var res = testRestTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                Employee.class);
        log.info("\n\nG_bestSeller, Response: {}, {}\n\n", res.getStatusCode(), res.getBody());
        Assertions.assertTrue(res.getStatusCode().is2xxSuccessful());
        Assertions.assertTrue(res.getBody().name().equalsIgnoreCase("Ana Silva"));
    }


    private List<EmployeeEntity> getAllEmployees() {
        return employeeJpaRepository.findAll();
    }

    private List<SellingEntity> getAllSellings() {
        return sellingJpaRepository.findAll();
    }

    private List<PositionEntity> getAllPositions() {
        return positionJpaRepository.findAll();
    }
}
