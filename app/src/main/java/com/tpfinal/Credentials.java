package com.tpfinal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Credentials implements StringKeyValuePairsConvertible {

    private final String login;

    private final String password;

    public Credentials(String ssn) throws IOException {
        String SSNHash = HashUtil.Hash(ssn);
        this.login = SSNHash;
        this.password = SSNHash;
    }

    @Override
    public Map<String, String> toKeyValuePairs() {
        return new HashMap<String, String>() {{
            put("password", password);
            put("login", login);
        }};
    }
}
