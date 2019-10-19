import { Component, OnInit } from '@angular/core';
import { HttpService } from '../services/httpservice.service';
import { Hospital } from '../classes/hospital';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hospital-display',
  templateUrl: './hospital-display.component.html',
  styleUrls: ['./hospital-display.component.css']
})
export class HospitalDisplayComponent implements OnInit {

  constructor(private displayHospital: HttpService, private activeRoute: ActivatedRoute) {
    // this.hospital = JSON.parse(this.activeRoute.snapshot.paramMap.get('hospital'));
  }

  hospital: Hospital;
  hospitals: Hospital[] = [];
  streetAddress: string;
  cityStateZip: string;
  // tslint:disable-next-line: variable-name
  private _searchTerm: string;
  filteredHospitals: Hospital[] = [];

    get searchTerm(): string {
        return this._searchTerm;
    }

    set searchTerm(value: string) {
      this._searchTerm = value;
      this.filteredHospitals = this.filterHospitals(value);
    }

    filterHospitals(searchString: string){
      return this.hospitals.filter(hospital =>
        hospital.name.toLowerCase().indexOf(searchString.toLowerCase()) !== -1 ||
        hospital.address.toLowerCase().indexOf(searchString.toLowerCase()) !== -1);
    }

  ngOnInit() {
    this.displayHospital.getHospitals()
    .subscribe
    (
      data => {
        this.hospitals = data;
        this.filteredHospitals = this.hospitals;
        console.log(this.hospitals);
        console.log(this.filteredHospitals);
      }
    );
  }
}
