import { Component, AfterViewInit, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';

import { LoginService } from 'app/core/login/login.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login.component.html',
})
export class LoginModalComponent implements AfterViewInit {
  @ViewChild('username', { static: false })
  username?: ElementRef;

  @ViewChild('password')
  password?: ElementRef;

  authenticationError = false;

  @Output() loginSuccess = new EventEmitter<any>();

  loginForm = this.fb.group({
    username: [''],
    password: [''],
    rememberMe: [false],
  });

  constructor(private loginService: LoginService, private fb: FormBuilder) {}

  ngAfterViewInit(): void {
    if (this.username) {
      this.username.nativeElement.focus();
    }
  }

  // cancel(): void {
  //   this.authenticationError = false;
  //   this.loginForm.patchValue({
  //     username: '',
  //     password: '',
  //   });
  // }

  login(): void {
    this.loginService
      .login({
        username: this.loginForm.get('username')!.value,
        password: this.loginForm.get('password')!.value,
        rememberMe: false,
      })
      .subscribe(
        () => {
          this.loginSuccess.emit(this.loginForm.get('username')!.value);
          this.authenticationError = false;
        },
        () => {
          alert('Login failed!');
          this.authenticationError = true;
        }
      );
  }

  // login(): void {
  //   const username = this.loginForm.get('username')!.value;
  //   const password = this.loginForm.get('password')!.value;

  //   this.loginService
  //     .login({
  //       username: username,
  //       password: password,
  //       rememberMe: false
  //     })
  //     .subscribe(
  //       () => {
  //         this.loginSuccess.emit(username);
  //       },
  //     );
  // }
}
