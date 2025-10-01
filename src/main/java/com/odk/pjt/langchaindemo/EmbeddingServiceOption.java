package com.odk.pjt.langchaindemo;

public class EmbeddingServiceOption {
    private String vendorName;
    private String modelName;
    private String apiKey;

    public EmbeddingServiceOption(String vendorName, String modelName, String apiKey) {
        this.vendorName = vendorName;
        this.modelName = modelName;
        this.apiKey = apiKey;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
