package kodlamaio.hrmsbackend.adapters;

import kodlamaio.hrmsbackend.business.abstracts.ApplicantCheckService;
import kodlamaio.hrmsbackend.entities.concretes.Applicant;
import kodlamaio.hrmsbackend.mernisServiceReference.RVAKPSPublicSoap;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdapter implements ApplicantCheckService {
    @Override
    public boolean checkIfCitizen(Applicant applicant) throws Exception {
        RVAKPSPublicSoap client = new RVAKPSPublicSoap();
        return client.TCKimlikNoDogrula(Long.valueOf(applicant.getNationalId()),
                applicant.getFirstName(), applicant.getLastName(), applicant.getDateOfBirth().getYear());
    }
}
