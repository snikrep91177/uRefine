import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hospital } from '../classes/hospital';
import { Review } from '../classes/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewsByHospitalService {

  constructor(private httpClient: HttpClient) { }

  getReviewsByHopital(hospital: Hospital): Observable<any> {
    return this.httpClient.post(`http://localhost:8765/review-service/reviews/findByHospital`, hospital);
  }

  updateReview(review: Review) {
    return this.httpClient.post('http://localhost:8765/review-service/reviews/updateReview', review);
  }
}
