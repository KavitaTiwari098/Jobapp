package comkavita.firstjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findCompanies(){
            return new ResponseEntity<>(companyService.findAll(),HttpStatus.OK);
    }

   @PostMapping
   public ResponseEntity<String> createCompany(@RequestBody Company company){
       return new ResponseEntity<>(companyService.createCompany(company),HttpStatus.CREATED);
   }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long companyId){
        Company company=companyService.findById(companyId);
        if(company!=null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long companyId, @RequestBody Company company){
        boolean updated=companyService.updateById(companyId, company);
        if(updated){
            return new ResponseEntity<>("Updated Successfully!!",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long companyId){
        boolean deleted=companyService.deleteById(companyId);
        if(deleted){
            return new ResponseEntity<>("Deleted Successfully!!",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
