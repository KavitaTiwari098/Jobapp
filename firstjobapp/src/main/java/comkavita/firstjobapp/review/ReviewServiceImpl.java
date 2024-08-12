package comkavita.firstjobapp.review;

import comkavita.firstjobapp.company.Company;
import comkavita.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company=companyService.findById(companyId);
        if(company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if(companyService.findById(companyId)!=null && reviewRepository.existsById(reviewId)){
            Review review =reviewRepository.findById(reviewId).orElse(null);
            Company company=review.getCompany();
            companyService.updateById(companyId,company);
            review.setCompany(null);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
      if(companyService.findById(companyId)!=null){
          updatedReview.setCompany(companyService.findById(companyId));
          updatedReview.setId(reviewId);
          reviewRepository.save(updatedReview);
        return true;
        }
        return false;
    }
}
