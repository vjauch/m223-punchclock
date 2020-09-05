package ch.zli.m223.punchclock.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import static ch.zli.m223.punchclock.security.SecurityConstants.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.auth0.jwt.algorithms.Algorithm.RSA256;

@Service
public class JWTTokenService {

	@Value("${jwt.expiration-time}")
    private long expirationTime;
    private final Algorithm algorithm;

    public JWTTokenService() {
        this.algorithm = getAlgorithm();
    }

    public String createToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public String verifyToken(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    public Algorithm getAlgorithm() {
        // TODO: filepath from properties
        return RSA256(readPublicKey(new File("...../key.pem.pub")),
                readPrivateKey(new File("....../key.pem")));
    }

    public RSAPublicKey readPublicKey(File file) {
        KeyFactory factory;
        try {
            factory = KeyFactory.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm exception: ", e);
        }

        try (FileReader keyReader = new FileReader(file);
                PemReader pemReader = new PemReader(keyReader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return (RSAPublicKey) factory.generatePublic(pubKeySpec);
        } catch (IOException | InvalidKeySpecException e) {
            throw new RuntimeException("Exception while reading key file: ", e);
        }
    }

    public RSAPrivateKey readPrivateKey(File file) {
        KeyFactory factory;
    	try {
            factory = KeyFactory.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
    	    throw new RuntimeException("No such algorithm exception: ", e);
        }
        try (FileReader keyReader = new FileReader(file);
                PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
        } catch (IOException | InvalidKeySpecException e) {
            // TODO: adapt exceptions
            throw new RuntimeException("Exception while reading key file: ", e);
        }
    }
}
