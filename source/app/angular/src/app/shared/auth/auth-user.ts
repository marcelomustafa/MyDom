import { BaseModel } from "@app/domain/custom/base-model";
import { AuthRole } from "@auth/auth-role";

export class AuthUser extends BaseModel{
  username?: string;
  email?: string;
  roles?: AuthRole[];
  firstName?: string;
  lastName?: string;
  token?: string;
}