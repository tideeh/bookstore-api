package com.example.helloworld.vo;

import java.util.Objects;

public class BookVO {

	private Long id;
	private String title;
	private String author;
	private String category;
	private String language;
	private float price;

	public BookVO() {
	}

	public BookVO(Long id, String title, String author, String category, String language, float price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.language = language;
		this.price = price;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof BookVO)) {
			return false;
		}
		BookVO bookVO = (BookVO) o;
		return Objects.equals(id, bookVO.id) && Objects.equals(title, bookVO.title)
				&& Objects.equals(author, bookVO.author) && Objects.equals(category, bookVO.category)
				&& Objects.equals(language, bookVO.language) && price == bookVO.price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, author, category, language, price);
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", title='" + getTitle() + "'" +
				", author='" + getAuthor() + "'" +
				", category='" + getCategory() + "'" +
				", language='" + getLanguage() + "'" +
				", price='" + getPrice() + "'" +
				"}";
	}

}