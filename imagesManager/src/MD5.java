import java.io.File;
import java.io.IOException;
import java.security.*;
import java.math.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class MD5 
{
    public static String gerarMD5(String texto)
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
    public static String gerarMD5 (File arquivo)
    {
        String retorno = null;
        MessageDigest md = null;
        String path = arquivo.getAbsolutePath();
        Path pathy = Paths.get(path);
        try 
        {
            md = MessageDigest.getInstance("MD5");
            try
            {
                md.update(Files.readAllBytes(pathy));
            } 
            catch (IOException ex) 
            {
                //Logger.getLogger(TestArea.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] digest = md.digest();
            
            retorno = DatatypeConverter.printHexBinary(digest)/*.toLowerCase()*/;    
        }
        catch (NoSuchAlgorithmException ex) 
        {
            //Logger.getLogger(TestArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}