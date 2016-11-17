import java.security.*;
import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {
    public static String gerarMd5(String texto){
        MessageDigest m = null;
        String retorno = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.update(texto.getBytes(),0,texto.length());
        retorno = new BigInteger(1,m.digest()).toString(16);
        
        return retorno;
    }
}