package com.peaksoft.employee.parameter.repository;

import com.peaksoft.employee.parameter.model.Location;
import com.peaksoft.employee.parameter.model.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    @Query(value = "select l from Location l where " +
            " l.city LIKE %?1% or l.address like %?1% or l.details like %?1%")
    List<Location> findByKeyword1(String keyword);

    @Query(value = "select l from Location l where " +
            "concat( l.city, l.address, l.details ) like %?1%")
    List<Location> findByKeyword(String keyword);

}
