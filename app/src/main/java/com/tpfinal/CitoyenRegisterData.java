package com.tpfinal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CitoyenRegisterData implements StringKeyValuePairsConvertible {

    private final String numeroAssuranceSocial;

    private final String courriel;

    private final String numeroTelephone;

    private final String villeResidence;

    private final String login;

    private final String password;

    public CitoyenRegisterData(String numeroAssuranceSocial) throws IOException {
        this.numeroAssuranceSocial = numeroAssuranceSocial;
        this.courriel = "MOBILE";
        this.numeroTelephone = "MOBILE";
        this.villeResidence = "MOBILE";

        String SSNHash = HashUtil.Hash(numeroAssuranceSocial);
        this.login = SSNHash;
        this.password = SSNHash;
    }

    @Override
    public Map<String, String> toKeyValuePairs() {
        return new HashMap<String, String>() {{
            put("numeroAssuranceSocial", numeroAssuranceSocial);
            put("courriel", courriel);
            put("numeroTelephone", numeroTelephone);
            put("villeResidence", villeResidence);
            put("login", login);
            put("password", password);
        }};
    }
}
