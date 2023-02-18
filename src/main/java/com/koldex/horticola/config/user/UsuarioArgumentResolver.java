package com.koldex.horticola.config.user;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public abstract class UsuarioArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Usuario.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            if (parameter.hasParameterAnnotation(Nullable.class)) {
                return null;
            }
            return autenticacaoRequerida(parameter, webRequest);
        }
        return extrairPerfil(authentication, parameter);
    }

    protected abstract Object autenticacaoRequerida(MethodParameter parameter,
                                                    NativeWebRequest webRequest);

    protected abstract Usuario extrairPerfil(Authentication authentication,
                                             MethodParameter parameter);

}
