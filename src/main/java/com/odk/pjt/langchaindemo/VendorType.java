package com.odk.pjt.langchaindemo;

public enum VendorType {
    OPEN_AI, OLLAMA;

    public static VendorType fromString(String vendorName) throws Exception {
        if (vendorName.equalsIgnoreCase("OPENAI")) {
            return VendorType.OPEN_AI;
        } else if (vendorName.equalsIgnoreCase("OLLAMA")) {
            return VendorType.OLLAMA;
        } else {
            throw new Exception("Invalid vendor type: " + vendorName);
        }
    }
}
