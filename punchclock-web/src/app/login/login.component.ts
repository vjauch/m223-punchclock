import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CredentialsModel} from "../domain/Credentials.model";
import {LoginService} from "../service/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: '',
      password: ''
    })
  }

  login(credentials: CredentialsModel) {
    this.loginService.login(credentials).subscribe(response => {
      const bearer = response.headers.get('Authorization');
      this.loginService.setToken(bearer);
      this.router.navigateByUrl('');
    });
  }
}
