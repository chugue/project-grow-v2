package shop.mtcoding.blog.model.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(ScrapRepository.class)
@DataJpaTest
public class Scrap_Test {

    @Autowired
    private ScrapRepository scrapRepository;

    @Test
    public void findScrap_test() {
        int resumeId = 1;
        int sessionUserid = 2;
        scrapRepository.findScrap(resumeId, sessionUserid);
    }
}
