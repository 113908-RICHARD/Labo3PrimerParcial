package ar.edu.utn.frc.tup.lciii.dtos.common.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeamResponseDTO {


    Integer teamId;

    String teamName;

    String country;
    Integer matchesPlayed;
    Integer wins;

    Integer draws;

    Integer losses;
    Integer pointsFor;

    Integer poinstAgainst;
    Integer pointsDifferential;
    Integer triesMade;
    Integer bonusPoints;
    Integer points;
    Integer totalYellowCards;
    Integer totalRedCards;

}
