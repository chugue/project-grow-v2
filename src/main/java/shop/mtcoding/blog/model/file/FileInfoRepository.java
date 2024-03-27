package shop.mtcoding.blog.model.file;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class FileInfoRepository {
    private final EntityManager em;

    @Transactional
    public void insert(String title, String imgFilename){
        Query query = em.createNativeQuery("insert into fileinfo_tb(title, img_filename) values(?,?)");
        query.setParameter(1, title);
        query.setParameter(2, imgFilename);

        query.executeUpdate();
    }

    public FileInfo findById(int id){
        Query query = em.createNativeQuery("select * from fileinfo_tb where id = ?", FileInfo.class);
        query.setParameter(1, id);

        return (FileInfo) query.getSingleResult();
    }
}