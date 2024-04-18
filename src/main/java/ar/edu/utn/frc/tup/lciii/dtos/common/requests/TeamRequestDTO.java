package ar.edu.utn.frc.tup.lciii.dtos.common.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamRequestDTO {

    Long id;
    String name;
    String country;
    @JsonProperty("world_ranking")
    Integer worldRanking;

    String pool;

}
