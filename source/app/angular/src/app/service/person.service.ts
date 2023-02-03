import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { BaseService } from './custom/base.service';

@Injectable({
  providedIn: 'root'
})
export class PersonService extends BaseService {

  protected getUrl(): string {
    return `${environment.urlApi}/persons`
  }

  constructor(
    public override http: HttpClient
  ){
    super(http);
  }
  


  public getPersons(): Observable<any>{
    return this.http.get<any>(this.getUrl()) 
  }

  public postPersons(person: any):Observable<any>{
    return this.http.post<any>(this.getUrl(), person, this.httpOptions);
  }

}
