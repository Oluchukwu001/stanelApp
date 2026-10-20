package stanel.app.store.company;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Company createCompany(CompanyRequestDTO requestDTO) {
        validateCompanyRequestDTO(requestDTO);
        Company company = new Company();
        company.setName(requestDTO.name());
        company.setEmail(requestDTO.email());
        company.setLogoUrl(requestDTO.logo());
        company.setAddress(requestDTO.address());
        company.setPhoneNumber(requestDTO.phoneNumber());
        List<String> images = requestDTO.imageUrlList().stream().filter(Objects::nonNull).toList();
        company.setImageUrls(images);
        return companyRepository.save(company);

    }

    @Transactional
    public Company updateCompany(Long id, CompanyRequestDTO requestDTO) {
        Company company = ServiceUtil.findOrThrow(companyRepository, id, "Company");
        ServiceUtil.setIfPresent(requestDTO.name(), company::setName);
        ServiceUtil.setIfPresent(requestDTO.address(), company::setAddress);
        ValidationUtil.requireValidEmail(requestDTO.email(), "Provide a valid email");
        ServiceUtil.setIfPresent(requestDTO.email(), company::setEmail);
        ServiceUtil.setIfPresent(requestDTO.logo(), company::setLogoUrl);
        ServiceUtil.setIfPresent(requestDTO.phoneNumber(), company::setPhoneNumber);
        if (!requestDTO.imageUrlList().isEmpty()) {
            addImageUrlToExistingOnes(company, requestDTO);
        }
        return companyRepository.save(company);
    }

    @Transactional
    public String deleteCompanyById(Long id) {
        if (!companyRepository.existsById(id)) {
            return "company with id " + id + "not found";
        }
        return "company successfully deleted";
    }

    @Transactional(readOnly = true)
    public Company getCompanyById(Long id) {
        return ServiceUtil.findOrThrow(companyRepository, id, "Company");
    }

    @Transactional(readOnly = true)
    public Page<Company> getAllCompanies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findAll(pageable);
    }

    private void validateCompanyRequestDTO(CompanyRequestDTO requestDTO) {
        ValidationUtil.requireNonBlank(requestDTO.name(), "Name is required");
        ValidationUtil.requireNonBlank(requestDTO.address(), "Address is required");
        ValidationUtil.requireNonBlank(requestDTO.email(), "Email is required");
        ValidationUtil.requireValidEmail(requestDTO.email(), "Provide valid email");
        ValidationUtil.requireNonBlank(requestDTO.phoneNumber(), "Phone number is required");
        ValidationUtil.requireNonBlank(requestDTO.logo(), "Logo url required");
        ValidationUtil.requireNotEmpty(requestDTO.imageUrlList(), "Image urls are required");
    }

    private void addImageUrlToExistingOnes(Company company, CompanyRequestDTO requestDTO) {

        for (String url : requestDTO.imageUrlList()) {
            if (!company.getImageUrls().contains(url.trim()) && url != null) {
                company.getImageUrls().add(url);
            } else {
                company.getImageUrls().removeIf(el -> url.equalsIgnoreCase(el.trim()) && url != null);
                company.getImageUrls().add(url);
            }

        }
    }

}
