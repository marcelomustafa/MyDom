import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseService } from './custom/base.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService extends BaseService {

  apiResource = '/persons';

  constructor(
    httpClient: HttpClient
  ){
    super(httpClient);
  }
  
  public override getURI(): string{
    return this.apiUrl + this.apiResource;
  }

  public getPersons(): Observable<ResponsePageable >{
    return this.httpClient.get<ResponsePageable>(this.getURI()) 
  }

  public postPersons(person: any):Observable<any>{
    return this.httpClient.post<any>(this.getURI(), person, this.httpOptions);
  }

}
