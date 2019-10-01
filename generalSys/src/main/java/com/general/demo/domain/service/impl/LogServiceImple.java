package com.general.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.general.demo.domain.model.system.Logging;
import com.general.demo.domain.repos.LoggingEventRepository;
import com.general.demo.domain.repos.entity.LoggingEvent;
import com.general.demo.domain.service.LogService;
import com.general.demo.util.Utils;

@Service
public class LogServiceImple implements LogService {
    @Autowired
    private LoggingEventRepository loggingEventRepository;
    @Override
    public Page<Logging> findAllForPageing(Pageable pageable) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public List<Logging> findAll() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public List<Logging> findByLevel(String level) {
        List<LoggingEvent>  loggingEvents = loggingEventRepository.findAllByLevel(level);
        List<Logging> models = new ArrayList<>();
        loggingEvents.forEach(m->models.add(Utils.copy(m,Logging.class)));
        return models;
    }

}
