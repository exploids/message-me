package webeng.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.LinkedList;
import java.util.List;

@FacesValidator
public class NameValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String name = value.toString();
        List<FacesMessage> messages = new LinkedList<>();
        if (name.length() < 1) {
            messages.add(new FacesMessage("Der Name ist zu kurz"));
        } else if (name.length() > 32) {
            messages.add(new FacesMessage("Der Name ist zu lang"));
        }
        if (name.startsWith("_")) {
            messages.add(new FacesMessage("Der Name muss mit einem Buchstaben beginnen"));
        }
        if (name.endsWith("_")) {
            messages.add(new FacesMessage("Der Name muss mit einem Buchstaben enden"));
        }
        char last = 0;
        for (char character : name.toCharArray()) {
            if ((character < 'a' || character > 'z') && character != 'ä' && character != 'ö' && character != 'ü' && character != 'ß' && character != '_') {
                messages.add(new FacesMessage("Der Name darf nur aus a, …, z, ä, ö, ü, ß und _ bestehen"));
            } else if (character == '_' && last == '_') {
                messages.add(new FacesMessage("Der Name darf nicht mehrere _ hintereinander enthalten"));
            }
            last = character;
        }
        if (!messages.isEmpty()) {
            throw new ValidatorException(messages);
        }
    }
}
