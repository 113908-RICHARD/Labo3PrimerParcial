package ar.edu.utn.frc.tup.lciii.dtos.common.requests;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MatchRequestDTO {

    Long id;
    String date;
    List<TemMatchesRequestDTO> teams;

    Integer stadium;
    String pool;
}
