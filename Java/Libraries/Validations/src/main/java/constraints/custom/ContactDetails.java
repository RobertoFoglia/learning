package constraints.custom;

import constraints.custom.annotations.CheckCase;
import constraints.custom.payload.Severity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetails {
    @NotNull(message = "Name is mandatory", payload = Severity.Error.class)
    @CheckCase(value = CaseMode.LOWER)
    private String name;

    @NotNull(message = "Phone number not specified, but not mandatory",
            payload = Severity.Info.class)
    @CheckCase(value = CaseMode.UPPER, message = "you can't use it")
    private String surname;
}
