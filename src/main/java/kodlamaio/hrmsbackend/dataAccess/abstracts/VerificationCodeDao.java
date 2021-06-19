package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeDao extends JpaRepository<VerificationCode, Integer> {
    VerificationCode getVerificationCodeById(int id);
    VerificationCode getVerificationCodeByUserId(int id);
    VerificationCode getVerificationCodeByCode(String code);
}
