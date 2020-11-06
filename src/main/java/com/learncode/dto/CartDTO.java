package com.learncode.dto;

import com.learncode.models.SanphamVaChitiet;

public class CartDTO {

	private SanphamVaChitiet product;
	private int quantity;

	public CartDTO() {
		super();
	}

	public CartDTO(SanphamVaChitiet product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public SanphamVaChitiet getProduct() {
		return product;
	}

	public void setProduct(SanphamVaChitiet product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartDTO [product=" + product + ", quantity=" + quantity + "]";
	}

}
