package uz.fayz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckBirthDate {
    private String result;
    private boolean success;
    private String resultBirthDate;
}
