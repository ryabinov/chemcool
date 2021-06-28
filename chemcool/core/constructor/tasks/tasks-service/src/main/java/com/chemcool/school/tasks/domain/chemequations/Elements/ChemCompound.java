package com.chemcool.school.tasks.domain.chemequations.Elements;

import com.chemcool.school.tasks.service.chemequations.ChemEquationsTaskException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Класс для построения химических соединений
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */

@Data
@AllArgsConstructor
public class ChemCompound {

    private String compoundSymbols;
    private Map<ChemElement, Integer> compoundElements;

    public ChemCompound() {
        this.compoundElements = new HashMap();
        this.compoundSymbols = "";
    }

    public boolean checkFactors(Map<ChemElement, Integer> elementsMap) {
        if(checkValenceFactor(elementsMap)) {
            return true;
        }
        return false;
    }

    public boolean checkValenceFactor(Map<ChemElement, Integer> elementsMap) {
        if (elementsMap.size() > 2) {
            return false;
        }
        ChemElement bufElement = null;
        for (ChemElement el : elementsMap.keySet()) {
            if (bufElement == null) {
                bufElement = el;
                continue;
            }
            if (bufElement.getElementValence().contains(elementsMap.get(el).toString()) && el.getElementValence().contains(elementsMap.get(bufElement).toString()))
                return true;
        }
        return false;
    }

    public void reaction(List<ChemElement> elements) {
        elements.stream().forEach(e -> compoundElements.put(e, compoundElements.getOrDefault(e, 0) + 1));
        if (checkFactors(compoundElements)) {
            reactionWrite();
        } else {
            throw new ChemEquationsTaskException("Ошибка условий формарования молекулы");
        }

        //betaTest
        System.out.println(checkValenceFactor(compoundElements));
    }

    public void reactionWrite() {
        compoundElements.keySet().stream().forEach(e -> compoundSymbols += e.getElementSymbol() + compoundElements.get(e));
        compoundSymbols = compoundSymbols.replaceAll("1", "");
    }
}
