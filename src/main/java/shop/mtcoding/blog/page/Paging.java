package shop.mtcoding.blog.page;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Component;
import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeResponse;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Data
@Component
public class Paging {
    private final JobsRepository jobsRepository;
    private final ResumeRepository resumeRepository;

    final int SHOW_PAGES = 9; //화면에 표현하고 싶은 게시물개수 (이력서 or 공고)
    int currentPage;
    int prevPage;
    int nextPage;

    public boolean firstPage(int page) {
        boolean firstPage;
        currentPage = page;
        prevPage = currentPage - 1;
        firstPage = prevPage == 0;
        return firstPage;
    }

    public boolean lastPage(int page, int totalPosts) {
        boolean lastPage;
        currentPage = page - 1;
        lastPage = (totalPosts - (SHOW_PAGES * currentPage)) < SHOW_PAGES;
        return lastPage;
    }

    public List<JobResponse.DTO> showPagesV2(int page, List<JobResponse.DTO> pageList) {
        currentPage = page;
        ArrayList<JobResponse.DTO> resumeList = new ArrayList<>();
        int totalPosts = pageList.size();
        int start = (SHOW_PAGES * currentPage) - SHOW_PAGES;
        int end = SHOW_PAGES * currentPage;

        // 현재 페이지에 필요한 공고만 조회해서 출력
        for (int j = start; j < end; j++) {
            if (j >= totalPosts) {
                break;
            }
            resumeList.add(pageList.get(j));
        }
        return resumeList;
    }

    public List<JobResponse.DTO> showPagesV2(int page, String keyword, List<JobResponse.DTO> pageList) {
        currentPage = page;
        ArrayList<JobResponse.DTO> jobsList = new ArrayList<>();
        int totalPosts = pageList.size();
        int start = (SHOW_PAGES * currentPage) - SHOW_PAGES;
        int end = SHOW_PAGES * currentPage;

        for (int j = start; j < end; j++) {
            if (j >= totalPosts) {
                break;
            }
            jobsList.add(pageList.get(j));
        }
        return jobsList;
    }

    public List<ResumeResponse.ResumeUserDTO> compShowPages(int page, List<ResumeResponse.ResumeUserDTO> pageList) {
        currentPage = page;
        ArrayList<ResumeResponse.ResumeUserDTO> jobsList = new ArrayList<>();
        int totalPosts = pageList.size();
        int start = (SHOW_PAGES * currentPage) - SHOW_PAGES;
        int end = SHOW_PAGES * currentPage;

        // 현재 페이지에 필요한 공고만 조회해서 출력
        for (int j = start; j < end; j++) {
            if (j >= totalPosts) {
                break;
            }
            jobsList.add(pageList.get(j));
        }
        return jobsList;
    }

    public List<ResumeResponse.ResumeUserDTO> compShowPagesV2(int page, List<ResumeResponse.ResumeUserDTO> pageList) {
        currentPage = page;
        ArrayList<ResumeResponse.ResumeUserDTO> resumeList = new ArrayList<>();
        int totalPosts = pageList.size();
        int start = (SHOW_PAGES * currentPage) - SHOW_PAGES;
        int end = SHOW_PAGES * currentPage;

        // 현재 페이지에 필요한 공고만 조회해서 출력
        for (int j = start; j < end; j++) {
            if (j >= totalPosts) {
                break;
            }
            resumeList.add(pageList.get(j));
        }
        return resumeList;
    }


    public int totalPages(int totalPosts) {
        int remainder = totalPosts % SHOW_PAGES;
        int division = totalPosts / SHOW_PAGES;
        return (remainder <= SHOW_PAGES) ? division + 1 : division;
    }


    public boolean complastPage(int currentPage, int totalPosts) {
        boolean lastPage;
        currentPage = currentPage - 1;
        lastPage = (totalPosts - (SHOW_PAGES * currentPage)) < SHOW_PAGES;
        return lastPage;
    }

    public boolean compfirstPage(int page) {
        boolean firstPage;
        currentPage = page;
        prevPage = currentPage - 1;
        firstPage = prevPage == 0;
        return firstPage;
    }

    public int compTotalPages(int totalPosts) {
        List<Resume> pageList = resumeRepository.findAll();
        int remainder = totalPosts % SHOW_PAGES; //3
        int division = totalPosts / SHOW_PAGES; // 1
        int totalPages = (remainder <= SHOW_PAGES) ? division + 1 : division;

        return totalPages;
    }

}
