package ra.databinding.model.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormLogin {
    @NotBlank(message = "Thiếu username")
    private String username;
    @NotBlank(message = "Thiếu password")
//    @Size(min = 6, message = "Password phải từ 6 kí tự trở lên")
    private String password;
}
