package uz.fayz.model;

import com.sun.javadoc.SeeTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String language;
    private String fullName;
    private String birthDate;
    private String province;            // viloyati 2
    private String region;              // tuman yoki shahar 1
    private String jobName;             // ish joyi
    private String position;            // lavozim
    private String typeOfDescription;   // shikoyat turi
    private String description;         // shikoyat
    private String phoneNumber;
    private Audio audio;
    private Voice voice;
    private Video video;
    private List<PhotoSize> photoSize;
    private Document document;

    @Override
    public String toString() {
        return "Murojaatchining ma'lumotlari:\n\n" +
                "\uD83D\uDE4D\uD83C\uDFFB\u200D♂️  F.I.SH. :  " + fullName +
                "\n\n\uD83D\uDD22  Tu'g'ilgan sana:  " + birthDate +
                "\n\n\uD83C\uDFD4  Viloyati:  "  + province +
                "\n\n\uD83C\uDFE0  Tumani:  "  + region +
                "\n\n\uD83D\uDC49  Tashkilot nomi:  "  + jobName +
                "\n\n\uD83D\uDC6E\uD83C\uDFFC\u200D♂️  Lavozimi:  " + position +
                "\n\n⁉️  Yo'llangan murojaatning turi:  " + "\n" + typeOfDescription +
                "\n\n\uD83E\uDD26\uD83C\uDFFB\u200D♂️  Murojaat matni:  " + "\n\n" + description +
                "\n\n\uD83D\uDCF1  Foydalanuvchining tel. raqami:  " + phoneNumber;
    }
}
