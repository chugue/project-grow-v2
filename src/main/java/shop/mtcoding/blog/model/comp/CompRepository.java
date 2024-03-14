package shop.mtcoding.blog.model.comp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CompRepository {
    private final EntityManager em;


    public List<Jobs> findAll(Integer id){
           Query query = em.createNativeQuery("select * from jobs_tb where user_id = ?;", Jobs.class);
           query.setParameter(1,id);

           List<Jobs> jobsList = query.getResultList();

           return jobsList;
    }

    public List<Object[]> findAllByUserId() {
        String q = """
                    select
                    rt.id, ut.id as user_id, ut.my_name, rt.title, rt.edu, rt.career, rt.area, st.name , st.color
                    from resume_tb rt
                    join user_tb ut
                    on rt.user_id = ut.id
                    join skill_tb st
                    on rt.id = st.resume_id
                    order by rt.id;
                    """;
        Query query = em.createNativeQuery(q);

        List<Object[]> jobList = (List<Object[]>) query.getResultList();
        return jobList;

    }


    @Transactional
    public void updateById(int id, CompRequest.UpdateDTO requestDTO) {
        String q = """
                UPDATE user_tb
                SET my_name = ?, password = ?,  comp_name = ?, phone = ?,  address = ?, birth = ?,  homepage = ?
                WHERE id =?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getMyName());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getCompName());
        query.setParameter(4, requestDTO.getPhone());
        query.setParameter(5, requestDTO.getAddress());
        query.setParameter(6, requestDTO.getBirth());
        query.setParameter(7, requestDTO.getHomepage());
        query.setParameter(8, id);
        query.executeUpdate();

    }

    @Transactional
    public void save() {}

    @Transactional
    public void deleteById () {}

    public User findById(Integer id) {
        String q = """
                SELECT *
                FROM user_tb
                WHERE id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);
       return (User) query.getSingleResult();
    }
}
