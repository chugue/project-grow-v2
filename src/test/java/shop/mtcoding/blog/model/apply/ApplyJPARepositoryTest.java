package shop.mtcoding.blog.model.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ApplyJPARepositoryTest {
    @Autowired
    private ApplyJPARepository applyJPARepo;

    @Test
    public void q_test(){
        // given
        int resumeId = 1;
        // when
        List<Apply> applyList = applyJPARepo.findAppliesByNot1ByResumeId(resumeId);
        // then

        System.out.println(applyList.size());

    }

    @Test
    public void s_test(){
        //given
        Integer id =1;
        //when
        List<Apply> dd =  applyJPARepo.findAllByJidAn2(id);

        //then
        System.out.println(dd);
        System.out.println(dd.size());
        System.out.println();
    }

}
