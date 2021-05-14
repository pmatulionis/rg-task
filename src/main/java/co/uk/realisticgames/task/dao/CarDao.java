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

    List<CarDataDto> findData(SortParams sortParams, FilterParams filterParams);

    void update(Integer id, CarDataDto dto);

    void create(CarDataDto dto);

    void delete(Integer id);
}
