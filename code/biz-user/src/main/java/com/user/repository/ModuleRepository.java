package com.user.repository;

import com.user.domain.entity.Module;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

    Optional<Module> queryById(Integer moduleId);

    Optional<Module> queryByName(String name);

    List<Module> queryByIsAvailableTrue();

}
