package br.com.mariapuri.mydom.config.securityCookie.JWTAuthentication.JWTAuthentication_01;


//import br.com.mariapuri.mydom.app.domain.model.UserModel;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.annotation.ManagedBean;
//import javax.servlet.http.HttpSession;
//import java.io.Serializable;
//
////@Getter
////@Setter
//@ManagedBean
//@SessionScoped
//public class userSession implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	private User user = new User();
//
//	public String validateUsernamePassword() {
//		String login = "matheus";
//		String senha = "1234";
//
//		if (login.equals(user.getUserName()) && senha.equals(user.getPassword())) {
//			HttpSession session = SessionBean.getSession();
//			session.setAttribute("username", user.getUserName());
//			return "menu";
//		} else {
//			return "login";
//		}
//	}
//
//	public String logout() {
//		HttpSession session = SessionBean.getSession();
//		session.invalidate();
//		return "login";
//
//	}
//}
