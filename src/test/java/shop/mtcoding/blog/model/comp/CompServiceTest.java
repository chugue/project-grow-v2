package shop.mtcoding.blog.model.comp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(CompService.class)
@DataJpaTest
public class CompServiceTest {
    @Autowired
    private CompService compService;

    @Test
    public void findAllResumeUserSKill_test(){
        // given

        // when
        compService.findAllRusList();
        // then

    }
}
