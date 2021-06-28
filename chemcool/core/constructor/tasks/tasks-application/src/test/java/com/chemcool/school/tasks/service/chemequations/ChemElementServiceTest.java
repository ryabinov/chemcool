package com.chemcool.school.tasks.service.chemequations;

import com.chemcool.school.tasks.app.TasksApplication;
import com.chemcool.school.tasks.domain.chemequations.Elements.ChemElement;
import com.chemcool.school.tasks.infrastructure.storage.chemequations.ChemElementRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Column;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TasksApplication.class)
@Transactional
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testEquationDb.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ChemElementServiceTest {

    @Autowired
    private ChemElementService chemElementService;

    private final ChemElement testElementName = new ChemElement("test",1,"test","test","test","test",1,"test","test",1,"test","test");

    @Test
    void save() {
        try {
            chemElementService.save(testElementName);
            ChemElement chemElement = chemElementService.getAll().get(0);

            if (!testElementName.equals(chemElement.getElementName())
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

//    private final ChemElementRepository elementRepository;
//
//    public String save(ChemElement element) {
//        elementRepository.save(element);
//        log.info("Элемент с ID: " + element.getElementId() + "  добавлен.");
//        return element.getElementId();
//    }
    @Test
    void getAll() {

    }

    @Test
    void getById() {

    }

    @Test
    void getByNumber() {

    }
}