package com.chemcool.school.tasks.domain.chemequations.Elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Entity химического элемента
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chemistry_element")
public class ChemElement {

    //id элемента в бд
    @Id
    @Column(name = "element_id")
    private String elementId;

    //порядковый номер элемента - это число равное числу протонов в ядре атома и числу электронов,
    //которые вращаются вокруг него
    @Column(name = "element_number")
    private int elementNumber;

    //символ элемента
    @Column(name = "element_symbol")
    private String elementSymbol;

    //транскрипция элемента на русском
    @Column(name = "element_transcription")
    private String ElementTranscription;


    //наименование элемента
    @Column(name = "element_name")
    private String elementName;

    //наименование элемента на латинском
    @Column(name = "element_lat_name")
    private String elementLatName;

    //молярная масса элемента
    @Column(name = "element_molar_mass")
    private double elementMolarMass;

    //степень окисления элемента
    @Column(name = "element_oxidation_value")
    private String elementOxidationValue;

    //Периоды  –  горизонтальные строки химических элементов
    //Номер периода  –  номер внешнего энергетического уровня в электронной формуле атома элемента
    @Column(name = "element_period")
    private String elementPeriod;

    //Группы - вертикальные строки химических элементов
    //Номер группы (для большинства элементов)  –  общее число валентных электронов
    // (электронов внешнего энергетического уровня, а также предпоследнего d-подуровня,
    // если он застроен не полностью)
    @Column(name = "element_group")
    private int elementGroup;

    //Подгруппа - А - главные (s- и р-элементы) и В - побочные (d- и f-элементы)
    @Column(name = "element_subgroup")
    private String elementSubgroup;

    @Column(name = "element_valence")
    private String elementValence;


    public int getElementLowValence() {
        return 8 - elementGroup;
    }

    public int getElementHighValence() {
        return elementGroup;
    }

    public String getElementValence() {
        if (elementValence.contains("-1")) {
            int val = elementGroup > 4 ? getElementLowValence() : getElementHighValence();
            return String.valueOf(val);
        }
        return elementValence;
    }
}
