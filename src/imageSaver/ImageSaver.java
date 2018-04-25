
package imageSaver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ImageSaver {

    public static void saveImage(String selectedFilePath, String imageFileName) {
        if (selectedFilePath != null) {
            try {

                File selectedFile = new File(selectedFilePath);
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                FileOutputStream fileOutputStream = new FileOutputStream("images\\" + imageFileName);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int b = 0;
                while (b != -1) {
                    b = bufferedInputStream.read();
                    bufferedOutputStream.write(b);
                }
                bufferedInputStream.close();
                bufferedOutputStream.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
