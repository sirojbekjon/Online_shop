package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.Flow;
import io.getarrays.start_up.entity.Product;
import io.getarrays.start_up.entity.User;
import io.getarrays.start_up.payload.FlowDto;
import io.getarrays.start_up.repository.FlowRepository;
import io.getarrays.start_up.repository.ProductRepository;
import net.bytebuddy.asm.MemberSubstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowService {

    @Autowired
    FlowRepository flowRepository;

    @Autowired
    ProductRepository productRepository;

    public HttpEntity<?> addFlow(FlowDto flowDto, User user) {
        Optional<Product> optionalProduct = productRepository.findById(flowDto.getProduct());
        if (optionalProduct.isPresent()) {
            Flow flow = new Flow(
                    flowDto.getName(),
                    optionalProduct.get(),
                    user,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    false,
                    0L
            );
            flowRepository.save(flow);
        }
        return ResponseEntity.status(202).body("Saved successfully");
    }

    public HttpEntity<?> getByFlowId(Long flowId) {
        Optional<Flow> optionalFlow = flowRepository.findById(flowId);
        if (optionalFlow.isPresent()){
            return ResponseEntity.status(200).body(optionalFlow.get());
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    public HttpEntity<?> editFlowById(Long flowId,FlowDto flowDto){
        Optional<Flow> optionalFlow = flowRepository.findById(flowId);
        Optional<Product> optionalProduct = productRepository.findById(flowDto.getProduct());

        if (optionalFlow.isPresent() && optionalProduct.isPresent()){
            Flow flow = optionalFlow.get();
            flow.setName(flowDto.getName());
            flow.setNeww(flowDto.isNeww());
            flow.setProduct(optionalProduct.get());
            flow.setHold(flowDto.isHold());
            flow.setArchived(flowDto.isArchived());
            flow.setHold(flowDto.isHold());
            flow.setCanceled(flowDto.isCanceled());
            flow.setOnway(flowDto.isOnway());
            flow.setName(flowDto.getName());
            flow.setDelivered(flowDto.isDelivered());
            flow.setReady(flowDto.isReady());
        }
        return ResponseEntity.status(202).body("Oqim o'zgartirildi");
    }

    public HttpEntity<?> deleteFlowById(Long flowId) {
         flowRepository.deleteById(flowId);
        Optional<Flow> optionalFlow = flowRepository.findById(flowId);
        if (!optionalFlow.isPresent()){
            return ResponseEntity.status(202).body("deleted");
        }
        return ResponseEntity.status(409).body("Not deleted");
    }
}
