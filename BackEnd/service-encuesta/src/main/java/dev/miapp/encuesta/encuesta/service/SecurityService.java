package dev.miapp.encuesta.encuesta.service;

import dev.miapp.encuesta.encuesta.dto.RenewPasswordFirstDTO;
import dev.miapp.encuesta.encuesta.dto.RespuestaApi;
import dev.miapp.encuesta.encuesta.dto.UpdatePasswordDTO;

public interface SecurityService {

	public RespuestaApi getToken(String username, String password);
	public RespuestaApi resetNewPasswordFirst(RenewPasswordFirstDTO updatePassword);
	public RespuestaApi updatePassword(UpdatePasswordDTO updatePassword);
	public RespuestaApi signOut(String token);
	public RespuestaApi refreshToken(String token);
}
