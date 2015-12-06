package com.afi.jx.lib.common.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RsaSignatureUtils {

    private static Signature signature;
    private static RSAPublicKey rsaPubKey;
    private static RSAPrivateKey rsaPriKey;
    private static String DATE_FORMAT = "yyyy-MM-dd";

    static {
        java.security.Security.addProvider(new BouncyCastleProvider());

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(
                "81973016a5fb22127a76dc40a7ea1b0ae471d91feca2823759b6f290de2c4c7fc0b426e47c1ccf65d38f43f724f91eaa0acef8f8f59065aca46b5a9d012c3fb7", 16), new BigInteger("10001", 16));
        
        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(
                "81973016a5fb22127a76dc40a7ea1b0ae471d91feca2823759b6f290de2c4c7fc0b426e47c1ccf65d38f43f724f91eaa0acef8f8f59065aca46b5a9d012c3fb7", 16), new BigInteger("791a8dd10c89f51c699fbc531bd8762f26af9225f86121e992089b0470ebdb0c446f8339c7dc5ffa420654a1e2aafae33d40fd115396a4219eda39834fa56661", 16));
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA", "BC");
            rsaPubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
            rsaPriKey = (RSAPrivateKey) keyFactory.generatePrivate(priKeySpec);
            signature = Signature.getInstance("SHA1withRSA", "BC");
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RsaSignatureUtils() {
        super();
    }

    /**
     * inisialisasi key, hanya digunakan untuk mengganti key baru atau
     * inisialisasi key awal.
     */
    public void createRSAKeyMsgdlrcodeStr() {
        try {
            KeyPairGenerator keyGen = null;
            keyGen = KeyPairGenerator.getInstance("RSA", "BC");
            keyGen.initialize(512, new SecureRandom());
            
            KeyPair keyPair = keyGen.generateKeyPair();
            
            RSAPrivateKey newRSAPriKey = (RSAPrivateKey) keyPair.getPrivate();
            BigInteger biPriKeyModulus = newRSAPriKey.getModulus();
            BigInteger biPriKeyPriExponent = newRSAPriKey.getPrivateExponent();
            
            RSAPublicKey newRSAPubKey = (RSAPublicKey) keyPair.getPublic();
            BigInteger biPubKeyModulus = newRSAPubKey.getModulus();
            BigInteger biPubKeyPubExponent = newRSAPubKey.getPublicExponent();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * mengembalikan sekumpulan atribut yang akan dihitung sebagai sertifikat
     * sebagai sebuah String
     * @return
     */
    public static String getPlaintText(String dealerCode, String address, String dealerName,
            String owner, String city, String mdcode, String tglAwal, String tglAkhir, String licType) {
        StringBuffer plaintText = new StringBuffer();

        if (dealerCode != null) {
            plaintText.append(dealerCode);
        }
        if (address != null) {
            plaintText.append(address);
        }
        if (dealerName != null) {
            plaintText.append(dealerName);
        }
        if (owner != null) {
            plaintText.append(owner);
        }
        if (city != null) {
            plaintText.append(city);
        }
        if (mdcode != null) {
            plaintText.append(mdcode);
        }
        if (tglAwal != null) {
            plaintText.append(tglAwal);
        }
        if (tglAkhir != null) {
            plaintText.append(tglAkhir);
        }
        if (licType != null) {
            plaintText.append(licType);
        }

        if (plaintText == null) {
            return null;
        } else {
            return plaintText.toString();
        }
    }

    // Generate RSA Signature, that submitted to DB as encryption value
    public byte[] getRSASignatureMstdlrcodeStr(String plainText) {
        try {
            // generate a signature
            signature.initSign(rsaPriKey, RsaUtils.createFixedRandom());
            signature.update(plainText.getBytes());
            byte[] sigBytes = signature.sign();
            System.out.println("getRSASignatureMstdlrcodeStr: BCERT created from: " + plainText);
            System.out.println("getRSASignatureMstdlrcodeStr: BCERT created:(" + Integer.toString(sigBytes.length) + ") " + RsaUtils.toHex(sigBytes));

            return sigBytes;
        } catch (SignatureException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RsaSignatureUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
