package br.com.rest.controlefacil.util.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by LUCAS RODRIGUES on 26/11/2017.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UtilScope {
}
