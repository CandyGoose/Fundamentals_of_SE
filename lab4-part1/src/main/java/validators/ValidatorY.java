package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validatorY")
public class ValidatorY implements Validator<Object> {
    private static final float MAX_Y = 5;
    private static final float MIN_Y = -5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Поле Y не может быть пустым!"));
        }
        double yValue = Double.parseDouble(String.valueOf(o));
        if (yValue < MIN_Y || yValue > MAX_Y) throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                        String.format("Значение Y должно находиться в отрезке [%s ... %s].", MIN_Y, MAX_Y))
        );
    }
}