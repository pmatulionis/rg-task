package co.uk.realisticgames.task.dao.impl;

import co.uk.realisticgames.task.dao.CarDao;
import co.uk.realisticgames.task.model.dto.CarDataDto;
import co.uk.realisticgames.task.model.dto.DataDto;
import co.uk.realisticgames.task.model.dto.VehicleTypeDto;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * CarDaoImpl class.
 *
 * @author Paulius Matulionis
 */
@Component
public class CarDaoImpl implements CarDao, InitializingBean {

    private final ObjectMapper objectMapper;

    private List<CarDataDto> dataDataList;

    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    private final Lock readLock;

    private final Lock writeLock;

    public CarDaoImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.readLock = READ_WRITE_LOCK.readLock();
        this.writeLock = READ_WRITE_LOCK.writeLock();
    }

    @Override
    public List<CarDataDto> findData(SortParams sortParams, FilterParams filterParams) {
        readLock.lock();
        try {
            List<CarDataDto> list = new LinkedList<>(this.dataDataList);

            if (filterParams.performFilter()) {
                list = list.stream().filter(f -> {
                    Predicate<CarDataDto> p = e -> e.getId() != null;

                    if (filterParams.name() != null) {
                        p = p.and(e -> filterParams.name().equals(e.getName()));
                    }

                    if (filterParams.country() != null) {
                        p = p.and(e -> filterParams.country().equals(e.getCountry()));
                    }

                    if (filterParams.type() != null) {
                        p = p.and(e -> e.getTypes().contains(new VehicleTypeDto(filterParams.type())));
                    }

                    return p.test(f);
                }).collect(Collectors.toCollection(LinkedList::new));
            }

            Optional.ofNullable(buildComparator(sortParams)).ifPresent(list::sort);

            return list;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void update(Integer id, CarDataDto dto) {
        writeLock.lock();
        try {
            this.dataDataList.stream().filter(f -> f.getId().equals(id)).forEach(r -> {
                r.setCountry(dto.getCountry());
                r.setName(dto.getName());
                r.setCommonName(dto.getCommonName());
                r.setTypes(dto.getTypes());
            });
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void create(CarDataDto dto) {
        writeLock.lock();
        try {
            this.dataDataList.add(dto);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void delete(Integer id) {
        writeLock.lock();
        try {
            this.dataDataList.removeIf(f -> f.getId().equals(id));
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void afterPropertiesSet()
            throws Exception {
        ClassPathResource resource = new ClassPathResource("data/carmfg_data.json");
        DataDto data = objectMapper.readValue(resource.getInputStream(), DataDto.class);
        this.dataDataList = data.getData();
    }

    private Comparator<CarDataDto> buildComparator(SortParams params) {
        if (params.performSort()) {
            if (SortParams.Field.COUNTRY.equals(params.getField())) {
                if (SortParams.Direction.DESC.equals(params.getDirection())) {
                    return Comparator.comparing(CarDataDto::getCountry).reversed();
                } else {
                    return Comparator.comparing(CarDataDto::getCountry);
                }
            }

            if (SortParams.Field.NAME.equals(params.getField())) {
                if (SortParams.Direction.DESC.equals(params.getDirection())) {
                    return Comparator.comparing(CarDataDto::getName).reversed();
                } else {
                    return Comparator.comparing(CarDataDto::getName);
                }
            }
        }
        return null;
    }
}
