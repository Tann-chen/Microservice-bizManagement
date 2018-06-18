package com.user.service;

import com.user.domain.entity.Module;
import com.user.domain.entity.User;

import java.util.List;
import java.util.Set;

public interface ModuleService {
    void addModule(String moduleName);

    void enableModule(Integer moduleId);

    void disableModule(Integer moduleId);

    List<Module> getAllModules();

    List<Module> getAvailModulesByAdmin();

    Set<Module> getAvailModulesByUser(User user);
}
