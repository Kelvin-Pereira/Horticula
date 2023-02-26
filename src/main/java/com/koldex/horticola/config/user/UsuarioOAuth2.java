package com.koldex.horticola.config.user;

import com.koldex.horticola.api.oauth.entity.User;
import com.koldex.horticola.config.exceptions.HttpException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.NativeWebRequest;

public final class UsuarioOAuth2 extends UsuarioArgumentResolver {

    @Override
    protected Object autenticacaoRequerida(MethodParameter parameter, NativeWebRequest webRequest) {
        throw HttpException.autenticacaoRequerida();
    }

    @Override
    protected Usuario extrairPerfil(Authentication authentication, MethodParameter parameter) {
        Usuario usuario = parseUsuario((User) authentication.getPrincipal());
        if (parameter.getParameterType().isInstance(usuario)) {
            return usuario;
        }
        throw HttpException.privilegiosInsuficientes();
    }

    private Usuario parseUsuario(User user) {
        return new UsuarioPrincipal(user);
    }

}
