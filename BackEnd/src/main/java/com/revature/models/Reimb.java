package com.revature.models;

import java.sql.Timestamp;
import java.util.Date;

public class Reimb {
	
	private int reimb_id;
	private double reimb_amount;
	private Date reimb_submitted;
	private Date reimb_resolved;
	private String reimb_description;
	private String reimb_receipt_url;
	private int reimb_status_id_fk;
	private int reimb_type_id_fk;
	private int reimb_author_id_fk;
	private int reimb_resolver_id_fk;
	
	private ReimbStatus reimbStatus;
	private ReimbType reimbType;
	private User reimbAuthor;
	private User reimbResolver;
	
	/* MARK: - Constructors --------------------------------------------------------------------- */
	
	//For getting Reimb
	public Reimb(int reimb_id, double reimb_amount, Date reimb_submitted, Date reimb_resolved,
			String reimb_description, String reimb_receipt_url, int reimb_status_id_fk, int reimb_type_id_fk,
			int reimb_author_id_fk, int reimb_resolver_id_fk, ReimbStatus reimbStatus, ReimbType reimbType, User author, User resolver) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt_url = reimb_receipt_url;
		this.reimb_status_id_fk = reimb_status_id_fk;
		this.reimb_type_id_fk = reimb_type_id_fk;
		this.reimb_author_id_fk = reimb_author_id_fk;
		this.reimb_resolver_id_fk = reimb_resolver_id_fk;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
		this.reimbAuthor = author;
		this.reimbResolver = resolver;
	}
	
	// For inserting reimb
	public Reimb(double reimb_amount, String reimb_description, String reimb_receipt_url, int reimb_type_id_fk,
			int reimb_author_id_fk) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_description = reimb_description;
		this.reimb_receipt_url = reimb_receipt_url;
		this.reimb_type_id_fk = reimb_type_id_fk;
		this.reimb_author_id_fk = reimb_author_id_fk;
	}

	/* MARK: - Getters and Setters ---------------------------------------------------------------- */
	
	// Getters
	public int getReimb_id() {
		return reimb_id;
	}

	public double getReimb_amount() {
		return reimb_amount;
	}

	public Date getReimb_submitted() {
		
//		LocalDateTime reimbSubmitted = reimb_submitted.toLocalDateTime();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM uu H:m", Locale.ENGLISH);
//		String formattedDate = reimbSubmitted.format(formatter);
		return reimb_submitted;
	}

	public Date getReimb_resolved() {
		return reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public String getReimb_receipt_url() {
		return reimb_receipt_url;
	}

	public int getReimb_status_id_fk() {
		return reimb_status_id_fk;
	}

	public int getReimb_type_id_fk() {
		return reimb_type_id_fk;
	}

	public int getReimb_author_id_fk() {
		return reimb_author_id_fk;
	}

	public int getReimb_resolver_id_fk() {
		return reimb_resolver_id_fk;
	}

	public ReimbStatus getReimbStatus() {
		return reimbStatus;
	}

	public ReimbType getReimbType() {
		return reimbType;
	}

	public User getReimbAuthor() {
		return reimbAuthor;
	}

	public User getReimbResolver() {
		return reimbResolver;
	}

	// Setters
	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public void setReimb_amount(double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public void setReimb_submitted(Date reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public void setReimb_resolved(Date reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public void setReimb_receipt_url(String reimb_receipt_url) {
		this.reimb_receipt_url = reimb_receipt_url;
	}

	public void setReimb_status_id_fk(int reimb_status_id_fk) {
		this.reimb_status_id_fk = reimb_status_id_fk;
	}

	public void setReimb_type_id_fk(int reimb_type_id_fk) {
		this.reimb_type_id_fk = reimb_type_id_fk;
	}

	public void setReimb_author_id_fk(int reimb_author_id_fk) {
		this.reimb_author_id_fk = reimb_author_id_fk;
	}

	public void setReimb_resolver_id_fk(int reimb_resolver_id_fk) {
		this.reimb_resolver_id_fk = reimb_resolver_id_fk;
	}

	public void setReimbStatus(ReimbStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}

	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	
}
