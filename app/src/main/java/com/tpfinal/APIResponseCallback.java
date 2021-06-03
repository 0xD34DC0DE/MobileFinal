package com.tpfinal;

@FunctionalInterface
public interface APIResponseCallback {
    void onAPIResponse(String json);
}
