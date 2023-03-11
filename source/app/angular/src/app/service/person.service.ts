import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PersonModel } from '@domain/model/person-model';
import { BaseService } from '@baseService/base.service';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PersonService extends BaseService<PersonModel> {

  protected getUrl(): string {
    return `${environment.apiUrl}/persons`
  }

  constructor(
    public override http: HttpClient
  ){
    super(http);
  }


  public postPersons(person: PersonModel):Observable<PersonModel>{
    return this.http.post<PersonModel>(this.getUrl(), person, this.httpOptions);
  }

}
