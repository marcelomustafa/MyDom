package br.com.mariapuri.mydom.config.security.domain;

//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import br.com.mariapuri.mydom.app.domain.model.UserModel;
//
//
////@AllArgsConstructor
////@NoArgsConstructor
//public class UserSecurity extends UserModel implements UserDetails {
//	private static final long serialVersionUID = 1L;
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		//var roleMapper = new ModelMapper();
//		
//				
////		this.getRoles().stream()
////			.forEach(item -> rolesSecurity.add(roleMapper.map(item, RoleSecurity.class)));
//		
////		var roleMapper = new ModelMapper();
//////		//var
////		List<RoleSecurity> rolesSecurity = this.getRoles().stream()
////				.map(item -> roleMapper.map(item, RoleSecurity.class))
////				.toList() ;
////		
////		//Collection<RoleSecurity> roleSecurity = Collection
////		
//		 return getRoles();
//		
//	}
//
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return super.getUserName();
//	}
//
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	
//	
//	
//	
////	@Override
////	public String getUsername() {
////		return this.getUserName();
////	}
////
////	@Override
////	public boolean isAccountNonExpired() {
////		return true;
////	}
////
////	@Override
////	public boolean isAccountNonLocked() {
////		return true;
////	}
////
////	@Override
////	public boolean isCredentialsNonExpired() {
////		return true;
////	}
////
////	@Override
////	public boolean isEnabled() {
////		return true;
////	}
//
//}
