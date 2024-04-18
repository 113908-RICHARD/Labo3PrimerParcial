package ar.edu.utn.frc.tup.lciii.dtos.common.respones;

import jakarta.validation.constraints.Negative;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PoolResponseDTO {
    String poolId;
    List<TeamResponseDTO> teams;

}
