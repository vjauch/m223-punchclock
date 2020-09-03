import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EntryModel} from "../domain/Entry.model";

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  constructor(private httpClient: HttpClient) { }

  public getAllEntries(): Observable<EntryModel[]> {
    return this.httpClient.get<EntryModel[]>('http://localhost:8081/entries');
  }

}
