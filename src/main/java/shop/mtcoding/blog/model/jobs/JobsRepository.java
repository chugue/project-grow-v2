package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JobsRepository {
    private final EntityManager em;

    public List<JobResponse.DTO> findAllWithUserV2(){
        String q = """
                select jt.id, jt.user_id, jt.area, jt.title, jt.edu, jt.career, jt.content, jt.dead_line, jt.task, jt.created_at, ut.comp_name 
                from jobs_tb jt inner join user_tb ut 
                on jt.user_id = ut.id;
                """;

        Query query = em.createNativeQuery(q);
        // 엔티티랑 다른 모양의 쿼리를 직접 DTO로 만들어서 매핑하기 위한 툴
        JpaResultMapper mapper = new JpaResultMapper();
        // mapper.list ( 쿼리, 결과를 위한 DTO)로 만들고 결과가 여러개라면 List<DTO>로 생산후 반환
        List<JobResponse.DTO> result = mapper.list(query, JobResponse.DTO.class);
        return result;
    }

    public List<JobResponse.DTO> findAllWithUserV2(String keyword) {
        String q = """
                SELECT j.*, u.comp_name 
                FROM jobs_tb j join user_tb u 
                on j.user_id = u.id 
                where j.title like ? or u.comp_name;
                """;

        Query query = em.createNativeQuery(q, Jobs.class);
        query.setParameter(1, "%" + keyword + "%");

        JpaResultMapper mapper = new JpaResultMapper();
        List<JobResponse.DTO> result = mapper.list(query, JobResponse.DTO.class);
        return result;
    }


    public List<Jobs> findAllV2() {
        String q = """
                select * from jobs_tb order by id desc;
                """;

        Query query = em.createNativeQuery(q, Jobs.class);
        return query.getResultList();
    }



    public Jobs findCompId(Integer jobId) {
        Query query = em.createNativeQuery("select * from jobs_tb where id = ?", Jobs.class);
        query.setParameter(1, jobId);

        Jobs job = (Jobs) query.getSingleResult();

        return job;
    }

    public List<Object[]> findAllByUserId() {
        String q = """  
                select
                    jt.id, ut.id as user_id, jt.title, jt.edu, jt.career, jt.area, jt.dead_line, st.name
                    from jobs_tb jt
                    join user_tb ut
                    on jt.user_id = ut.id
                    join skill_tb st
                    on jt.id = st.jobs_id
                    order by jt.id;
                    """;
        Query query = em.createNativeQuery(q);

        List<Object[]> jobList = (List<Object[]>) query.getResultList();
        return jobList;

    }


    public List<JobResponse.JobListByUserId> findAllByUserId(Integer id) {

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
        List<JobResponse.JobListByUserId> jobList = mapper.list(query,JobResponse.JobListByUserId.class);

        return jobList;
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


    public Object[] findById(Integer jobId) {
        String q = """
                select ut.comp_name, ut.business_number, ut.phone, jt.area, jt.edu, jt.career, jt.content,
                    jt.title, jt.id, ut.homepage, jt.task, jt.user_id, jt.dead_line
                from jobs_tb jt
                join user_tb ut
                on jt.user_id = ut.id
                where jt.id = ?
                    """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobId);
        Object[] job = (Object[]) query.getSingleResult();
        return job;
    }

    @Transactional
    public void updateById() {
    }

    @Transactional
    public void save(JobRequest.JobWriterDTO requestDTO) {
        // 공고 insert
        String q = """
                insert into Jobs_tb(title,area,edu,career,content,dead_line,task,user_id,created_at) 
                values(?,?,?,?,?,?,?,?,now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getArea());
        query.setParameter(3, requestDTO.getEdu());
        query.setParameter(4, requestDTO.getCareer());
        query.setParameter(5, requestDTO.getContent());
        query.setParameter(6, requestDTO.getDeadLine());
        query.setParameter(7, requestDTO.getTask());
        query.setParameter(8, requestDTO.getUserId());

        query.executeUpdate();

        Long newJobsid = (Long) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();

        // 스킬 insert
        for (int i = 0; i < requestDTO.getSkill().size(); i++) {
            Query addSkillquery = em.createNativeQuery("insert into skill_tb(role,jobs_id,name) values (2,?,?)");
            addSkillquery.setParameter(1,newJobsid);
            addSkillquery.setParameter(2,requestDTO.getSkill().get(i));

            addSkillquery.executeUpdate();
        }

    }

    @Transactional
    public void update(JobRequest.JobUpdateDTO requestDTO,Integer id) {
        Query query = em.createNativeQuery("update Jobs_tb set title = ? ,area = ?,edu=?,career =? ,content = ?, dead_line = ? , task = ? where id = ?");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getArea());
        query.setParameter(3, requestDTO.getEdu());
        query.setParameter(4, requestDTO.getCareer());
        query.setParameter(5, requestDTO.getContent());
        query.setParameter(6, requestDTO.getDeadLine());
        query.setParameter(7, requestDTO.getTask());
        query.setParameter(8, id);

        query.executeUpdate();

        Query skillDeleteQueary = em.createNativeQuery("delete from skill_tb where  jobs_id = ?;");
        skillDeleteQueary.setParameter(1,id);

        skillDeleteQueary.executeUpdate();

        for (int i = 0; i < requestDTO.getSkill().size(); i++) {
            Query skillInsertQuery = em.createNativeQuery("insert into skill_tb(jobs_id,name,role) values(?,?,2)");
            skillInsertQuery.setParameter(1,id);
            skillInsertQuery.setParameter(2,requestDTO.getSkill().get(i));

            skillInsertQuery.executeUpdate();
        }

    }


    @Transactional
    public void deleteById (Integer compId,Integer jobId) {

        // 먼저 관련된 이력서 신청 삭제

        Query applyDeleteQuery = em.createNativeQuery("DELETE FROM apply_tb WHERE jobs_id = ?");
        applyDeleteQuery.setParameter(1, jobId);
        applyDeleteQuery.executeUpdate();

        // Query resumeDeleteQuery = em.createNativeQuery("delete from skill_tb")
        //스킬 테이블에 있는 jobId 찾아서 삭제
        Query skillDeleteQuery = em.createNativeQuery("delete from skill_tb where jobs_id = ?");
        skillDeleteQuery.setParameter(1,jobId);
        skillDeleteQuery.executeUpdate();

        Query jobDeleteQuery = em.createNativeQuery("delete from jobs_tb where user_id = ? AND id = ?");

        jobDeleteQuery.setParameter(1,compId);
        jobDeleteQuery.setParameter(2,jobId);

        jobDeleteQuery.executeUpdate();

        Query autoQuery = em.createNativeQuery("ALTER TABLE jobs_tb AUTO_INCREMENT = 1 SET @COUNT = 0;");

        Long newJobsid = (Long) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
    }

    public Object[] findByJobId(Integer jobId) {
        String q = """
                select 
                ut.comp_name, jt.user_id, ut.address, ut.phone, jt.area, jt.edu, jt.career, jt.content,
                jt.title, ut.homepage, jt.task, jt.dead_line, ut.business_number, ut.photo  
                from jobs_tb jt 
                join user_tb ut 
                on jt.user_id = ut.id 
                where jt.id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, jobId);
        Object[] job = (Object[]) query.getSingleResult();
        return job;
    }
}
