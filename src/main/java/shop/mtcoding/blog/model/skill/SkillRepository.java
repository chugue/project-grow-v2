package shop.mtcoding.blog.model.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SkillRepository {
    private final EntityManager em;

    public List<Skill> findAllV2(int jobsId){
        Query query = em.createNativeQuery("select * from skill_tb where jobs_id = ?", Skill.class);
        query.setParameter(1, jobsId);
        return query.getResultList();
    }
    //

    public List<String> findALLNameByJobsId(int jobsId){
        Query query = em.createNativeQuery("select name from skill_tb where jobs_id = ?", String.class);
        query.setParameter(1, jobsId);
        return query.getResultList();
    }

    public List<String> findALLNameByResumeId(int resumeId){
        Query query = em.createNativeQuery("select name from skill_tb where resume_id = ?", String.class);
        query.setParameter(1, resumeId);
        return query.getResultList();
    }

    public List<SkillRequest.ApplyskillDTO> findAllSkillById(Integer id){
        String q = """
                select
                 st.name, st.color
                from apply_tb at
                join resume_tb rt
                  on at.resume_id = rt.id
                join skill_tb st
                  on st.resume_id = rt.id
                where at.id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(query, SkillRequest.ApplyskillDTO.class);
    }

    public List<SkillRequest.ApplyskillDTO> JobsSkill(Integer id) {
        String q = """
                select 
                st.name, st.color 
                from jobs_tb jt 
                join skill_tb st 
                on st.jobs_id = jt.id 
                where jt.id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(query, SkillRequest.ApplyskillDTO.class);
    }

    public List<SkillRequest.ApplyskillDTO> resumeSkill(Integer id) {
        String q = """
                select 
                st.name, st.color 
                from resume_tb rt 
                join skill_tb st 
                on st.resume_id = rt.id 
                where rt.id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper();
        return mapper.list(query, SkillRequest.ApplyskillDTO.class);
    }

    public List<Skill> compfindAllV2(Integer id) {
        Query query = em.createNativeQuery("select * from skill_tb where resume_id = ?", Skill.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
}
