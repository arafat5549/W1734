package com.ssf.model;

import java.math.BigDecimal;

/**
 * 账户类
 * @author wyy
 *
 */
public class TxAccount {
    private Integer id;
    private String name;
    private BigDecimal balance;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "TxAccount [id=" + id + ", name=" + name + ", balance="
				+ balance + "]";
	}
    
    
}
