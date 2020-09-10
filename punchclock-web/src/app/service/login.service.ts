import {Injectable} from '@angular/core';
import {CredentialsModel} from "../domain/Credentials.model";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {LocalStorageService} from "./local-storage.service";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient,
              private localstorageService: LocalStorageService) {
  }

  public login(credentials: CredentialsModel): Observable<HttpResponse<void>> {
    return this.httpClient.post<void>(environment.apiBasePath + 'login', credentials, {observe: 'response'});
  }

  public setToken(bearerToken: string): void {
    const token = bearerToken.replace('Bearer ', '');
    this.localstorageService.storeToken(token);
  }

  public getToken(): string {
    return this.localstorageService.getToken();
  }

  public isAuthenticated(): boolean {
    return this.localstorageService.getToken() != null;
  }

}
