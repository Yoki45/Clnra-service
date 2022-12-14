package io.clnra.c_l_n_r_a.service;

import io.clnra.c_l_n_r_a.domain.Partners;
import io.clnra.c_l_n_r_a.model.PartnersDTO;
import io.clnra.c_l_n_r_a.repos.PartnersRepository;
import io.clnra.c_l_n_r_a.util.NotFoundException;
import io.clnra.c_l_n_r_a.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PartnersService {

    private final PartnersRepository partnersRepository;

    public PartnersService(final PartnersRepository partnersRepository) {
        this.partnersRepository = partnersRepository;
    }

    public List<PartnersDTO> findAll() {
        final List<Partners> partnerss = partnersRepository.findAll(Sort.by("id"));
        return partnerss.stream()
                .map((partners) -> mapToDTO(partners, new PartnersDTO()))
                .collect(Collectors.toList());
    }

    public PartnersDTO get(final Long id) {
        return partnersRepository.findById(id)
                .map(partners -> mapToDTO(partners, new PartnersDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final PartnersDTO partnersDTO) {
        final Partners partners = new Partners();
        mapToEntity(partnersDTO, partners);
        return partnersRepository.save(partners).getId();
    }

    public void update(final Long id, final PartnersDTO partnersDTO) {
        final Partners partners = partnersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(partnersDTO, partners);
        partnersRepository.save(partners);
    }

    public void delete(final Long id) {
        partnersRepository.deleteById(id);
    }

    private PartnersDTO mapToDTO(final Partners partners, final PartnersDTO partnersDTO) {
        partnersDTO.setId(partners.getId());
        partnersDTO.setPartnerName(partners.getPartnerName());
        partnersDTO.setPartnerType(partners.getPartnerType());
        partnersDTO.setPartnerAddress(partners.getPartnerAddress());
        partnersDTO.setPartnerEmail(partners.getPartnerEmail());
        partnersDTO.setCBOName(partners.getCBOName());
        return partnersDTO;
    }

    private Partners mapToEntity(final PartnersDTO partnersDTO, final Partners partners) {
        partners.setPartnerName(partnersDTO.getPartnerName());
        partners.setPartnerType(partnersDTO.getPartnerType());
        partners.setPartnerAddress(partnersDTO.getPartnerAddress());
        partners.setPartnerEmail(partnersDTO.getPartnerEmail());
        partners.setCBOName(partnersDTO.getCBOName());
        return partners;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Partners partners = partnersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        if (!partners.getCBOPartnersCBORegistrations().isEmpty()) {
            return WebUtils.getMessage("partners.cBORegistration.manyToMany.referenced", partners.getCBOPartnersCBORegistrations().iterator().next().getId());
        }
        return null;
    }

}
