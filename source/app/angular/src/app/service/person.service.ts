import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PersonModel } from '@domain/model/person-model';
import { BaseService } from '@baseService/base.service';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import { Pageable } from '@app/shared/data-type/pageable.interface';
import { Page } from '@app/shared/data-type/page.interface';
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

  public  realizarRequisicao(array: any[], pageable: Pageable): Observable<Page<any>> | any {   
    const url = `${this.getUrl()}/pesquisa-paginada${this.getPageablePrams(pageable)}`;
    this.http.get<Page<any>>(url,this.httpOptions)
      .subscribe(
        (response) => {
          return response; // Manipule a resposta da requisição HTTP aqui
        },
        (error) => {
          return null;// Manipule os erros da requisição HTTP aqui
        }
      );
  } 

}
