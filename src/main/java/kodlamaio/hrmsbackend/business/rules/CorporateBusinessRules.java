package kodlamaio.hrmsbackend.business.rules;

import kodlamaio.hrmsbackend.business.requests.CreateCorporateRequest;
import kodlamaio.hrmsbackend.core.utilities.exceptions.BusinessException;
import kodlamaio.hrmsbackend.dataAccess.abstracts.CorporateDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CorporateBusinessRules {
    private final CorporateDao corporateDao;

    public void checkIfEmailMatchesDomain(CreateCorporateRequest createCorporateRequest) {
        var domainFromEmail = createCorporateRequest.getEmail().split("@")[1];
        var result = createCorporateRequest.getWebsiteUrl().contains(domainFromEmail);
        if (!result) {
            throw new BusinessException("Email adresi ile web sitesi eslesmiyor");
        }
    }

    public void checkIfCompanyNameIsUnique(CreateCorporateRequest createCorporateRequest) {
        var result = this.corporateDao.existsByCompanyName(createCorporateRequest.getCompanyName());
        if (result)
            throw new BusinessException("Bu sirket adi zaten kayitli");
    }
}
