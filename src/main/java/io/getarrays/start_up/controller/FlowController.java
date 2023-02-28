package io.getarrays.start_up.controller;

import io.getarrays.start_up.entity.Flow;
import io.getarrays.start_up.entity.User;
import io.getarrays.start_up.payload.FlowDto;
import io.getarrays.start_up.repository.FlowRepository;
import io.getarrays.start_up.security.CurrentUser;
import io.getarrays.start_up.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flow")
public class FlowController {


    @Autowired
    FlowRepository flowRepository;

    @Autowired
    FlowService flowService;


    @PostMapping("/add")
    public HttpEntity<?> addFlow(@RequestBody FlowDto flowDto, @CurrentUser User user){
      return flowService.addFlow(flowDto,user);
    };

    @GetMapping("/get")
    public HttpEntity<?> getFlow(){
        List<Flow> allFlow = flowRepository.findAll();
        return ResponseEntity.status(202).body(allFlow);
    }

    @GetMapping("/user_flow/{userId}")
    public HttpEntity<?> getByUserId(@PathVariable Long userId){
        return flowService.getByUserId(userId);
    }






}
