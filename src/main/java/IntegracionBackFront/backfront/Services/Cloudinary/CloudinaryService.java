package IntegracionBackFront.backfront.Services.Cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CloudinaryService {
    //1. Definir el tamaño de las imágenes en MB
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    //2. Definir las extensiones permitidas
    private static final String [] ALLOWED_EXTENSIONS = {".jpg", "jpeg", "png"};

   //3. Atributo del Cloudinary
    private final Cloudinary cloudinary;

    //4. Crear constructor de CLoudinary
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    //Cloudinary tiene la capacidad de separar las imagenes, osea  configurar si tengo imageens de empleados, esas imagenes ir a parar a una carpeta de emppleados, asi si tengo imagenes de productos, no se confundan ni se mezclen los de productos con empleados y viceversa

    public String uploadImage(MultipartFile file) throws IOException {
        validateImage(file);
    }

    private void validateImage(MultipartFile file) {
        //Verificar si el file esta vacio

        if(file.isEmpty()){
            throw new IllegalArgumentException("El archivo esta vacío");
        }

        //Verificar si el tamaño excede el tamaño permitido
        if(file.getSize() > MAX_FILE_SIZE){
            throw new IllegalArgumentException("El tamaño del archivo no debe de exceder a 5MB");
        }

        //Verificar nombre original del archivo del archivo
        String originalFileName = file.getOriginalFilename();
        if(originalFileName == null){
            throw new IllegalArgumentException("Nombre de archivo invalido");
        }

        //Extraer y validar la extensión del archivo
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
        if(!Arrays.asList(ALLOWED_EXTENSIONS).contains(extension)){
            throw new IllegalArgumentException("Solo se permiten archivos JPG, JPEG Y PNG");
        }

        //Verificar que el tipo MIME sea una imagen
        if(!file.getContentType().startsWith("image/")){
            throw new IllegalArgumentException("El archivo debe de ser una imágen válida");
        }
    }

}
