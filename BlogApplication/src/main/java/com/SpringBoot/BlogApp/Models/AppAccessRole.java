package com.SpringBoot.BlogApp.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="AuthenticationRole")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppAccessRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int roleId;
	@Column(name = "role")
	private String accessRole;
}
