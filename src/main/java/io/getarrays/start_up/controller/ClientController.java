package io.getarrays.start_up.controller;

import io.getarrays.start_up.payload.ClientDto;
import io.getarrays.start_up.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public HttpEntity<?> addClientProduct(@RequestBody ClientDto clientDto){
        return clientService.addClientProduct(clientDto);
    }

    @GetMapping("/get/{flowId}")
    public HttpEntity<?> getAllClientProductByFlowID(@PathVariable Long flowId){
        return clientService.getAllClientByFlowId(flowId);
    }
}
