package com.general.demo.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.general.demo.domain.model.system.Logging;

public interface LogService {

       Page<Logging> findAllForPageing(Pageable pageable);
       List<Logging> findAll();
       List<Logging> findByLevel(String level);
}
