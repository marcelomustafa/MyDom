import { BaseModel } from '@baseDomain/base-model';

export class UserModel extends BaseModel{
  username?: string;
  email?: string;
  firstName?: string;
  lastName?: string;
}