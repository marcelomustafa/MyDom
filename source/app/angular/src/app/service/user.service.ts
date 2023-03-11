import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BaseService } from '@baseService/base.service';
import { UserModel } from '@domain/model/user-model';
import { environment } from '@environments/environment';
@Injectable({
  providedIn: 'root'
})
export class UserService extends BaseService<UserModel> {

  protected override getUrl(): string {
    return `${environment.apiUrl}/users`
  }

  constructor(
    public override http: HttpClient
  ){
    super(http);
  }
  
}
