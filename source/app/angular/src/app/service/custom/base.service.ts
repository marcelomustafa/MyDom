import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pageable } from '@app/shared/data-type/pageable.interface';
import { Observable } from 'rxjs';

@Injectable()
export abstract class BaseService<T> {
  
  protected abstract getUrl(): string;

  protected httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };  

  constructor(
    public http: HttpClient
  ) { }

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(`${this.getUrl()}/all`);
  }

  protected getPageablePrams(pageable: Pageable): string  {
    let params = "";
    let variableUrlMark = "?";
    if(pageable){
      let variableMark = "";
      if (pageable.page) {
        params += variableUrlMark + variableMark + `page=${pageable.sort}`;
        variableUrlMark = "";
        variableMark = "&";
      }	
      if (pageable.page) {
        params += variableUrlMark + variableMark + `size=${pageable.size}`;
        variableUrlMark = "";
        variableMark = "&";
      }	
      if (pageable.sort) {
        params += variableUrlMark + variableMark + `sort=${pageable.sort}`;
        variableUrlMark = "";
        variableMark = "&";
      }	
    }
    return params;
  }

  protected getObjectParams(object: any): string {
    let paramsx = "";

    const params = new HttpParams();

    for (const key in object) {
      if (object.hasOwnProperty(key)) {
        params.set(key, object[key]);
      }
    }

    // const queryParams = myObjects.map((obj, index) => {
    //   const params = Object.entries(obj).map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`);
    //   return params.join('&');
    // }).join('&');

    // const queryParams = myArray.map((item, index) => {
    //   const paramName = `param${index + 1}`;
    //   return `${paramName}=${item}`;
    // }).join('&');


    return "";
  }

  protected getArrayObjectParams(object: any[]): string {
    let params = "";
    params = this.getObjectParams(null);
    return params;
  }  

}