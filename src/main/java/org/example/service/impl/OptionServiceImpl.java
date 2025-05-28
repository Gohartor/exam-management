package org.example.service.impl;

import org.example.entity.Option;
import org.example.repository.OptionRepository;
import org.example.service.OptionService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class OptionServiceImpl
        extends BaseServiceImpl<Option, Long, OptionRepository>
        implements OptionService {

    public OptionServiceImpl(OptionRepository repository) {
        super(repository);
    }

    @Override
    public List<Option> findByQuestionId(Long questionId) {
        return repository.findByQuestionId(questionId);
    }

    @Override
    public List<Option> findCorrectOptionByQuestionId(Long questionId) {
        return repository.findCorrectOptionsByQuestionId(questionId);
    }

    @Override
    public List<Option> searchByText(String keyword) {
        return repository.searchByText(keyword);
    }
}