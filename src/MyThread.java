import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread{
    public static String ACCESS_TOKEN = "<your token_app>";
    public static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    public static DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    @Override
    public void run() {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            InputStream in = new ByteArrayInputStream(baos.toByteArray());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
            Date today = new Date();
            client.files().uploadBuilder("/" + formatter.format(today) + ".png").uploadAndFinish(in);
            sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
