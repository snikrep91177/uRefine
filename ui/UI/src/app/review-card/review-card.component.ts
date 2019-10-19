import { Component, OnInit } from '@angular/core';

@Component({
// tslint:disable-next-line: component-selector
  selector: 'review-card',
  templateUrl: './review-card.component.html',
  styleUrls: ['./review-card.component.css']
})
export class ReviewCardComponent implements OnInit {

  submitDate: Date = new Date();
  sampleDate = 'July 7, 2019';
  constructor() { }

  ngOnInit() { }
}
