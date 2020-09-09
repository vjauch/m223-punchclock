package ch.zli.m223.punchclock.security;

import static ch.zli.m223.punchclock.security.SecurityConstants.ALGORITHM_RSA;
import static ch.zli.m223.punchclock.security.SecurityConstants.TOKEN_PREFIX;
import static com.auth0.jwt.algorithms.Algorithm.RSA256;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.env.Environment;
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
import java.util.Objects;

@Service
public class JWTTokenService {


	private final Environment environment;
    private final Algorithm algorithm;

    public JWTTokenService(Environment environment) {
        this.environment = environment;
        this.algorithm = getAlgorithm();
    }

    public String createToken(String subject) {
        return JWT.create()
                .withSubject(subject)
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
    }

    public String verifyToken(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }

    private Algorithm getAlgorithm() {
        return RSA256(readPublicKey(new File(Objects.requireNonNull(environment.getProperty("jwt.public-key-file"),
                "Public key file property not set"))),
                readPrivateKey(new File(Objects.requireNonNull(environment.getProperty("jwt.private-key-file"),
                        "Private key file property not set"))));
    }

    private RSAPublicKey readPublicKey(File file) {
        KeyFactory factory;
        try {
            factory = KeyFactory.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: ", e);
        }

        try (FileReader keyReader = new FileReader(file);
                PemReader pemReader = new PemReader(keyReader)) {
            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return (RSAPublicKey) factory.generatePublic(pubKeySpec);
        } catch (IOException | InvalidKeySpecException e) {
            throw new RuntimeException("Exception while reading public key file: ", e);
        }
    }

    private RSAPrivateKey readPrivateKey(File file) {
        KeyFactory factory;
    	try {
            factory = KeyFactory.getInstance(ALGORITHM_RSA);
        } catch (NoSuchAlgorithmException e) {
    	    throw new RuntimeException("No such algorithm: ", e);
        }
        try (FileReader keyReader = new FileReader(file);
                PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
        } catch (IOException | InvalidKeySpecException e) {
            throw new RuntimeException("Exception while reading private key file: ", e);
        }
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + Long.parseLong(
                Objects.requireNonNull(environment.getProperty("jwt.expiration-time"),
                        "JWT expiration time property not set")));
    }
}
