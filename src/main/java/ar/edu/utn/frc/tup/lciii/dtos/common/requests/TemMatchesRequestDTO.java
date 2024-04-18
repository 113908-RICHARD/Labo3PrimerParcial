package ar.edu.utn.frc.tup.lciii.dtos.common.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TemMatchesRequestDTO {

    Integer id;
    Integer points;
    Integer tries;
    @JsonProperty("yellow_cards")
    Integer yellowCards;

    @JsonProperty("red_cards")
    Integer redCards;
}
