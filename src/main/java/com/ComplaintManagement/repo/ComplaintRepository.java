package com.ComplaintManagement.repo;

import com.ComplaintManagement.model.Complaint;
import com.ComplaintManagement.model.ComplaintStatus;
import com.ComplaintManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {


     public List<Complaint> findByUser(User user);

     public List<Complaint> findByStatus(ComplaintStatus complaintStatus);

     public List<Complaint> findByCategory(String category);

     Optional<Complaint> findByIdAndUser(Long id, User user);

     @Query("select c.status,count(c) from Complaint c group by c.status")
     public List<Object[]> complaintStatsByStatus();


}
