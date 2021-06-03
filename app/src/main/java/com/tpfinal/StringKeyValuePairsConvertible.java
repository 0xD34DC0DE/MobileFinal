package com.tpfinal;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface StringKeyValuePairsConvertible {
    Map<String, String> toKeyValuePairs() throws JsonProcessingException;
}
