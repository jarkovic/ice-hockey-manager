package cz.fi.muni.pa165.hockeymanager.dto;

import lombok.Data;

/**
 * @author Matus Jarkovic 456441
 */
@Data
public class HockeyPlayerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer offensiveStrength;

    private Integer defensiveStrength;

    private TeamDto team;

    public String getFullName() {
        return this.firstName + this.lastName;
    }
}
