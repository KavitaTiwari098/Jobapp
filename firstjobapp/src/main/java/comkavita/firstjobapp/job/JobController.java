package comkavita.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;
    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        return ResponseEntity.ok(jobService.createJob(job));
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findAll(@PathVariable Long id){
        Job job= jobService.findById(id);
        if(job!=null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
     String status=jobService.deleteById(id);
     if(status!=null)
     return new ResponseEntity<>("Deletion Successful",HttpStatus.OK);
     else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,  @RequestBody Job updatedJob){
        boolean updated = jobService.updateById(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("Updated Successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
