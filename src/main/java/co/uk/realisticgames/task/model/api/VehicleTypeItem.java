package co.uk.realisticgames.task.model.api;

import co.uk.realisticgames.task.model.dto.VehicleTypeDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * VehicleTypeItem class.
 *
 * @author Paulius Matulionis
 */
@Generated
@Data
@NoArgsConstructor
@ApiModel(description = "Main object to hold the details of a single type of the car data")
public class VehicleTypeItem {

    @ApiModelProperty("A flag which specifies if the type is primary")
    private boolean primary;

    @ApiModelProperty("Property to specify a name of the type")
    private String name;

    public VehicleTypeItem(VehicleTypeDto type) {
        this.primary = type.isPrimary();
        this.name = type.getName();
    }
}
