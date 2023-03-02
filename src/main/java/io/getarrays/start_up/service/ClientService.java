package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.Client;
import io.getarrays.start_up.entity.Flow;
import io.getarrays.start_up.payload.ClientDto;
import io.getarrays.start_up.repository.ClientRepository;
import io.getarrays.start_up.repository.FlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FlowRepository flowRepository;

    public HttpEntity<?> addClientProduct(ClientDto clientDto) {
        Optional<Flow> optionalFlow = flowRepository.findById(clientDto.getFlow());
        if (optionalFlow.isPresent()){
        Client client = new Client(
                clientDto.getName(),
                clientDto.getPhoneNumber(),
                optionalFlow.get()
        );
        Client save = clientRepository.save(client);
            return ResponseEntity.status(202).body("Buyurtmangiz qabul qilindi sizga tez orada aloqaga chiqishadi");
        }else {
            return ResponseEntity.status(404).body("Hatolik yuz berdi qayta urunib ko'ring");
        }
    }


    public HttpEntity<?> getAllClientByFlowId(Long flowId) {
        List<Client> clients = clientRepository.findByFlowId(flowId);
        return ResponseEntity.status(202).body(clients);
    }
}
