package shop.mtcoding.blog.model.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepo;

    @Autowired
    private EntityManager em;

//    @Test
//    public void findByIdJoinUse_test() {
//        // given
//        int id = 1;
//
//        // when
//        Board board = boardJPARepo.findByIdJoinUser(id);
//
//        // then
//        System.out.println("findByIdJoinUse_test : "+board.getTitle());
//        System.out.println("findByIdJoinUse_test : "+board.getUser().getMyName());
//
//    }

    // findAll (sort)
    @Test
    public void findAll_test() {
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        //when
        List<Board> boardList = boardJPARepo.findAll(sort);

        //then
        System.out.println("findAll_test : " + boardList);

    }

    @Test
    public void deleteById_test() {
        //given
        int id = 1;

        //when
        boardJPARepo.deleteById(id);
        em.flush();

        //then

    }

}
