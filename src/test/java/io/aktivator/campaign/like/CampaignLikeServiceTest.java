package io.aktivator.campaign.like;

import io.aktivator.campaign.donation.DonationCampaignDto;
import io.aktivator.campaign.donation.DonationCampaignService;
import io.aktivator.user.model.User;
import io.aktivator.user.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CampaignLikeServiceTest {

    public static final long CURRENT_USER_ID = 3L;
    public static final long CREATED_LIKE_ID = 2L;
    public static final long CAMPAIGN_ID = 1L;
    public static final String EXTERNAL_ID = "wohoo";
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private UserService userService;
    @Mock
    private DonationCampaignService donationCampaignService;
    @InjectMocks
    private LikeService likeService;
    @Captor
    private ArgumentCaptor<CampaignLike> likeArgumentCaptor = ArgumentCaptor.forClass(CampaignLike.class);

    @Test
    @Disabled
    void shouldReturnLikeCreatedByRepository() {
        User user = new User();
        user.setId(CURRENT_USER_ID);
        user.setExternalId(EXTERNAL_ID);

        CampaignLike campaignLike = new CampaignLike();
        campaignLike.setOwner(user);
        CampaignLike createdCampaignLike = new CampaignLike();
        createdCampaignLike.setId(CREATED_LIKE_ID);

        DonationCampaignDto campaign = new DonationCampaignDto();
        campaign.setId(CAMPAIGN_ID);

        when(likeRepository.save(any(CampaignLike.class))).thenReturn(createdCampaignLike);
        when(donationCampaignService.getCampaign(CAMPAIGN_ID)).thenReturn(campaign);
        when(userService.getCurrentUser()).thenReturn(user);

        CampaignLike response = likeService.createLike(CAMPAIGN_ID);
        assertThat(response.getId()).isEqualTo(CREATED_LIKE_ID);
        verify(likeRepository).save(likeArgumentCaptor.capture());
        assertThat(likeArgumentCaptor.getValue().getOwner().getExternalId().equals(EXTERNAL_ID));
    }
}