package org.example.repository;

import org.example.entity.Option;
import org.example.repository.base.BaseRepository;

import java.util.List;

public interface OptionRepository
        extends BaseRepository<Option, Long> {

    List<Option> findByQuestionId(Long questionId);

    List<Option> findCorrectOptionsByQuestionId(Long questionId);

    List<Option> searchByText(String keyword);

}