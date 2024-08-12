package comkavita.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    String createJob(Job job);

    Job findById(Long id);

    String deleteById(Long id);

    boolean updateById(Long id, Job updatedJob);
}
