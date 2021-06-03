package com.tpfinal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PermitService {

    private static final String PERMIT_URL = "http://10.0.2.2:9090/permis/get/%s";

    public static void getPermitData(String SSN, PermitDataCallback permitDataCallback) {
        new APICallGet(new APIResponseCallback() {
            @Override
            public void onAPIResponse(String json) {
                try {

                    ObjectMapper objectMapper = new ObjectMapper();
                    permitDataCallback.onDataReceived(
                            objectMapper.readValue(json, new TypeReference<PermitData>(){})
                    );

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    permitDataCallback.onDataReceived(null);
                }
            }
        }).execute(String.format(PERMIT_URL, SSN));
    }

    @FunctionalInterface
    public interface PermitDataCallback {
        void onDataReceived(PermitData permitData);
    }

}
