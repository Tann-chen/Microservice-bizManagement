package com.user.repository;

import com.user.domain.entity.Module;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Integer>{

    Module queryById(Integer moduleId);

    Module queryByName(String name);

    List<Module> queryByIsAvailableTrue();

}
