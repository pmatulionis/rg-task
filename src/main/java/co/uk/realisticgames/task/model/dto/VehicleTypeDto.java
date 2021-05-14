package co.uk.realisticgames.task.model.dto;

import co.uk.realisticgames.task.model.api.VehicleTypeItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * VehicleTypeDto class.
 *
 * @author Paulius Matulionis
 */
@Data
@EqualsAndHashCode(exclude = {"primary"})
@NoArgsConstructor
public class VehicleTypeDto implements Serializable {

    @JsonProperty("IsPrimary")
    private boolean primary;

    @JsonProperty("Name")
    private String name;

    public VehicleTypeDto(String name) {
        this.name = name;
    }

    public VehicleTypeDto(VehicleTypeItem item) {
        this.primary = item.isPrimary();
        this.name = item.getName();
    }
}
