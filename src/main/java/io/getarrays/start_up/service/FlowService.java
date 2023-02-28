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

    public HttpEntity<?> getByUserId(Long userId) {
        List<Flow> FlowList = flowRepository.findByUserId(userId);
        if (!FlowList.isEmpty()){
            return ResponseEntity.status(200).body(FlowList);
        }
        return ResponseEntity.status(404).body("Not Found");
    }
}
