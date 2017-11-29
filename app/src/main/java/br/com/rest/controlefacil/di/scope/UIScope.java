package br.com.rest.controlefacil.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by LUCAS RODRIGUES on 27/11/2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UIScope {
}
