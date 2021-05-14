package co.uk.realisticgames.task.service;

import co.uk.realisticgames.task.model.api.CarDataItem;
import co.uk.realisticgames.task.model.api.ResponseItem;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;

import java.util.List;

/**
 * CarService interface.
 *
 * @author Paulius Matulionis
 */
public interface CarService {

    List<CarDataItem> findData(SortParams sortParams, FilterParams filterParams);

    ResponseItem update(Integer id, CarDataItem item);

    ResponseItem create(CarDataItem item);

    ResponseItem remove(Integer id);
}
