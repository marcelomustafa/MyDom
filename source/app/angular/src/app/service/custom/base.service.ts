import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export abstract class BaseService {
  
  protected apiUrl = 'http://localhost:8080';

  protected httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };  

  constructor(
    private httpClient: HttpClient
  ) { }
  
  public getURI(): String{
    return this.apiUrl;

  }
}
