import java.io.File;
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
        Path directory = FileSystems.getDefault().getPath("Examples"+ File.separator +"Dir2");
        try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)){
            for(Path file : contents){
                System.out.println(file.getFileName());
            }

        }catch (IOException | DirectoryIteratorException e){
            System.out.println(e.getMessage());
        }
        String separator = File.separator;
        System.out.println("File separator: " + separator);
        separator = FileSystems.getDefault().getSeparator();
        System.out.println("FileSystem separator: " + separator );
        try{
            Path tempFile = Files.createTempFile("myapp",".appext");
            System.out.println("Temporary File path = " + tempFile.toAbsolutePath());

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        //how to get file store:
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store : stores){
            System.out.println(store);
            System.out.println(store.name());
        }
        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for(Path path : rootPaths){
            System.out.println(path);
        }
        System.out.println("--------Walking tree for dir 2 ------------");
        Path dir2Path = FileSystems.getDefault().getPath("Examples" + File.separator + "Dir2" );
        try{
            Files.walkFileTree(dir2Path, new PrintNames());

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println("-----Copy Dir2 to Dir4/Dir2Copy---");
        Path copyPath = FileSystems.getDefault().getPath("Examples" +
                File.separator + "Dir4" + File.separator + "Dir2Copy");
        try{
            Files.walkFileTree(dir2Path, new CopyFiles(dir2Path,copyPath));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
