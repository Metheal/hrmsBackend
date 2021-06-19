package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.JobPosting;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {
    List<JobPosting> getAllByActiveIsTrue();

    List<JobPosting> getAllByActiveIsTrue(Sort sort);

    List<JobPosting> getAllByActiveAndEmployer_Id(Boolean active, int id);

    List<JobPosting> getAllByActiveAndCity_Name(Boolean active, String name);
}
