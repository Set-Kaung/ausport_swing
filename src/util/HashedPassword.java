package util;

import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import model.User;

public class HashedPassword {
    private final byte[] hash;
    private final byte[] salt;

    public HashedPassword(byte[] hashedPassword, byte[] salt) {
        this.hash = hashedPassword;
        this.salt = salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public static boolean authenticate(String username, String password, User dbUser){
        byte[] loginHash = hashWithArgon2(password, dbUser.getPassword().getSalt());
        return Arrays.equals(loginHash, dbUser.getPassword().getHash());
    }

    public static HashedPassword getHashedPassword(String password){
        byte[] salt = generateSalt();
        byte[] pass = hashWithArgon2(password, salt);
        return new HashedPassword(pass, salt) ;
    }

    private static byte[] hashWithArgon2(String password,byte[] salt) {
        Argon2Parameters parameters = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id).withSalt(salt)
                .withMemoryAsKB(32768)
                .withIterations(8)
                .withParallelism(4)
                .build();
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(parameters);

        byte[] hashedPassword = new byte[32]; // Adjust based on your desired output length
        generator.generateBytes(password.toCharArray(), hashedPassword, 0, hashedPassword.length);
        return hashedPassword;
    }

    private static byte[] generateSalt() {
        SecureRandom rand = new SecureRandom();
        byte[] salt = new byte[16];
        rand.nextBytes(salt);
        return salt;
    }

}

