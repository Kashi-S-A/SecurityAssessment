package com.ks.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ks.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	private String taskName;

	@CreationTimestamp
	private LocalDateTime assignedDate;

	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.ASSIGNED;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
