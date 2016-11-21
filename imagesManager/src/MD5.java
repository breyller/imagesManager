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

/*
*
* @author Bruno Lopes
* @author Bruno Reyller
* @author Henrique
* Gera uma Hash usando uma string baseada no arquivo sendo usado, a hash se baseia em seu nome
* @param texto - Nome do arquivo, ou outra string relacionado ao mesmo
* @return retorno - String com a hash
*/
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
    * @param arquivo - Arquivo tipo File sendo usado em que a hash sera baseada
    * @return retorno - String com a hash em Hexadecimal
    */
    public static String gerarMD5 (File arquivo) //Achei mais pratico passar o arquivo pra gerar a hash
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