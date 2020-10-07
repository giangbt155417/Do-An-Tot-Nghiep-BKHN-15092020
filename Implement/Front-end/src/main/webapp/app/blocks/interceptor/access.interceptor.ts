import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CSRFService } from '../../core/auth/csrf.service';
import { retry } from 'rxjs/operators';
import { Injectable } from '@angular/core';

@Injectable()
export class AccessInterceptor implements HttpInterceptor {
  constructor(public csrfService: CSRFService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let csrfToken = this.csrfService.getCSRF();
    req = req.clone({ setHeaders: { 'X-XSRF-TOKEN': csrfToken } });
    return next.handle(req).pipe(retry(1));
  }
}
