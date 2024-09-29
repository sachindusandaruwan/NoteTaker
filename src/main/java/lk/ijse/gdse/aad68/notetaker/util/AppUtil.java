package lk.ijse.gdse.aad68.notetaker.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {

    public static String createNoteId(){
        return "NOTE-"+UUID.randomUUID();
    }

    public static String createUserId(){
        return "USER-"+UUID.randomUUID();
    }

    public static String toBase64ProfilePic(MultipartFile profilePic){
        try {
            byte[] imageToByte=profilePic.getBytes();
            return Base64.getEncoder().encodeToString(imageToByte);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return Base64.getEncoder().encodeToString(profilePic);
        //String value eka base64 String ekak karanawa
    }
}
