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
//        using fileToPath() to convert io-> nio (paths)
        System.out.println("----convert io-> nio---------");
//        one way of doing it:
        File file = new File("C:\\Examples\\file.txt");
        Path convertedPath = file.toPath();
        System.out.println("ConvertedPath = " + convertedPath);
//      another way 1:
        File parent = new File("C:\\Examples");
        File resolvedFile = new File(parent,"dir\\file.txt");
        System.out.println(resolvedFile.toPath());
//        another way 2:

        resolvedFile = new File("C:\\Examples", "dir\\file.txt");
        System.out.println(resolvedFile.toPath());
//        another way 3:
        Path parentPath = Paths.get("C:Examples");
        Path childPath = Paths.get("dir\\file.txt");
        System.out.println(parentPath.resolve(childPath));
//if you pass empty string getAbsoluteFile can find path to this empty string :
        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("WorkingDirectory = " + workingDirectory.getAbsoluteFile());

        System.out.println("------- print Dir1 contents using list()----");
// when working with java.io you use file.list() and file.listfiles(), list() returns an array of file.
// you can pass an optional filtering, but it only return firsdt level entry doesn't walk the tree down
// to any sort of subfolders.
        File dir2File = new File(workingDirectory,"\\Examples\\Dir2");
        String[] dir2Contents = dir2File.list();
        for(int i = 0; i < dir2Contents.length; i++){
            System.out.println("i = " + i + " : " + dir2Contents[i]);

        }
        System.out.println("--------print Dir2 contents using listFiles() --------");
        File[] dir2Files = dir2File.listFiles();
        for(int i = 0; i < dir2Files.length; i++) {
            System.out.println("i = " + i + " : " + dir2Files[i].getName());
        }
//        Conclusion: if working with file System its beter to use java.nio
//        but when reading and writing file contents often the java.io streams are the better choice.

    }
}
