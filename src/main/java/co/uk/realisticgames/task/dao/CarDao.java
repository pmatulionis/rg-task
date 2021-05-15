package co.uk.realisticgames.task.dao;

import co.uk.realisticgames.task.model.dto.CarDataDto;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;

import java.util.List;

/**
 * CarDao interface.
 *
 * @author Paulius Matulionis
 */
public interface CarDao {

    /**
     * Finds the data by the given filter parameters and sorts by the given sort parameters. If neither of them is
     * provided then all the data is returned. If filter parameters are provided the data is filtered matching exact
     * values.
     *
     * @param sortParams   sort parameters
     * @param filterParams filter parameters
     * @return a list of car data
     */
    List<CarDataDto> findData(SortParams sortParams, FilterParams filterParams);

    /**
     * Updates the given car data by the given ID. As there may be duplicates by the same ID, all records that are
     * matched will be updated.
     *
     * @param id  id to find the records to update
     * @param dto car data object
     */
    void update(Integer id, CarDataDto dto);

    /**
     * Creates completely new car data entry in the existing data list.
     *
     * @param dto car data object
     */
    void create(CarDataDto dto);

    /**
     * Deletes car data record(s) that is/are matched by the given ID. As there may be multiple records found by
     * the given ID, they all be deleted.
     *
     * @param id the ID of the car data
     */
    void delete(Integer id);
}
