import { User } from './user';
import { Hospital } from './hospital';

export class Review {
  reviewId: number;
  author: User;
  hospital: Hospital;
  title: string;
  body: string;
  overallRating: number;
  waitTime: string;
  staffResponsiveness: number;
  doctorCommunication: number;
  nurseCommunication: number;
  medicationCommunication: number;
  options: number;
  recommendations: number;
  datePosted: number;
  upvotes: User[];
}
