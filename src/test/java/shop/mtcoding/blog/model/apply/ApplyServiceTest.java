package shop.mtcoding.blog.model.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(ApplyService.class)
@DataJpaTest
public class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;

    @Test
    public void find_test() {
        //given
        int resumeId = 1;
        //when
        applyService.t(resumeId);
        //then


    }
}
