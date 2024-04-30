package com.example.helloworld.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Objects;

@Table(name = "BOOKS")
@Entity
public class Book extends BaseEntity {

	@Column(name = "TITLE")
	private String title;

	@Column(name = "AUTHOR")
	private String author;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "PRICE")
	private float price;

	public Book() {
	}

	public Book(String title, String author, String category, String language, float price) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.language = language;
		this.price = price;
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
		if (!(o instanceof Book)) {
			return false;
		}
		Book book = (Book) o;
		return Objects.equals(getId(), book.getId()) && Objects.equals(title, book.title)
				&& Objects.equals(author, book.author) && Objects.equals(category, book.category)
				&& Objects.equals(language, book.language) && price == book.price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), title, author, category, language, price);
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