package com.example.j2eeapp.services.impl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserAuthenticationProviderService;

public class UserAuthenticationProviderServiceImpl implements UserAuthenticationProviderService {

	private AuthenticationManager authenticationManager;

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public boolean processUserAuthentication(UserEntity user) {
		try {
			Authentication request= new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
			Authentication result= authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
			return true;
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),"Sorry!"));
			return false;
		}
	}

}
