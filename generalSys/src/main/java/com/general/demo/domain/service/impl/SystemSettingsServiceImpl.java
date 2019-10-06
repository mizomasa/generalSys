package com.general.demo.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.general.demo.domain.model.system.SystemSetting;
import com.general.demo.domain.repos.MSystemRepository;
import com.general.demo.domain.repos.entity.M_System;
import com.general.demo.domain.repos.entity.M_SystemPK;
import com.general.demo.domain.service.SystemSettingsService;
import com.general.demo.ex.BusinessException;
import com.general.demo.ex.FatalException;
import com.general.demo.util.Utils;

@Service
public class SystemSettingsServiceImpl implements SystemSettingsService {
    @Autowired
    private MSystemRepository mSystemRepository;

    @Override
    public List<SystemSetting> findBy(@NotNull String id) {
        List<M_System> systems = mSystemRepository.findById(id);
        return Utils.copyList(systems, SystemSetting.class);
    }

    @Override
    public SystemSetting findBy(@NotNull String id, @NotNull String subId) throws BusinessException {
        return Utils.copy(
                mSystemRepository
                    .findById(new M_SystemPK(id,subId))
                    .orElseThrow(() ->new BusinessException("対象データがありません。"))
                ,SystemSetting.class);
    }

    @Override
    public List<SystemSetting> findAll() {
        List<M_System> systems = mSystemRepository.findAll();
        return Utils.copyList(systems, SystemSetting.class);
    }

    @Override
    @Transactional
    public SystemSetting saveFor(@NotNull SystemSetting model) throws FatalException {
        M_SystemPK pk = new M_SystemPK(model.getId(),model.getSubId());

        Optional<M_System> system = mSystemRepository.findById(pk);
        if(model.isNew()) {
            if(system.isPresent()) throw new FatalException("");
        }else {
            system.orElseThrow(()->new FatalException("対象データが削除されています。"));
        }
        return save(model);
    }

    @Override
    @Transactional
    public void delete(@NotNull String id, @NotNull String subId) throws BusinessException {
        M_SystemPK pk = new M_SystemPK(id,subId);
        mSystemRepository.findById(pk)
            .orElseThrow(()->new BusinessException("既に削除されています。"));
        mSystemRepository.deleteById(pk);
    }

    private SystemSetting save(SystemSetting model) {
        M_System saveData = Utils.copy(model,M_System.class);
        mSystemRepository.save(saveData);
        return Utils.copy(saveData,SystemSetting.class);
    }

}
