import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        System.out.println("+++++++++WorkingDirectoryFile+++++++++++");
      Path path = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
      printFile(path);
        System.out.println("+++++++ subdirectoryFile+++++++++++");
//      Path filePath = FileSystems.getDefault().getPath("files","SubdirectoryFile.txt");
//        if you don't want to specify absolute path:
      Path filePath = Paths.get(".","files","SubdirectoryFile.txt");
      printFile(filePath);
        System.out.println("++++++++++outDirectory+++++++++++");
//        filePath = Paths.get("C:\\Users\\Lenovo\\Desktop\\Repozytoria\\outerdirectionFile.txt");
        filePath = Paths.get("C:\\","Users\\Lenovo\\Desktop\\Repozytoria","outerdirectionFile.txt");
        printFile(filePath);
//        alternative ways of doing it if you can't specify absolute paths in your code:\
//        current directory which will resolve to the intellij project directory:
        filePath = Paths.get(".");
        System.out.println(filePath.toAbsolutePath());
//        normilize method:
        System.out.println("++++++++++++normalize method:+++++++++");
        Path path2= FileSystems.getDefault().getPath(".","files","..","files","SubdirectoryFile.txt");
        System.out.println(path2.normalize().toAbsolutePath());
        printFile(path2.normalize());

        //create a path to a file that doesn't exist:
        Path path3 = FileSystems.getDefault().getPath("ThisfileNotExist.txt");
        System.out.println(path3.toAbsolutePath());
        Path path4 = Paths.get("C:\\","Users\\Lenovo\\Desktop\\Repozytoria\\Paths","ThisfileNotExist.txt");
        System.out.println(path4.toAbsolutePath());
//        check if this file exist:
        filePath = FileSystems.getDefault().getPath("files");
        System.out.println("Does Exists ? : " + Files.exists(filePath));
        System.out.println("Does path4 Exists ? : " + Files.exists(path4));
        System.out.println("Does path3 Exists ? : " + Files.exists(path3));


    }
    private static void printFile(Path path){
        try(BufferedReader fileReader = Files.newBufferedReader(path)){
            String line;
            while((line = fileReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}