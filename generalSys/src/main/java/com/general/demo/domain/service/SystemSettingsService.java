package com.general.demo.domain.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.general.demo.domain.model.system.SystemSetting;
import com.general.demo.ex.BusinessException;
import com.general.demo.ex.FatalException;

public interface SystemSettingsService {

    List<SystemSetting> findBy(@NotNull String id);
    SystemSetting findBy(@NotNull String id, @NotNull  String subId) throws BusinessException;
    List<SystemSetting> findAll();
    SystemSetting saveFor(@NotNull SystemSetting model) throws FatalException;
    void delete(@NotNull String id, @NotNull  String subId) throws BusinessException;

}
