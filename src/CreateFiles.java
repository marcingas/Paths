import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CreateFiles {
    public static void main(String[] args) {
        try{
//            create file:
//            Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file2.txt");
//            Files.createFile(fileToCreate);
//            create directory:
//            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir4");
//            Files.createDirectory(dirToCreate);
//            create directiories:
//            Path dirToCreate = FileSystems.getDefault().getPath("Examples", "Dir2/Dir3/Dir4/Dir5/Dir6");
//            Files.createDirectories(dirToCreate);

//            Path dirToCreate = FileSystems.getDefault().getPath("Examples","Dir2\\Dir3\\Dir4\\Dir5\\Dir6\\Dir7");
//            Files.createDirectories(dirToCreate);
            Path filePath = FileSystems.getDefault().getPath("Examples","Dir1\\file1.txt");
            long size = Files.size(filePath);
            System.out.println("size = " + size);
            System.out.println("Last modified = " + Files.getLastModifiedTime(filePath));

//            get atributes for the same file:
            BasicFileAttributes atributes = Files.readAttributes(filePath,BasicFileAttributes.class);
            System.out.println("size = " + atributes.size());
            System.out.println("Last Modified =" + atributes.lastModifiedTime());
            System.out.println("Created =" + atributes.creationTime());
            System.out.println("is directory = " + atributes.isDirectory());
            System.out.println("is regular file = " + atributes.isRegularFile());

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
