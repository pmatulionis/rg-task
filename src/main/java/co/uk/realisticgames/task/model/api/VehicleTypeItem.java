package co.uk.realisticgames.task.model.api;

import co.uk.realisticgames.task.model.dto.VehicleTypeDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VehicleTypeItem class.
 *
 * @author Paulius Matulionis
 */
@Data
@NoArgsConstructor
public class VehicleTypeItem {

    private boolean primary;

    private String name;

    public VehicleTypeItem(VehicleTypeDto type) {
        this.primary = type.isPrimary();
        this.name = type.getName();
    }
}
