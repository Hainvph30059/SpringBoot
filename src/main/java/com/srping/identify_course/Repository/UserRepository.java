package com.srping.identify_course.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping.identify_course.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(
            String username); // Tạo hàm tìm theo một field nào đó chỉ cần bắt đâu bằng existBy{field} spring sẽ tự
    // động tạo phương thức mà không cần implentmen

    Optional<User> findByUsername(String username);
}
