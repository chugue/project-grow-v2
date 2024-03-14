package shop.mtcoding.blog.model.resume;

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
public class ResumeRepository {
    private final EntityManager em;

    public List<ResumeResponse.ResumeUserDTO> findAllWithUserV2() {
        String q = """
                select rt.id, rt.user_id, rt.title, rt.area, rt.edu, rt.career, rt.introduce, rt.port_link, rt.created_at, ut.my_name 
                from resume_tb rt inner join user_tb ut 
                on rt.user_id = ut.id 
                order by rt.id desc 
                """;

        Query query = em.createNativeQuery(q);
        // 엔티티랑 다른 모양의 쿼리를 직접 DTO로 만들어서 매핑하기 위한 툴
        JpaResultMapper mapper = new JpaResultMapper();
        // mapper.list ( 쿼리, 결과를 위한 DTO)로 만들고 결과가 여러개라면 List<DTO>로 생산후 반환
        List<ResumeResponse.ResumeUserDTO> result = mapper.list(query, ResumeResponse.ResumeUserDTO.class);
        return result;
    }

    public List<ResumeResponse.ResumeUserDTO> findAllWithUserV2(String keyword) {
        String q = """
                select rt.*, ut.my_name 
                from resume_tb rt inner join user_tb ut 
                on rt.user_id = ut.id 
                where rt.title like ? or ut.my_name like ? order by rt.id desc ;
                """;

        Query query = em.createNativeQuery(q, Resume.class);
        query.setParameter(1, "%" + keyword + "%");
        query.setParameter(2, "%" + keyword + "%");

        JpaResultMapper mapper = new JpaResultMapper();
        List<ResumeResponse.ResumeUserDTO> result = mapper.list(query, ResumeResponse.ResumeUserDTO.class);

        return result;
    }


    public List<Resume> findAll() {
        String q = """
                select * from resume_tb order by id desc
                """;

        Query query = em.createNativeQuery(q, Resume.class);
        List<Resume> resumeList = query.getResultList();

        return resumeList;
    }


    public List<Object[]> findAll(Integer userId) {


        String q = """
                SELECT r.id, r.user_Id, r.title, r.edu, r.area, s.resume_Id,r.career, s.name 
                FROM resume_tb r
                inner join user_tb u
                ON r.user_id = u.id
                inner join skill_tb s
                on r.id = s.resume_id
                where u.id = ?;
                """;


        Query query = em.createNativeQuery(q);

        query.setParameter(1, userId);

        List<Object[]> resumeList = query.getResultList();

        return resumeList;
    }

    public Resume findById(Integer id) {
        String q = """
                select * from resume_tb where id = ?
                """;
        Query query = em.createNativeQuery(q, Resume.class);
        query.setParameter(1, id);

        Resume resume = (Resume) query.getSingleResult();
        return resume;
    }

    public Object[] findByResumeId(Integer resumeId) {
        String q = """
                select 
                rt.id, ut.my_name, ut.address, ut.phone, ut.email, 
                ut. birth, rt.edu, rt.career, rt.introduce, rt.title, 
                rt.port_link, rt.area 
                from resume_tb rt 
                join user_tb ut 
                on rt.user_id = ut.id 
                where rt.id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        Object[] resume = (Object[]) query.getSingleResult();
        return resume;
    }

    public List<ResumeRequest.resumeTitleIdDTO> findByResumeTitle(Integer userId) {
        Query query = em.createNativeQuery("select id, title from resume_tb where user_id = ?");
        query.setParameter(1, userId);
        JpaResultMapper mapper = new JpaResultMapper();
        List<ResumeRequest.resumeTitleIdDTO> list = mapper.list(query, ResumeRequest.resumeTitleIdDTO.class);
        return list;
    }

    // DTO 타입으로 스킬리스트빼고 전부 들고오는 매서드
    public List<ResumeRequest.UserViewDTO> findAllUserId(Integer userId) {
        Query query = em.createNativeQuery("select rt.id ,rt.title, rt.edu, rt.career, rt.area from resume_tb rt  where user_id =?");
        query.setParameter(1, userId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<ResumeRequest.UserViewDTO> result = mapper.list(query, ResumeRequest.UserViewDTO.class);

        return result;
    }

    //SkillDTO 타입으로 이력서테이블에 들어가있는 스킬 찾는매서드
    public List<SkillRequest.ResumeSkillDTO> findAllByResumeId(Integer id) {
        Query query = em.createNativeQuery(" select st.name,st.color from skill_tb st inner join resume_tb rt on st.resume_id = rt.id where rt.id =?");
        query.setParameter(1, id);

        JpaResultMapper mapper = new JpaResultMapper(); // 이거안쓰면 DTO 타입으로 못받아오고 Object 로 가져와야함
        List<SkillRequest.ResumeSkillDTO> resumeSKillList = mapper.list(query, SkillRequest.ResumeSkillDTO.class);

        return resumeSKillList;
    }




    // 탬플릿에서 유저 못찾고 있는데 ..
    @Transactional
    public void save(ResumeRequest.ResumeWriterDTO requestDTO) {
        String q = """
                insert into resume_tb(id,user_id,title, area, edu, career, introduce, port_link, created_at) 
                values (?,?,?,?,?,?,?,?,now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getId());
        query.setParameter(2, requestDTO.getUserId());
        query.setParameter(3, requestDTO.getTitle());
        query.setParameter(4, requestDTO.getArea());
        query.setParameter(5, requestDTO.getEdu());
        query.setParameter(6, requestDTO.getCareer());
        query.setParameter(7, requestDTO.getIntroduce());
        query.setParameter(8, requestDTO.getPortLink());

        query.executeUpdate();

        Long newResumeid = (Long) em.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();

        //스킬 insert
        for (int i = 0; i < requestDTO.getSkill().size(); i++) {
            Query addSkillquery = em.createNativeQuery("insert into skill_tb(role,resume_id,name) values (1,?,?)");
            addSkillquery.setParameter(1, newResumeid);
            addSkillquery.setParameter(2, requestDTO.getSkill().get(i));

            addSkillquery.executeUpdate();
        }
    }


    // yz/0305 이력서 삭제부분
    @Transactional
    public void deleteById(Integer id) {
        Query query = em.createNativeQuery("delete from resume_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    // yz/0305 이력서 수정부분
    @Transactional
    public void updateById(ResumeRequest.ResumeUpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update resume_tb set area = ?, career = ?, edu = ?, introduce = ?, port_link = ?, title = ? where id = ?");
        query.setParameter(1, requestDTO.getArea());
        query.setParameter(2, requestDTO.getCareer());
        query.setParameter(3, requestDTO.getEdu());
        query.setParameter(4, requestDTO.getIntroduce());
        query.setParameter(5, requestDTO.getPortLink());
        query.setParameter(6, requestDTO.getTitle());
        query.setParameter(7, id);

        query.executeUpdate();

        Query skillDeleteQueary = em.createNativeQuery("delete from skill_tb where resume_id = ?;");
        skillDeleteQueary.setParameter(1,id);

        skillDeleteQueary.executeUpdate();

        for (int i = 0; i < requestDTO.getSkill().size(); i++) {
            Query skillInsertQuery = em.createNativeQuery("insert into skill_tb(resume_id, name, role) values(?,?,2)");
            skillInsertQuery.setParameter(1,id);
            skillInsertQuery.setParameter(2,requestDTO.getSkill().get(i));

            skillInsertQuery.executeUpdate();
        }
    }


}