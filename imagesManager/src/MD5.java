import java.io.File;
import java.io.IOException;
import java.security.*;
import java.math.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class MD5 
{
    public static String gerarMd5(String texto)
    {
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
    
    /*
    * Gera uma Hash usando o arquivo de tipo File sendo usado, a hash se baseia em seus Bytes
    * @param arquivo
    * @return retorno - String com a hash em Hexadecimal
    */
    public static String gerarMD5 (File arquivo) throws NoSuchAlgorithmException, IOException
    {
        String path = "/src/Img";
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(path)));
        byte[] digest = md.digest();

        String retorno = DatatypeConverter.printHexBinary(digest)/*.toLowerCase()*/;
        System.out.println(retorno);
        
        return retorno;        
    }
}