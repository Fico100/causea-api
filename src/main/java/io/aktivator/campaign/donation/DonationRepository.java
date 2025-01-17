package io.aktivator.campaign.donation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationRepository extends PagingAndSortingRepository<Donation, Long> {
    Optional<Donation> findByIdAndLikes_Owner_Id(Long id, Long ownerId);
    Page<Donation> findByOwnerId(Long valueOf, Pageable pageable);
}
