package br.com.fernandoSilva.gestaosenha.modules.senha.criptografia;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class PasswordEncriptor {
    private static final String ALGORITHM = "AES";
    private static SecretKey staticSecretKey;

    public PasswordEncriptor() {
        if (staticSecretKey == null) {
            staticSecretKey = generateSecretKey();
        }
    }

    public String encrypt(String password) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, staticSecretKey);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, staticSecretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    public boolean comparePasswords(String rawPassword, String encryptedPassword) {
        try {
            String decryptedPassword = decrypt(encryptedPassword);
            return rawPassword.equals(decryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comparar senhas", e);
        }
    }

    private SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar a chave secreta", e);
        }
    }
}
