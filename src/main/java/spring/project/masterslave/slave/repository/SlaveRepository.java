package spring.project.masterslave.slave.repository;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import spring.project.masterslave.domain.entity.Slave;

@Repository
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.SLAVE, matchIfMissing = false)
public interface SlaveRepository extends JpaRepository<Slave, Long> {
}
