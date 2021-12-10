package isa.adventuretime.Controller;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/image")
public class ImageController {

    @PostMapping(path = "/save/{namePic}")
	public ResponseEntity<String> saveImage(@PathVariable("namePic") String namePic, @RequestParam ("file") MultipartFile multipartFile) throws Exception {
        System.out.println("New picture name:");
        System.out.println(namePic);
        String imgName = namePic + ".png";
        Path filepath = Paths.get("src/frontend/src/assets/images", imgName);
        if(System.getProperty("os.name").indexOf("Win") >= 0)
            filepath = Paths.get("src\\frontend\\src\\assets\\images", imgName);
                
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(multipartFile.getBytes());
        }
        return new ResponseEntity<>(multipartFile.getOriginalFilename() ,HttpStatus.OK);
	}

}
