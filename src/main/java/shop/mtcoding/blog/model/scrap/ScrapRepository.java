package shop.mtcoding.blog.model.scrap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.dto.scrap.ScrapRequest;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.model.resume.Resume;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScrapRepository {

    private final EntityManager em;

    // select * from resume_tb where id in (select resume_id from scrap_tb where user_id = 6)
    public List<Resume> findByUserIdWithResume(Integer id){
        String q = """
                select * from resume_tb where id in (select resume_id from scrap_tb where user_id = ?)
                """;

        Query query = em.createNativeQuery(q, Resume.class);
        query.setParameter(1, id);
        List<Resume> resumeList = query.getResultList();

        return resumeList;
    }

    public List<Scrap> findByUserId(Integer id) {
        String q = """
                SELECT * FROM SCRAP_TB where user_id = ?;
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
        List<Scrap> scrapList = query.getResultList();

        return scrapList;
    }

    public ScrapResponse.DetailDTO findScrap(Integer resumeId) {
        String q = """
                SELECT * FROM scrap_tb WHERE resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);

        Integer id = 0;
        Boolean isScrap = false;

        System.out.println("id : " + id);
        System.out.println("isScrap : " + isScrap);

        ScrapResponse.DetailDTO responseDTO = new ScrapResponse.DetailDTO(
                id, isScrap
        );

        return responseDTO;
    }

    public ScrapResponse.DetailDTO findScrap(Integer resumeId, Integer sessionUserId) {
        String q = """
                SELECT id, 
                case when user_id is null then false else true 
                end as isScrap From scrap_tb 
                where resume_id = ? and user_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, sessionUserId);

        Integer id = null;
        Boolean isScrap = null;
        try {
            Object[] row = (Object[]) query.getSingleResult();
            id = (Integer) row[0];
            isScrap = (Boolean) row[1];
        } catch (Exception e) {
            id = 0;
            isScrap = false;
        }

        System.out.println("id : " + id);
        System.out.println("isScrap : " + isScrap);

        ScrapResponse.DetailDTO responseDTO = new ScrapResponse.DetailDTO(
                id, isScrap
        );

        return responseDTO;
    }

    @Transactional
    public void save(ScrapRequest.SaveDTO requestDTO, Integer sessionUserId) {
        String q = """
                insert into scrap_tb(resume_id, user_id, created_at) values(?,?, now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, sessionUserId);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(Integer id) {
        String q = """
                delete from scrap_tb where id = ?
                """;
        Query query = em.createNativeQuery(q);
        System.out.println("deleteById 스크랩 키값 : " + id);
        query.setParameter(1, id);
        query.executeUpdate();
    }


}
