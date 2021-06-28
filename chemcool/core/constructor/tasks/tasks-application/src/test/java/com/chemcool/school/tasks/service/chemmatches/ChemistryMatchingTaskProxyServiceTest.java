package com.chemcool.school.tasks.service.chemmatches;

import com.chemcool.school.tasks.app.TasksApplication;
import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTask;
import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTaskExample;
import com.chemcool.school.tasks.domain.chemmatches.CoupleForMatching;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TasksApplication.class)
class ChemistryMatchingTaskProxyServiceTest {

    @Autowired
    private ChemistryMatchingTaskProxyService service;

    private String id;

    @BeforeEach
    public void add() {
        List<CoupleForMatching> coupleForMatching = new ArrayList<>();

        coupleForMatching.add(new CoupleForMatching("He", "Гелий"));
        coupleForMatching.add(new CoupleForMatching("H", "Водород"));
        coupleForMatching.add(new CoupleForMatching("O", "Кислород"));
        coupleForMatching.add(new CoupleForMatching("C", "Углерод"));

        id = service.add(new ChemistryMatchingTaskExample(
                "uuid",
                "Выберите правильные пары",
                1,
                2,
                coupleForMatching
        ));

        assertThat(id).isNotNull();
        System.out.println("Создана задача с id: " + id);
    }


    @Test
    void getById_true() {
        timer(1000);
        ChemistryMatchingTask task = service.getById(id).orElse(null);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("Выберите правильные пары");
    }

    @Test
    void getById_null() {  // получаем по несуществующему ID
        timer(1000);
        ChemistryMatchingTask task = service.getById(id + "1").orElse(null);
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        timer(1000);
        ChemistryMatchingTask task = service.getById(id + "1").orElse(null);
        assertThat(task).isNull();
    }

    @Test
    void getAllByChapterId() {
        timer(1000);
        List<ChemistryMatchingTask> task = service.getAllByChapterId(1);
        assertThat(task).isNotNull();
    }

    @Test
    void update() {
        timer(1000);
        ChemistryMatchingTask task = service.getById(id).orElse(null);
        assertThat(task).isNotNull();
        task.setDescription("Choose the right pairs");

        List<CoupleForMatching> updateCoupleForMatchings = new ArrayList<>();

        updateCoupleForMatchings.add(new CoupleForMatching("He", "Helium"));
        updateCoupleForMatchings.add(new CoupleForMatching("H", "Hydrogen"));
        updateCoupleForMatchings.add(new CoupleForMatching("O", "Oxygen"));
        updateCoupleForMatchings.add(new CoupleForMatching("C", "Carbon"));

        task.setCoupleForMatchingList(updateCoupleForMatchings);
        service.update(task);

        timer(1000);

        ChemistryMatchingTask taskUp = service.getById(id).orElse(null);

        assertThat(taskUp).isNotNull();
        assertThat(taskUp.getDescription()).isEqualTo("Choose the right pairs");
        assertThat(taskUp.getCoupleForMatchingList().stream()
                .filter(c -> c.getRightCouple().equals("Helium"))
                .findAny()).isNotNull();
    }

    @AfterEach
    void deleteById() {
        service.deleteById(id);
        ChemistryMatchingTask task = service.getById(id).orElse(null);
        assertThat(task).isNull();
    }

    public void timer(int milliseconds) {  /*добавил таймер перед каждым методом, в противном случае кафка не успевает сохранить
                                    таску и тест проваливается*/
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}