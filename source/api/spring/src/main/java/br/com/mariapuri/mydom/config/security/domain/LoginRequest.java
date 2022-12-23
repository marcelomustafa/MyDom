package br.com.mariapuri.mydom.config.security.domain;

import javax.validation.constraints.NotNull;

//@NoArgsConstructor
//@Getter
//@Setter
public class LoginRequest {
 
    @NotNull
    private String userName;
 
    @NotNull
    private String password;
    
    LoginRequest(){
    	
    }

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
    
    
}
