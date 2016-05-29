/**
 * 
 */
package com.mycloud.store.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mycloud.entity.Role;
import com.mycloud.entity.User;

/**
 * @author Shawn
 *
 */
public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 5032413811106994728L;

	public CustomUserDetails(User user) {
		if(user != null){
			this.setId(user.getId());
			this.setName(user.getName());
			this.setUsername(user.getUsername());
			this.setEmail(user.getEmail());
			this.setPassword(user.getPassword());
			this.setRoles(user.getRoles());
			this.setMobile(user.getMobile());
		}		
	}
	
	public static void main(String[] args) {
		String password = "123456";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
    }
	
	private static class SimpleGrantedAuthorityComparator implements Comparator<SimpleGrantedAuthority> {
		@Override
		public int compare(SimpleGrantedAuthority o1, SimpleGrantedAuthority o2) {
			return o1.equals(o2) ? 0 : -1;
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authList = new TreeSet<SimpleGrantedAuthority>(new SimpleGrantedAuthorityComparator());

		for (Role role : this.getRoles()) {
			authList.addAll(getGrantedAuthorities(role));
		}

		return authList;
	}

	public static Set<SimpleGrantedAuthority> getGrantedAuthorities(Role role) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
