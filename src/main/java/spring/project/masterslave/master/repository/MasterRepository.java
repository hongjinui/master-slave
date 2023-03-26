package spring.project.masterslave.master.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import spring.project.masterslave.domain.entity.Master;

import java.util.List;

@Repository
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.MASTER, matchIfMissing = false)
public interface MasterRepository extends JpaRepository<Master,Long> {

    @Query("select m from Master m order by m.id desc ")
    List<Master> findAllByOrderByIdDesc();
    @Query("select m from Master m where m.id > :id order by m.id desc ")
    List<Master> findAllByIdGreaterThanOrderByIdDesc(Long id);
}
