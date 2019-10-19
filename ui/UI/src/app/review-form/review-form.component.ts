import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ReviewsService } from '../services/reviews.service';
import { ActivatedRoute } from '@angular/router';
import { Hospital } from '../classes/hospital';
import { User } from '../classes/user';
import { ThrowStmt } from '@angular/compiler';

@Component({
// tslint:disable-next-line: component-selector
  selector: 'review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  myForm: FormGroup;
  hospital: Hospital;
  currentUser: User;

  constructor(
    private formBuilder: FormBuilder,
    private reviewServ: ReviewsService,
    private activeRoute: ActivatedRoute
  ) {
    this.hospital = JSON.parse(this.activeRoute.snapshot.paramMap.get('hospital'));
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(this.currentUser);
    this.createForm();
  }

  private createForm() {
    this.myForm = this.formBuilder.group({
      author: this.currentUser,
      hospital: this.hospital,
      title: '',
      body: '',
      overallRating: '',
      waitTime: '',
      staffResponsiveness: '',
      doctorCommunication: '',
      nurseCommunication: '',
      medicationCommunication: '',
      options: '',
      recommendations: ''
    });
  }

  private onSubmit() {
    console.log(this.myForm.value);
    this.reviewServ.postReview(this.myForm.value).subscribe(data => {
      console.log('Returning submit result');
      console.log(data);
    });
  }

  ngOnInit() {
  }

}
