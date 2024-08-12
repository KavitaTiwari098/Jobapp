package comkavita.firstjobapp.review;

import jakarta.persistence.GeneratedValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findReviewsByCompanyId(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK) ;
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReviews(@PathVariable Long companyId, @RequestBody Review review){
        boolean created= reviewService.createReview(companyId,review);
        if(created){
            return new ResponseEntity("Review Created Successfully!!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company with given id doesn't exists",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> findReviewsByReviewId(@PathVariable Long companyId, @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId,reviewId), HttpStatus.OK) ;
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewsById(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review){
        boolean updated =reviewService.updateReviewById(companyId,reviewId,review);
        if(updated){
            return new ResponseEntity<>("Review Updated Successfully!!", HttpStatus.OK);
        }
         return new ResponseEntity<>("Company with given id doesn't exists",HttpStatus.NOT_FOUND) ;
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewsById(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean deleted =reviewService.deleteReviewById(companyId,reviewId);
        if(deleted){
            return new ResponseEntity<>("Review Deleted Successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company with given id doesn't exists",HttpStatus.NOT_FOUND) ;
    }
}
