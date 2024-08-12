package comkavita.firstjobapp.job.Impl;

import comkavita.firstjobapp.job.Job;
import comkavita.firstjobapp.job.JobRepository;
import comkavita.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs=new ArrayList<Job>();
    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public String createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
        return "Job Added Successfully!";
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return "Deleted Successfully!";
        }catch(Exception exc){
            return null;
        }
    }

    @Override
    public boolean updateById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);

            if (jobOptional.isPresent()) {
                Job job=jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setLocation(updatedJob.getLocation());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
