package isa.adventuretime.Controller;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/image")
public class ImageController {

    
    @PostMapping(path = "/save")
	public ResponseEntity<String> saveImage(@RequestParam ("file") MultipartFile multipartFile) throws Exception {
        Path filepath = Paths.get("src\\frontend\\src\\assets\\images", "POBOGUSUS.jpg");

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(multipartFile.getBytes());
        }

        return new ResponseEntity<>(multipartFile.getOriginalFilename() ,HttpStatus.OK);
	}

}
