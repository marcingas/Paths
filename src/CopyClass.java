import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CopyClass {
    public static void main(String[] args) {
       try {
//           delete file:
           Path fileToDelete = FileSystems.getDefault().getPath("Examples","Dir1", "file1copy.txt");
           Files.deleteIfExists(fileToDelete);
           //rename file:
//           Path fileToMove=FileSystems.getDefault().getPath("Examples", "Dir1","file1copyanother.txt");
//           Path destination = FileSystems.getDefault().getPath("Examples","Dir1", "file1copy.txt");
//           Files.move(fileToMove,destination);
//           move copied file to dir1:(you have to specify full path)
//            Path fileToMove=FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//            Path destination = FileSystems.getDefault().getPath("Examples","Dir1", "file1copyy.txt");
//            Files.move(fileToMove,destination);

//           copy file:
//           Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
//           Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
//           Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

           //copy directiories using the same copy method: but directiories will be copied without files inside
//           sourceFile = FileSystems.getDefault().getPath("Examples","Dir1");
//           copyFile = FileSystems.getDefault().getPath("Examples", "Dir4");
//           Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);

       }catch (IOException e){
           System.out.println(e.getMessage());
       }
    }
}
