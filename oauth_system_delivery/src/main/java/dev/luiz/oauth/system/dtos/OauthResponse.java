package dev.luiz.oauth.system.dtos;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.luiz.oauth.system.enums.UserType;
import lombok.Getter;

public class OauthResponse implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Getter
    private String cpf;

    @Getter
    private String password;

    @Getter
    private UserType userType;

    private boolean enabled;
	
    public OauthResponse(String cpf, String password, UserType userType) {
        this.cpf = cpf;
        this.password = password;
        this.userType = userType;
        this.enabled = true;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(
		        new SimpleGrantedAuthority(userType.name())
		    );
	}
	
	@Override
    public String getUsername() {
        return this.cpf;
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
        return enabled;
    }
}
