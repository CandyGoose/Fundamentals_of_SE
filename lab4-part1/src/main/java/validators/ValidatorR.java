package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validatorR")
public class ValidatorR implements Validator<Object> {
    private static final float MAX_R = 3;
    private static final float MIN_R = -5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Поле R не может быть пустым!"));
        }
        double yValue = Double.parseDouble(String.valueOf(o));
        if (yValue < MIN_R || yValue > MAX_R) throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                        String.format("Значение X должно находиться в отрезке [%s ... %s].", MIN_R, MAX_R))
        );
    }
}