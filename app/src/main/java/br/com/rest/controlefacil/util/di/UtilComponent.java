package br.com.rest.controlefacil.util.di;

import java.util.HashMap;

import br.com.rest.controlefacil.util.Preferences;
import dagger.Component;

/**
 * Created by LUCAS RODRIGUES on 26/11/2017.
 */
@Component(modules = UtilModule.class)
public interface UtilComponent {
    HashMap<String, String> hashMap();

    Preferences preferences();
}
