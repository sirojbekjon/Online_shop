package io.getarrays.start_up.repository;

import com.sun.jna.platform.win32.WinDef;
import io.getarrays.start_up.entity.Flow;
import org.apache.commons.math3.analysis.function.Floor;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlowRepository extends JpaRepository<Flow,Flow> {

    List<Flow> findByUserId(Long user_id);

}
