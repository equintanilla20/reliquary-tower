package org.eqdev.server.repository;

import org.eqdev.server.model.UserInventory;
import org.eqdev.server.model.UserInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserInventoryRepository extends JpaRepository<UserInventory, UserInventoryId> {
    List<UserInventory> findByIdUserId(Long userId);
}
