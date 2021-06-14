package kodlamaio.hrmsbackend.business.concretes;

import kodlamaio.hrmsbackend.business.abstracts.EmailService;
import kodlamaio.hrmsbackend.core.utilities.results.Result;
import kodlamaio.hrmsbackend.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService {
    @Override
    public Result sendEmail(String emailAddress, String message) {
        System.out.println("Alici: " + emailAddress + ". Gonderilen mesaj: " + message);
        return new SuccessResult("Email gonderildi");
    }
}
