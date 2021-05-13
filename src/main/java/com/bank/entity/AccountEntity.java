package com.bank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bank.domain.Account;
import com.bank.domain.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

	@Id
	private String id;
	
	@Column(nullable = false, length = 50)
	private String number;
	
	@Column(name = "branch_location", nullable = false, length = 50)
	private String branchLocation;
	
	@Column(nullable = false, length = 50)
	private AccountType type;
	
	@Column(name = "interest_rate", nullable = false, length = 50)
	private Double interestRate;
	
	@Column(name = "opening_date", nullable = false, length = 50)
	private LocalDate openingDate;
	
	@Column(nullable = false, length = 50)
	private Boolean active;
	
	@Column(nullable = false, length = 50)
	private String card;
	
	@Column(nullable = false, length = 50)
	private Float balance;
	
	@OneToMany(cascade = CascadeType.REFRESH)
	private List<TransactionEntity> transactionEntities;
	
	/**
	 * Method to prepare Account DTO from account entity
	 * @param accountEntity
	 * @return account
	 */
	public static Account prepareDto(AccountEntity accountEntity) {
		Account account = new Account(accountEntity.getId(), accountEntity.getNumber(),
				accountEntity.getBranchLocation(), accountEntity.getType(), accountEntity.getInterestRate(),
				accountEntity.getOpeningDate(), accountEntity.getActive(), accountEntity.getCard(),
				accountEntity.getBalance(), TransactionEntity.prepareTransactionList(accountEntity.getTransactionEntities()));
		return account;
	}
	
	/**
	 * Method to prepare Account List from Account Entity list
	 * @param accountEntities
	 * @return list of accounts
	 */
	public static List<Account> prepareDtoList(List<AccountEntity> accountEntities) {
		List<Account> accounts = new ArrayList<>();
		for(AccountEntity entity: accountEntities) {
			accounts.add(prepareDto(entity));
		}
		return accounts;
	}
}