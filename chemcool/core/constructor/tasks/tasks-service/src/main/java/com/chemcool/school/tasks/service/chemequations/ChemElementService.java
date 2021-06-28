package com.chemcool.school.tasks.service.chemequations;

import com.chemcool.school.tasks.domain.chemequations.Elements.ChemElement;
import com.chemcool.school.tasks.infrastructure.storage.chemequations.ChemElementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Бизнес логика для работы с химическими элементами
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChemElementService {
    private final ChemElementRepository elementRepository;

    public String save(ChemElement element) {
        elementRepository.save(element);
        log.info("Элемент с ID: " + element.getElementId() + "  добавлен.");
        return element.getElementId();
    }

    public List<ChemElement> getAll() {
        return elementRepository.findAll();
    }

    public Optional<ChemElement> getById(String id) {
        return elementRepository.findById(id);
    }

    public Optional<ChemElement> getByNumber(int number) {
        return elementRepository.findByElementNumber(number);
    }
}
