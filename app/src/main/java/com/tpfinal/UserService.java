package com.tpfinal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final String LOGIN_URL = "http://10.0.2.2:9090/user/login";
    private static final String REGISTER_URL = "http://10.0.2.2:9090/user/register/citoyen";

    private static UserData currentUser;

    public static void registerOrLogin(String SSN, RegisterOrLoginCallback registerOrLoginCallback) {

        try {

            new APICallPost(new APIResponseCallback() {
                @Override
                public void onAPIResponse(String json) {

                    if(json == null) {
                        currentUser = null;
                        return;
                    }

                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        UserData userData = objectMapper.readValue(json, new TypeReference<UserData>(){});

                        if(userData.getErrorMessage() != null && userData.getErrorMessage().equals("Wrong credentials")) {
                            register(SSN, registerOrLoginCallback);
                        } else {
                            currentUser = userData;
                            registerOrLoginCallback.onDataReceived(userData);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        registerOrLoginCallback.onDataReceived(null);
                    }
                }
            }, new Credentials(SSN)).execute(LOGIN_URL);

        } catch (IOException e) {
            e.printStackTrace();
            registerOrLoginCallback.onDataReceived(null);
        }
    }

    @FunctionalInterface
    public interface RegisterOrLoginCallback {
        void onDataReceived(UserData userData);
    }

    private static void register(String ssn, RegisterOrLoginCallback registerOrLoginCallback) {
        try {
            CitoyenRegisterData citoyenRegisterData = new CitoyenRegisterData(ssn);

            new APICallPost(new APIResponseCallback() {
                @Override
                public void onAPIResponse(String json) {
                    if(json == null) {
                        currentUser = null;
                        return;
                    }

                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        UserData userData = objectMapper.readValue(json, new TypeReference<UserData>() {});

                        if(userData.getErrorMessage() == null)
                            currentUser = userData;

                        registerOrLoginCallback.onDataReceived(userData);

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }, citoyenRegisterData).execute(REGISTER_URL);

        } catch (IOException e) {
            e.printStackTrace();
            registerOrLoginCallback.onDataReceived(null);
        }
    }

    public static UserData getCurrentUser() {
        return currentUser;
    }
}
