import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Directive, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export abstract class BaseService {
  
  protected abstract getUrl(): string;

  protected httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };  

  constructor(
    public http: HttpClient
  ) { }

  

}
