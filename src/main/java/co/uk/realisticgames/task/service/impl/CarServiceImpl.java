package co.uk.realisticgames.task.service.impl;

import co.uk.realisticgames.task.dao.CarDao;
import co.uk.realisticgames.task.model.api.CarDataItem;
import co.uk.realisticgames.task.model.api.ResponseItem;
import co.uk.realisticgames.task.model.dto.CarDataDto;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;
import co.uk.realisticgames.task.service.CarService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CarServiceImpl class.
 *
 * @author Paulius Matulionis
 */
@Service
public class CarServiceImpl implements CarService {

    private static final ResponseItem OK = new ResponseItem("ok");

    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public List<CarDataItem> findData(SortParams sortParams, FilterParams filterParams) {
        List<CarDataDto> data = carDao.findData(sortParams, filterParams);
        return data.stream().map(CarDataItem::new).toList();
    }

    @Override
    public ResponseItem update(Integer id, CarDataItem item) {
        carDao.update(id, new CarDataDto(item));
        return OK;
    }

    @Override
    public ResponseItem create(CarDataItem item) {
        carDao.create(new CarDataDto(item));
        return OK;
    }

    @Override
    public ResponseItem remove(Integer id) {
        carDao.delete(id);
        return OK;
    }
}
