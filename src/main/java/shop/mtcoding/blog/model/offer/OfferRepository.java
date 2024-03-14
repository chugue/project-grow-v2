package shop.mtcoding.blog.model.offer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.apply.ApplyRequest;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.UserRequest;
import shop.mtcoding.blog.model.user.UserResponse;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OfferRepository {
    private final EntityManager em;

    public List<UserResponse.UserListByUserId> findAllByResumeId(Integer userId) {

        String q = """
                select
                rt.id, rt.title, rt.area, rt.career 
                from resume_tb rt
                join user_tb ut
                on rt.user_id = ut.id
                where ut.id = ?;
                    """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, userId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<UserResponse.UserListByUserId> resumeList = mapper.list(query,UserResponse.UserListByUserId.class);

        return resumeList;
    }

    public List<OfferResponse.OfferListByUserId> findAllByUserId(Integer id) {

        String q = """
                select
                jt.id as user_id, ut.comp_name, jt.title, jt.task, jt.career
                from jobs_tb jt
                join user_tb ut
                on jt.user_id = ut.id
                where ut.id = ?
                    """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper();
        List<OfferResponse.OfferListByUserId> jobList = mapper.list(query,OfferResponse.OfferListByUserId.class);

        return jobList;
    }

    public List<OfferRequest.CompOfterDTO> findAllByJobsId(Integer jobsId){
        String q = """
                select
                     ot.id, ut.my_name, rt.title, rt.career, ot.resume_id, ot.status
                     from offer_tb ot
                     join resume_tb rt
                     on ot.resume_id = rt.id
                     join user_tb ut
                     on ut.id = rt.user_id
                     where ot.jobs_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<OfferRequest.CompOfterDTO> result = mapper.list(query,OfferRequest.CompOfterDTO.class);
        return result;
    }

    public List<UserRequest.ResumeOfterDTO> findAllByJobsId2(Integer resumeId){
        String q = """
                    select 
                    at.id, ut.comp_name, jt.title, jt.career, at.jobs_id, at.is_pass 
                    from apply_tb at 
                    join jobs_tb jt 
                    on at.jobs_id = jt.id 
                    join user_tb ut 
                    on ut.id = jt.user_id 
                    where at.resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<UserRequest.ResumeOfterDTO> result = mapper.list(query, UserRequest.ResumeOfterDTO.class);
        return result;
    }





    public List<SkillRequest.JobSkillDTO> findAllSkillById(Integer id){
        String q = """
               select
               st.name,st.color
               from jobs_tb jt
               join user_tb ut
               on jt.user_id = ut.id
               join skill_tb st
               on st.jobs_id = jt.id
               where jt.id =?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,id);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(query,SkillRequest.JobSkillDTO.class);

    }

    public OfferResponse.OfferDetailDTO findOffer(Integer resumeId) {
        String q = """
                SELECT * FROM offer_tb WHERE resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);

        Integer id = 0;
        Boolean isScrap = false;

        System.out.println("offerId : " + id);
        System.out.println("isOffer : " + isScrap);

        OfferResponse.OfferDetailDTO responseDTO = new OfferResponse.OfferDetailDTO(
                id, isScrap
        );

        return responseDTO;
    }

    public OfferResponse.OfferDetailDTO findOffer(Integer resumeId, Object sessionUserId) {
        String q = """
                SELECT id, 
                case when jobs_id is null then false else true 
                end as isOffer From offer_tb 
                where resume_id = ? and jobs_id = ?;
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

        System.out.println("offerId : " + id);
        System.out.println("isOffer : " + isScrap);

        OfferResponse.OfferDetailDTO responseDTO = new OfferResponse.OfferDetailDTO(
                id, isScrap
        );

        return responseDTO;
    }

    @Transactional
    public void save(OfferRequest.SaveDTO requestDTO, Integer status) {
        String q = """
                insert into offer_tb(jobs_id, resume_id, status, created_at) values(?,?,?, now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getJobsId());
        query.setParameter(2, requestDTO.getResumeId());
        query.setParameter(3, status);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(Object jobsId, OfferRequest.DeleteDTO resumeId) {
        String q = """
                delete from OFFER_TB where jobs_id = ? and resume_id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobsId);
        query.setParameter(2, resumeId.getResumeId());
        query.executeUpdate();
    }
}