package com.github.jsf.dynamic_placeholders.components.impls.actions;

import com.github.jsf.dynamic_placeholders.components.ComponentsInfo;
import com.github.jsf.dynamic_placeholders.components.impls.actions.Action;
import com.github.jsf.dynamic_placeholders.names.UName;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash extends Action {
    public static final UName ID = new UName("hash");
    public static final UName VALUE = new UName("value", "v", "input");
    public static final UName ALGORITHM = new UName("algorithm", "algo", "a");

    public Hash(ComponentsInfo infos) {
        super(ID, infos);
    }

    @Override
    public String evaluate() {
        String value = as(VALUE, String.class).orElseThrow(() ->
                new IllegalArgumentException("The parameter " + VALUE + " is needed for \"" + ID + "\" action"));
        String algo = as(ALGORITHM, String.class).orElse("SHA-256");

        try {
            MessageDigest digest = MessageDigest.getInstance(algo);
            byte[] encodedhash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unsupported hash algorithm: " + algo, e);
        }
    }
}