package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.common.respones.PoolResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PoolsService {

    PoolResponseDTO getPool(String id);
    List<PoolResponseDTO> getAlPools();
}
