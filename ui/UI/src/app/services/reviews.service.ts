import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ReviewsService {

  constructor(private httpClient: HttpClient) { }

  postReview(review) {
    return this.httpClient.post('http://localhost:8765/review-service/reviews/postReview', review);
  }

}
