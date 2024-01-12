package be.tftic.spring.demo.bonus.annotation;

import be.tftic.spring.demo.api.model.dto.UserDTO;

public class DemoAnnotation {

    public static void main(String[] args) {
        Class<UserDTO> clazz = UserDTO.class;
//		UserDTO dto = new UserDTO(0,null,null);
//		clazz = (Class<UserDTO>) dto.getClass();

        Annotated annotated = clazz.getAnnotation(Annotated.class);
        String valeur = annotated.value();
    }




}
