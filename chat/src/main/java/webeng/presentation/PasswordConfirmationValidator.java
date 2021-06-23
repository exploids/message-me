package webeng.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class PasswordConfirmationValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        UIInput passwordInput = (UIInput) context.getViewRoot().findComponent("login:password");
        String original = (String) passwordInput.getValue();
        String confirmation = (String) value;
        if (!confirmation.equals(original)) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Die Passwörter stimmen nicht überein",
                    "Die Passwörter stimmen nicht überein");
            throw new ValidatorException(message);
        }
    }
}
