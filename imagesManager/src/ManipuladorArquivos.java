
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ManipuladorArquivos implements IPersistencia{

    public ManipuladorArquivos() {

    }
    public static final int CHUNK = 1024; //Number of bytes to use in the buffer
    public static final String directory = "src/Img/";

    //Funcao para comparar arquivos Byte por Byte
    //Na testada a fundo, se der ruim foi ela.
    public boolean compararArquivos(Path arquivo1, Path arquivo2) throws IOException {
        final long size = Files.size(arquivo1);
        if (size != Files.size(arquivo2)) {
            return false;
        }

        if (size < CHUNK) {
            return Arrays.equals(Files.readAllBytes(arquivo1), Files.readAllBytes(arquivo2));
        }

        try (InputStream is1 = Files.newInputStream(arquivo1);
                InputStream is2 = Files.newInputStream(arquivo2)) {
            int data;
            while ((data = is1.read()) != -1) {
                if (data != is2.read()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void writeImage(String enderecoAbsoluto, String nomeArquivo) throws IOException {
        String enderecoLocal = directory + nomeArquivo;

        if (enderecoLocal.equals(enderecoAbsoluto)) //checks to see if they are not the same arguments
        {
            System.err.println("Nome ja existente!");
            System.exit(0);
        }

        /*Receives and checks files and destinations*/
        File source = new File(enderecoAbsoluto); //receives the file location from the argument lines
        File dest = new File(enderecoLocal); //receives the file destination from the argument lines

        //Compares if the images are equal (should be changed later to compare to ALL images on the folder
        Path caminho1 = Paths.get(enderecoAbsoluto);
        Path caminho2 = Paths.get(enderecoLocal);
        boolean igual = compararArquivos(caminho1, caminho2);

        if (igual == true) {
            System.out.println("Imagem ja existente!");
        } else {
            /*Initializes streams*/
            InputStream input = null; //initializes the input stream
            OutputStream output = null; //initializes the output stream

            /*Starts to copy the files*/
            try {

                long startTime = System.currentTimeMillis(); //Timer
                input = new FileInputStream(source); //Input will be the source file
                output = new FileOutputStream(dest); //Output will be the destination file, it will erase the previous file
                byte[] buffer = new byte[CHUNK]; //Prepares the buffer
                int bytesRead;

                while ((bytesRead = input.read(buffer)) > 0) //Copies the bytes on the input to the output
                {
                    output.write(buffer, 0, bytesRead);
                }

                System.out.println("Time: " + (System.currentTimeMillis() - startTime)); //Displays time spent in the copy operation
            } /*Exception handling*/ catch (FileNotFoundException fnfe) {
                System.err.println("File was not found");
            } /*Always closes the streams after the try*/ finally //Closes the input and output stream
            {
                if (input != null) //Checks to see if there's an input stream
                {
                    input.close();//Closes the input and output stream
                }
                if (output != null) //Checks to see if there's an output stream
                {
                    output.close();//Closes the input and output stream
                }
            }
            System.out.println("Done");
        }
    }

    public void updateImage(String enderecoNovo, String nomeArquivo) throws IOException {
        String enderecoLocal = "/home/brunoreyller/NetBeansProjects/imagesManager/src/imagesManager/RepositorioLocal/" + nomeArquivo;

        /*Receives and checks files and destinations*/
        File source = new File(enderecoNovo); //receives the file location from the argument lines
        File dest = new File(enderecoLocal); //receives the file destination from the argument lines

        /*Initializes streams*/
        InputStream input = null; //initializes the input stream
        OutputStream output = null; //initializes the output stream

        /*Starts to copy the files*/
        try {

            long startTime = System.currentTimeMillis(); //Timer
            input = new FileInputStream(source); //Input will be the source file
            output = new FileOutputStream(dest); //Output will be the destination file, it will erase the previous file
            byte[] buffer = new byte[CHUNK]; //Prepares the buffer
            int bytesRead;

            while ((bytesRead = input.read(buffer)) > 0) //Copies the bytes on the input to the output
            {
                output.write(buffer, 0, bytesRead);
            }

            System.out.println("Time: " + (System.currentTimeMillis() - startTime)); //Displays time spent in the copy operation
        } /*Exception handling*/ catch (FileNotFoundException fnfe) {
            System.err.println("File was not found");
        } /*Always closes the streams after the try*/ finally //Closes the input and output stream
        {
            if (input != null) //Checks to see if there's an input stream
            {
                input.close();//Closes the input and output stream
            }
            if (output != null) //Checks to see if there's an output stream
            {
                output.close();//Closes the input and output stream
            }
        }
        System.out.println("Done");
    }

    public void deleteImage(String nomeArquivo) throws IOException {

    }

    public void listContent() {
        File f = new File("C:\\Test\\");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
    }

    @Override
    public Imagem getImageById(int id) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public Imagem getImageById(int id, int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public Imagem getImageByHash(String hash) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public Imagem getImageByHash(String hash, int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Imagem> getImageByTitle(String title) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Imagem> getImageByTitle(String title, int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Imagem> getImageByDescription(String description) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Imagem> getImageByDescription(String description, int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Imagem> getImageByAlbumId(int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public Album getAlbumById(int id) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Album> getAlbumByTitle(String title) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public ArrayList<Album> getAlbumByDescription(String description) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean setAlbum(Album alb) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean setImage(Imagem img) {
        //implementar este
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean setImageOnAlbum(int idImage, int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean updateAlbum(Album alb) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean updateImage(Imagem img) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean deleteImage(int idImage) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean deleteAlbum(int idAlbum) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }

    @Override
    public boolean deleteImageFromAlbum(Album alb) {
        //ok
        throw new UnsupportedOperationException("Metodo indisponivel.");
    }
}