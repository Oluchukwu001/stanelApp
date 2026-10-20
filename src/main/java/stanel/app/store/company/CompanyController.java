package stanel.app.store.company;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/company")
@PreAuthorize("hasRole(ADMIN)")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDTO createCompany(@RequestBody CompanyRequestDTO requestDTO) {
        return companyMapper.toDto(companyService.createCompany(requestDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponseDTO updateCompany(@PathVariable long id, @RequestBody CompanyRequestDTO requestDTO) {
        return companyMapper.toDto(companyService.updateCompany(id, requestDTO));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CompanyResponseDTO getCompanyById(@PathVariable Long id) {
        return companyMapper.toDto(companyService.getCompanyById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCompanyById(@PathVariable Long id) {
        return companyService.deleteCompanyById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CompanyResponseDTO> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return companyService.getAllCompanies(page, size).map(companyMapper::toDto);
    }

}
