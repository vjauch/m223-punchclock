import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {ProjectModel} from "../domain/Project.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private httpClient: HttpClient) { }

  public getProjects(): Observable<ProjectModel[]> {
    return this.httpClient.get<ProjectModel[]>(environment.apiBasePath + 'projects');
  }
}

