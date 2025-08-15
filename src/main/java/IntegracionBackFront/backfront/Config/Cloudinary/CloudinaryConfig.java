package IntegracionBackFront.backfront.Config.Cloudinary;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//Clase de configuracion, las clases de configuracion tienden a ejecutarse cuando la api se empieza a ejecutar
//Tambien sirve que al ejecutarse la appi, las variables se envien al CLoudinary
@Configuration
public class CloudinaryConfig {

    private String cloudName;
    private String apiKey;
    private String apiSecret;

    //Bean sirve para que este metodo pueda ser inyectado sobre cualquier otra clase con un tipo de inyección diferente a @Autowired
    public Cloudinary cloudinary(){
        //Cargando todas las variables del archivo .env
        Dotenv dotenv = Dotenv.load();

        //Crear un Map para almacenar la configuración
        //Sirve para guardar la clave valor de las variables de cloudinary
        Map<String, String> config = new HashMap<>();

        //Obteniendo las credenciales desde las variables de entorno
        config.put("cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"));
        config.put("api_key", dotenv.get("CLOUDINARY_API_KEY"));
        config.put("api_secret", dotenv.get("CLOUDINARY_API_SECRET"));

        //Retornar una nueva instancia de cloudinary con la configuración cargada
        //Si cloudinary llega a tener algún error la api no se ejecuta
        return new Cloudinary(config);
    }
}
