package io.aktivator.events;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.aktivator.user.services.AuthUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {
    private AuthUserDTO donor;
    private BigDecimal amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
