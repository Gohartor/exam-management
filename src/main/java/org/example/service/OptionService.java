package org.example.service;

import org.example.entity.Option;
import org.example.service.base.BaseService;

import java.util.List;

public interface OptionService extends BaseService<Option, Long> {
    List<Option> findByQuestionId(Long questionId);

    List<Option> findCorrectOptionByQuestionId(Long questionId);

    List<Option> searchByText(String keyword);
}