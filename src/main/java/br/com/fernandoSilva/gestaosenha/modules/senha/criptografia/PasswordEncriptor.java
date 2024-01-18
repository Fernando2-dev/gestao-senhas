package br.com.fernandoSilva.gestaosenha.modules.senha.criptografia;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncriptor {
    private static final String ALGORITHM = "AES";
    private static final String KEY_FILE_PATH = "secret.key";
    private static SecretKey staticSecretKey;

    static {
        staticSecretKey = readSecretKeyFromFile();
        if (staticSecretKey == null) {
            staticSecretKey = generateSecretKey();
            saveSecretKeyToFile(staticSecretKey);
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

    private static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar a chave secreta", e);
        }
    }

    private static void saveSecretKeyToFile(SecretKey secretKey) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(KEY_FILE_PATH))) {
            outputStream.writeObject(secretKey);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a chave secreta no arquivo", e);
        }
    }

    private static SecretKey readSecretKeyFromFile() {
        try {
            if (Files.exists(Paths.get(KEY_FILE_PATH))) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(KEY_FILE_PATH))) {
                    return (SecretKey) inputStream.readObject();
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler a chave secreta do arquivo", e);
        }
    }
}
