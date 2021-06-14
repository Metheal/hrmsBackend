package kodlamaio.hrmsbackend.business.abstracts;

import kodlamaio.hrmsbackend.core.utilities.results.Result;

public interface EmailService {
    Result sendEmail(String emailAddress, String message);
}
