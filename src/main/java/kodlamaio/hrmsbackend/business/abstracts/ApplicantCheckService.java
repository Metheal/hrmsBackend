package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.Applicant;

public interface ApplicantCheckService {
    boolean checkIfCitizen(Applicant applicant) throws Exception;
}
