import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private httpClient: HttpClient) { }

  getHospitals(): Observable<any> {
    return this.httpClient.get('http://localhost:8765/review-service/hospitals/allHospitals');
   }
}
