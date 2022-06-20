package com.testserver.test.controller;

import com.testserver.test.dto.*;
import com.testserver.test.model.Employees;
import com.testserver.test.model.Feedback;
import com.testserver.test.model.Job;
import com.testserver.test.model.Location;
import com.testserver.test.repository.EmployeeRep;
import com.testserver.test.repository.FeedbackRep;
import com.testserver.test.repository.JobRep;
import com.testserver.test.repository.LocationRep;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final EmployeeRep employeeRep;
    private final FeedbackRep feedbackRep;
    private final JobRep jobRep;
    private final LocationRep locationRep;

    @GetMapping(path = "/employee")
    @Transactional
    public ResponseEntity<List<EmployeeResponseDTO>> getImployees(){
        var employees = employeeRep.findAll().stream().map(this::createEmployeeDto).collect(Collectors.toList());
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(path = "/location")
    @Transactional
    public ResponseEntity<List<LocationDTO>> getLocations(){
        var locations = locationRep.findAll().stream().map(this::createLocationResponse).collect(Collectors.toList());
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    @GetMapping(path = "/job")
    @Transactional
    public ResponseEntity<List<JobDTO>> getjobslist(){
        var jobs = jobRep.findAll().stream().map(this::createJobResponse).collect(Collectors.toList());
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{id}")
    @Transactional
    public ResponseEntity<EmployeeResponseDTO> getImployees(@PathVariable("id") long id){
        var employees = employeeRep.findById(id);
        if (employees.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.createEmployeeDto(employees.get()), HttpStatus.OK);
    }

    @PutMapping(path = "/employee/{id}")
    @Transactional
    public ResponseEntity<EmployeeResponseDTO> likeImployee(@PathVariable("id") long id){
        var employees = employeeRep.findById(id);
        if (employees.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        employees.get().setLiked(employees.get().getLiked()+1);
        employeeRep.save(employees.get());
        return new ResponseEntity<>(this.createEmployeeDto(employees.get()), HttpStatus.OK);
    }

    @PostMapping(path = "/feedback")
    @Transactional
    public ResponseEntity<FeedbackResponseDTO> submitFeedback(@RequestBody FeedbackRequestDTO feedbackRequest){

        var response = this.createFeedbackResponse(feedbackRep.save(this.createFeedbackFromRequest(feedbackRequest)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(path = "/seed")
    @Transactional
    public ResponseEntity<String> seed(){
        var l1 = new Location();
        l1.setName("USA");
        locationRep.save(l1);
        var l2 = new Location();
        l2.setName("EUR");
        locationRep.save(l2);
        var l3 = new Location();
        l3.setName("GEO");
        locationRep.save(l3);

        var j1 = jobRep.save(new Job("BUilder"));
        var j2 = jobRep.save(new Job("Frontend"));
        var j3 = jobRep.save(new Job("Backend"));
        var e1 = new Employees();
        e1.setJob_id(j1.getId());
        e1.setDescription("Builder");
        e1.setName("LABODAROMAN");
        e1.setLiked(0);
        e1.setLocation_id(l1.getId());
        e1.setAvatar("/uploads/1.jpeg");
        employeeRep.save(e1);
        var e2 = new Employees();
        e2.setJob_id(j2.getId());
        e2.setDescription("Frontender");
        e2.setName("NONAME");
        e2.setLiked(0);
        e2.setLocation_id(l2.getId());
        e2.setAvatar("/uploads/2.png");
        employeeRep.save(e2);
        var e3 = new Employees();
        e3.setJob_id(j3.getId());
        e3.setDescription("Backend Dev");
        e3.setName("BIGBRO");
        e3.setLiked(0);
        e3.setLocation_id(l2.getId());
        e3.setAvatar("/uploads/3.png");
        employeeRep.save(e3);
        return new ResponseEntity<>("seeded",HttpStatus.OK);
    }

    private EmployeeResponseDTO createEmployeeDto(Employees employees){
        var empResp = new EmployeeResponseDTO();

        empResp.setAvatar(employees.getAvatar());
        empResp.setDescription(employees.getDescription());
        empResp.setId(employees.getId());
        empResp.setLiked(employees.getLiked());
        empResp.setJob_id(employees.getJob_id());
        empResp.setLocation_id(employees.getLocation_id());
        empResp.setName(employees.getName());
        return empResp;
    }
    private FeedbackResponseDTO createFeedbackResponse(Feedback employees){
        return new FeedbackResponseDTO(){{
            setId(employees.getId());
            setName(employees.getName());
            setEmail(employees.getEmail());
            setMessage(employees.getMessage());
        }};
    }
    private Feedback createFeedbackFromRequest(FeedbackRequestDTO employeesRequest){
        var feedback = new Feedback();
        feedback.setEmail(employeesRequest.getEmail());
        feedback.setMessage(employeesRequest.getMessage());
        feedback.setName(employeesRequest.getName());
        return feedback;
    }
    private LocationDTO createLocationResponse(Location location){
        return new LocationDTO(){{
           setId(location.getId());
           setName(location.getName());
        }};
    }
    private JobDTO createJobResponse(Job job){
        return new JobDTO(){{
           setId(job.getId());
           setName(job.getName());
        }};
    }
}
