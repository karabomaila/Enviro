package com.enviro.assessment.grad001.karabomaila;

import com.enviro.assessment.grad001.karabomaila.model.AccountProfile;
import com.enviro.assessment.grad001.karabomaila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Base64;
import java.util.Scanner;
import java.util.UUID;

@Configuration
public class FileParserImpl implements FileParser{

    @Value("${file.upload.dir}")
    private String imageFilePath;

    private UserService userService;

    @Autowired
    public FileParserImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void parseCSV(File csvFile) {
        try {
            Scanner scanner = new Scanner(csvFile);
            scanner.nextLine(); // skip the first line

            while(scanner.hasNext()){
                String[] data = scanner.nextLine().split(",");

                // save the user profile data
                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setFirstName(data[0]);
                accountProfile.setLastName(data[1]);
                // create image link
                File image = convertCSVDataToImage(data[2], data[3]);
                URI uri = createImageLink(image);
                accountProfile.setImageLink(uri.toString());
                // save the account profile
                userService.saveUserProfile(accountProfile);
            }
            scanner.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public File convertCSVDataToImage(String imageFormat, String base64ImageData) {
        String uniqueFileName = UUID.randomUUID().toString();
        String imageFileName = null;
        if (imageFormat.equalsIgnoreCase("image/jpeg")) imageFileName = uniqueFileName + ".jpeg";
        else if (imageFormat.equalsIgnoreCase("image/png")) imageFileName = uniqueFileName + ".png";
        else if (imageFormat.equalsIgnoreCase("image/jpg")) imageFileName = uniqueFileName + ".jpg";

        try {
            byte[] bytes = Base64.getDecoder().decode(base64ImageData);

            FileOutputStream fileOutputStream = new FileOutputStream(imageFilePath + imageFileName);
            fileOutputStream.write(bytes);
            fileOutputStream.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new File(imageFilePath + imageFileName);
    }

    @Override
    public URI createImageLink(File fileImage) {
        return fileImage.toURI();
    }
}
