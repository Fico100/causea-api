package io.aktivator.campaign.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.aktivator.user.services.AuthUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private AuthUserDTO user;
    private String text;
    private Long campaignId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
