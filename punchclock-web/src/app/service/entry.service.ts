import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EntryModel} from "../domain/Entry.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class EntryService {

  constructor(private httpClient: HttpClient) { }

  public getAllEntries(): Observable<EntryModel[]> {
    return this.httpClient.get<EntryModel[]>(environment.apiBasePath + 'entries');
  }

  public deleteEntry(id: number): Observable<void> {
    return this.httpClient.delete<void>(environment.apiBasePath + 'entries/' + id);
  }
}
