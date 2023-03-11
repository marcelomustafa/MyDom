import { BaseModel } from "@app/domain/custom/base-model";

export class AuthRole extends BaseModel{
  name?: string;
  description?: string;
}