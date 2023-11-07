package com.peaksoft.employee.parameter.repository;

import com.peaksoft.employee.parameter.model.Country;
import com.peaksoft.employee.parameter.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

    @Query(value = "select s from State s where " +
            "s.stateName LIKE %?1% or s.capital like %?1% or s.details like %?1%")
    List<State> findByKeyword1(String keyword);

    @Query(value = "select s from State s where " +
            "concat( s.stateName, s.capital, s.code, s.details ) like %?1%")
    List<State> findByKeyword(String keyword);

}
