package isa.adventuretime.Controller;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "/api/image")
public class ImageController {

    @PostMapping(path = "/save/{namePic}")
	public ResponseEntity<String> saveImage(@PathVariable("namePic") String namePic, @RequestParam ("file") MultipartFile multipartFile) throws Exception {
        // System.out.println("New picture name:");
        // System.out.println(namePic);
        String imgName = namePic + ".png";
        Path filepath = Paths.get("src/frontend/src/assets/images", imgName);
        if (System.getProperty("os.name").indexOf("Win") >= 0)
            filepath = Paths.get("src\\frontend\\src\\assets\\images", imgName);
                
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(multipartFile.getBytes());
        }
        return new ResponseEntity<>(multipartFile.getOriginalFilename(), HttpStatus.OK);
	}
    @PostMapping(path = "/existsByIdAndType/{id}") 
    public ResponseEntity<Boolean> existsByIdAndType(@PathVariable("id") Long id, RequestEntity<String> type) {
        switch (type.getBody().replace("=", "")) {
            case "COTTAGE":
                String imgName = "cottage_" + id + ".png";
                Path filepath = Paths.get("src/frontend/src/assets/images", imgName);
                if (System.getProperty("os.name").indexOf("Win") >= 0)
                    filepath = Paths.get("src\\frontend\\src\\assets\\images", imgName);
                
                
                return new ResponseEntity<>(Files.exists(filepath), HttpStatus.OK);

            case "ADVENTURE":
                imgName = "adventure_" + id + ".png";
                filepath = Paths.get("src/frontend/src/assets/images", imgName);
                if (System.getProperty("os.name").indexOf("Win") >= 0)
                    filepath = Paths.get("src\\frontend\\src\\assets\\images", imgName);

                System.out.println(Files.exists(filepath));
                return new ResponseEntity<>(Files.exists(filepath), HttpStatus.OK);

            case "BOAT":
                imgName = "boat_" + id + ".png";
                filepath = Paths.get("src/frontend/src/assets/images", imgName);
                if (System.getProperty("os.name").indexOf("Win") >= 0)
                    filepath = Paths.get("src\\frontend\\src\\assets\\images", imgName);
                
                return new ResponseEntity<>(Files.exists(filepath), HttpStatus.OK);
            
            default:
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
