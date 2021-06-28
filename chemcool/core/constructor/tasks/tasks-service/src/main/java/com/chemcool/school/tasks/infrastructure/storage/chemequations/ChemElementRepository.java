
package com.chemcool.school.tasks.infrastructure.storage.chemequations;

import com.chemcool.school.tasks.domain.chemequations.Elements.ChemElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Репозиторий для работы с химическими элементами из БД
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */
@Repository
public interface ChemElementRepository extends JpaRepository<ChemElement, String>, JpaSpecificationExecutor<ChemElement> {
    Optional<ChemElement> findByElementNumber(int elementNumber);
}
