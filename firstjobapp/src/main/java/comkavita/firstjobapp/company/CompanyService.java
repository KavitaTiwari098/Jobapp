package comkavita.firstjobapp.company;

import comkavita.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    String createCompany(Company company);

    Company findById(Long id);

    boolean deleteById(Long id);

    boolean updateById(Long id, Company updatedCompany);
}
