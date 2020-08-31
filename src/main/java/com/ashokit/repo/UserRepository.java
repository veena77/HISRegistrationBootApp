package com.ashokit.repo;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ashokit.entity.UserEntity;
import java.lang.String;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<UserEntity,Serializable>{


public UserEntity findByPassword(String tempPwd);

//	public UserEntity findByPasswordAndEmailId(String tempPwd,String email);
	
UserEntity findByEmailId(String emailId);
List<UserEntity> findByRoleName(String roleName);

@Modifying
@Query(value = "update UserEntity e set e.activeSW=:activeSW where e.userId=:id")
int deleteByIdAndActiveSW(String id,String activeSW);

/*Native::
 @Modifying
@Query(value = "update Users u set u.status = ? where u.name = ?", 
  nativeQuery = true)
int updateUserSetStatusForNameNative(Integer status, String name);
*/

}
