package br.biblioteca.livros.beans;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

    public static String write(String baseFolder, MultipartFile file) {

        String realPath = "/home/gtkanal/Documentos/documentos_fib_java/images/" + baseFolder;

        // se for windows, verificar corretamenteo path da pasta, por exemplo
        //  String realPath = "D:\\Arquivos\\" + baseFolder;



        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        try {
            String path = realPath + "/" + file.getOriginalFilename();
            file.transferTo(new File(path));
            return baseFolder + "/" + file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
