import { Component, OnInit } from '@angular/core';
import { ReviewsByHospitalService } from '../services/reviews-by-hospital.service';
import { Review } from '../classes/review';
import { ActivatedRoute } from '@angular/router';
import { Hospital } from '../classes/hospital';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReviewsService } from '../services/reviews.service';
import { User } from '../classes/user';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-review-by-hospital',
  templateUrl: './review-by-hospital.component.html',
  styleUrls: ['./review-by-hospital.component.css'],
  providers: [NgbRatingConfig]
})
export class ReviewByHospitalComponent implements OnInit {

  hospital: Hospital;
  review: Review;
  name: string;
  myForm: FormGroup;
  reviews: Review[];
  reviewsLen;
  currentUser: User;
  submitAnonymously: boolean;
  specializations: string[];

  private index;

  constructor(
    private reviewsByHospital: ReviewsByHospitalService,
    private activeRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private reviewServ: ReviewsService,
    private ratingConfig: NgbRatingConfig) {
    this.hospital = JSON.parse(this.activeRoute.snapshot.paramMap.get('hospital'));
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.createForm();
    console.log(this.hospital);
    this.name = this.hospital.name;
    this.ratingConfig.max = 5;
    this.ratingConfig.readonly = false;
    this.specializations =
      [
      'Adult Intensivist',
      'Allergy',
      'Anesthesia',
      'Bariatric Medicine/Surgery',
      'Burn /Trauma',
      'Cardiac Catheterization',
      'Cardiology',
      'Cardiovascular Surgery',
      'Colorectal Surgery',
      'Dermatology',
      'Electrophysiology',
      'Emergency Medicine',
      'Endocrinology',
      'Family Practice',
      'Gastroenterology',
      'General Surgery',
      'Geriatrics',
      'Gynecologic Oncology',
      'Hematology/Oncology',
      'Hepatobiliary',
      'Hospitalist',
      'Infectious Disease',
      'Internal Medicine',
      'Interventional Radiology',
      'Medical Genetics',
      'Neonatology',
      'Nephrology',
      'Neuroradiology',
      'Neurology',
      'Neurosurgery',
      'Nuclear Medicine',
      'Obstetrics & Gynecology',
      'Occupational Medicine',
      'Ophthalmology',
      'Oral Surgery',
      'Orthopedics',
      'Otolaryngology/Head & Neck Surgery',
      'Pain Management',
      'Palliative Care',
      'Pain Management',
      'Palliative Care',
      'Pathology: Surgical & Anatomic',
      'Pediatric Intensivist',
      'Pediatrics',
      'Pediatric Surgery',
      'Physical Medicine',
      'Plastic & Reconstructive Surgery',
      'Podiatric Surgery',
      'Psychiatry',
      'Pulmonary Medicine',
      'Radiation Oncology',
      'Radiology',
      'Rheumatology',
      'Surgical Oncology',
      'Thoracic Surgery',
      'Transplant Surgery',
      'Urology',
      'Vascular Surgery',
      'Wound Care'
      ];
  }

  ngOnInit() {
    this.reviewsByHospital.getReviewsByHopital(this.hospital)
      .subscribe
      (
        (data: Review[]) => {
          this.reviews = data;
          this.reviewsLen = data.length;
          console.log(this.reviews);
          console.log(this.reviews[0].author.username);
        }
      );

  }

  private createForm() {
    this.myForm = this.formBuilder.group({
      author: this.currentUser ? this.currentUser.userId : null,
      hospital: this.hospital.hospitalId,
      title: '',
      body: '',
      overallRating: '',
      waitTime: '',
      staffResponsiveness: '',
      doctorCommunication: '',
      nurseCommunication: '',
      medicationCommunication: '',
      options: '',
      recommendations: '',
      specializationRating: '',
      specialization: ''
    });
  }

  private onSubmit() {
    if (this.submitAnonymously) {
      this.myForm.value.author = null;
    }
    console.log(this.myForm.value);
    this.reviewServ.postReview(this.myForm.value).subscribe(data => {
      console.log('Returning submit result');
      console.log(data);
      window.location.reload();
    });
  }

  private upvoteReview(review) {
    console.log(review.upvotes);
    console.log(this.currentUser);
    this.index = review.upvotes.findIndex(user => user.userId === this.currentUser.userId);
    if (this.index <= -1) {
      // console.log(this.index);
      review.upvotes.push(this.currentUser);
    } else {
      const user = review.upvotes[this.index];
      review.upvotes = review.upvotes.filter(obj => obj !== user);
    }
    console.log(review.upvotes.length);
    this.reviewsByHospital.updateReview(review).subscribe(data => {
      console.log(data);
    });
  }

}
