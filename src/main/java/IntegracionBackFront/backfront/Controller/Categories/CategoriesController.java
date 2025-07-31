package IntegracionBackFront.backfront.Controller.Categories;
import IntegracionBackFront.backfront.Models.DTO.Categories.CategoryDTO;
import IntegracionBackFront.backfront.Services.Categories.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiCategory")
public class CategoriesController {
    @Autowired
    private CategoryService service;

    @GetMapping("/getDataCategories")
    private ResponseEntity<Page<CategoryDTO>> getData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        if(size <= 0 || size > 50){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño de la pagina debe de ser entre 1 y 50 "
            ));
                    return ResponseEntity.ok(null);
        }
        //Invocando al metodo del service y guardando los datos en el objeto de tipo DTO
        //Si no hay datos el objeto será nulo
        Page<CategoryDTO> dto = service.getAllCategories(page, size);
        if(dto == null){
            ResponseEntity.badRequest().body(Map.of(
                    "Status", "No hay registros de categorias"
            ));
        }
        return ResponseEntity.ok(dto);
    }
}
