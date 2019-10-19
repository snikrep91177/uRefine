import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Hospital } from '../classes/hospital';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    const HOSPITALS = [
        {
            hospitalId: 1,
            name: 'default',
            address: null,
            img: null,
            specialty: null
        },
        {
            hospitalId: 2,
            name: 'Childrens National Medical Center',
            address: '111 Michigan Ave NW, Washington, DC 20010',
            img: null,
            specialty: null
        },
        {
            hospitalId: 3,
            name: 'George Washington University Hospital',
            address: '900 23rd St NW, Washington, DC 20037',
            img: null,
            specialty: null
        },
        {
            hospitalId: 4,
            name: 'Hospital for Sick Children',
            address: '1731 Bunker Hill Rd NE, Washington, DC 20017',
            img: null,
            specialty: null
        },
        {
            hospitalId: 5,
            name: 'Howard University Hospital',
            address: '2041 Georgia Ave NW, Washington, DC 20059',
            img: null,
            specialty: null
        },
        {
            hospitalId: 6,
            name: 'MedStar Georgetown University Hospital',
            address: '3800 Reservoir Rd NW, Washington, DC 20007',
            img: null,
            specialty: null
        },
        {
            hospitalId: 7,
            name: 'MedStar National Rehabilitation Hospital',
            address: '102 Irving Street NW, Washington, DC 20010',
            img: null,
            specialty: null
        },
        {
            hospitalId: 8,
            name: 'MedStar Washington Hospital Center',
            address: '110 Irving St. NW, Washington, DC 20010',
            img: null,
            specialty: null
        },
        {
            hospitalId: 9,
            name: 'Providence Hospital',
            address: '1150 Varnum Street NE, Washington, DC 20017',
            img: null,
            specialty: null
        },
        {
            hospitalId: 10,
            name: 'Psychiatric Institute of Washington',
            address: '4228 Wisconsin Avenue NW, Washington, DC',
            img: null,
            specialty: null
        },
        {
            hospitalId: 11,
            name: 'Sibley Memorial Hospital',
            address: '5255 Loughboro Rd NW, Washington, DC 20016',
            img: null,
            specialty: null
        },
        {
            hospitalId: 12,
            name: 'Specialty Hospital of Washington - Capitol Hill',
            address: '223 7th Street NE, Washington, DC 20002',
            img: null,
            specialty: null
        },
        {
            hospitalId: 13,
            name: 'Specialty Hospital of Washington - Hadley',
            address: '4601 Martin Luther King Jr. Ave SW, Washington, DC 20032',
            img: null,
            specialty: null
        },
        {
            hospitalId: 14,
            name: 'St. Elizabeths Hospital',
            address: '1100 Alabama Avenue SE, Washington, DC 20032',
            img: null,
            specialty: null
        },
        {
            hospitalId: 15,
            name: 'United Medical Center',
            address: '1310 Southern Avenue SE, Washington, DC 20032',
            img: null,
            specialty: null
        }
    ];
    return HOSPITALS;
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.

  genId(HOSPITALS: Hospital[]): number {
    return HOSPITALS.length > 0 ? Math.max(...HOSPITALS.map(hospital => hospital.hospitalId)) + 1 : 1;
  }

  constructor() { }
}
