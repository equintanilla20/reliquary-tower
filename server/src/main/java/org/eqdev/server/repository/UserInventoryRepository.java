package org.eqdev.server.repository;

import org.eqdev.server.model.UserInventory;
import org.eqdev.server.model.UserInventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInventoryRepository extends JpaRepository<UserInventory, UserInventoryId> {
    
}
