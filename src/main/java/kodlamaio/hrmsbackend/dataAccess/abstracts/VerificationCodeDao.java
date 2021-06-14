package kodlamaio.hrmsbackend.dataAccess.abstracts;

import kodlamaio.hrmsbackend.entities.concretes.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeDao extends JpaRepository<VerificationCode, Integer> {
    VerificationCode findVerificationCodeById(int id);
    VerificationCode findVerificationCodeByUserId(int id);
    VerificationCode findVerificationCodeByCode(String code);
}
