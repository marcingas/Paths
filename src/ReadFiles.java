import java.io.IOException;
import java.nio.file.*;

public class ReadFiles {
    public static void main(String[] args) {
        //equivalent of 3 but with Lambda expression:
        DirectoryStream.Filter<Path>filter = p -> Files.isRegularFile(p);
        //3 version of new directory stream method, allows to write our own filters:
//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>(){
//            public boolean accept(Path path)throws IOException {
//                return Files.isRegularFile(path);
//            }
//        };
        Path directory = FileSystems.getDefault().getPath("Examples/Dir2");
        try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)){
            for(Path file : contents){
                System.out.println(file.getFileName());
            }

        }catch (IOException | DirectoryIteratorException e){
            System.out.println(e.getMessage());
        }

    }
}
