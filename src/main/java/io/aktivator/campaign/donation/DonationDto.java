package io.aktivator.campaign.donation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.aktivator.campaign.CampaignStatus;
import io.aktivator.configuration.UserIdSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonationDto {
    private Long id;
    private String title;
    private String description;
    private Long ownerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created = new Date();
    private Long target;
    private boolean featured;
    private boolean liked;
    private int likesCount;
    private CampaignStatus campaignStatus;
    private BigDecimal balance;
}
