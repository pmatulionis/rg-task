package co.uk.realisticgames.task.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ResponseItem class.
 *
 * @author Paulius Matulionis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItem implements Serializable {

    private String message;
}
