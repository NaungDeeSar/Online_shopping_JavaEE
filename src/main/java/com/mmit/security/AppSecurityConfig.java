package com.mmit.security;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;

import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@ApplicationScoped
@DeclareRoles({"Admin","Staff","Customer"})
@FacesConfig(version = javax.faces.annotation.FacesConfig.Version.JSF_2_3)
@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(loginPage="/login.xhtml"))
public class AppSecurityConfig {

}
