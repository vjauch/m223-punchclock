import {UserModel} from "./User.model";
import {ActivityModel} from "./Activity.model";

export class EntryModel {
  checkIn: Date;
  checkOut: Date;
  user: UserModel;
  activity: ActivityModel;
}

