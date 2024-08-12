package comkavita.firstjobapp.review;

import comkavita.firstjobapp.company.Company;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);

    boolean createReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean deleteReviewById(Long companyId, Long reviewId);

    boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview);
}
